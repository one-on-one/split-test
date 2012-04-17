package com.ono.ab

class SplitTestService {

    boolean transactional = false
    def sessionStorageService

    def SplitTestVariantHit loadSplitTestVariantHit(String testName) {

        // Load the Split Test or generate it
        SplitTest thisTest = findOrCreateSplitTestByName(testName)

        // Return the already-hit variant if it exists
        SplitTestVariantHit thisHit = loadCurrentSplitTestVariantHit(testName)
        if (thisHit) {
            return thisHit
        }

        // Ensure that the variants were created properly
        if (thisTest?.splitTestVariants?.size() == 0) {
            println "Unable to add hit to variant b/c variants list wasn't populated properly"
            return null
        }

        // Find the variant with the lowest number of hits
        SplitTestVariant returningVariant = thisTest?.leastHitVariant()

        SplitTestVariantHit splitTestVariantHit = new SplitTestVariantHit()
        splitTestVariantHit.splitTestVariant = returningVariant

        if (!splitTestVariantHit.hasErrors() && splitTestVariantHit.save(flush:true)) {
            sessionStorageService.getSession().putAt("splitTest_${sanitizeString(testName)}", splitTestVariantHit.id)
            return splitTestVariantHit

        } else {
            println "Unable to create split test variant hit for ${returningVariant?.name}:"
            splitTestVariantHit.errors.fieldErrors.each { e ->
                println e.toString()
            }
            return null
        }
    }

    def SplitTestVariantHit markTestAsActionOneConverted(String splitTestName) {
        SplitTestVariantHit splitTestVariantHit = loadCurrentSplitTestVariantHit(splitTestName)
        splitTestVariantHit?.markActionOneAsConverted()
        return splitTestVariantHit
    }

    def SplitTestVariantHit markTestAsActionTwoConverted(String splitTestName) {
        SplitTestVariantHit splitTestVariantHit = loadCurrentSplitTestVariantHit(splitTestName)
        splitTestVariantHit?.markActionTwoAsConverted()
        return splitTestVariantHit
    }

    def SplitTestVariantHit markTestAsActionThreeConverted(String splitTestName) {
        SplitTestVariantHit splitTestVariantHit = loadCurrentSplitTestVariantHit(splitTestName)
        splitTestVariantHit?.markActionThreeAsConverted()
        return splitTestVariantHit
    }

    def SplitTestVariantHit markTestAsConverted(String splitTestName) {
        SplitTestVariantHit splitTestVariantHit = loadCurrentSplitTestVariantHit(splitTestName)
        splitTestVariantHit?.markAsConverted()
        return splitTestVariantHit
    }

    def SplitTest findOrCreateSplitTestByName(String testName) {
        SplitTest thisTest = SplitTest.findByName(testName)
        if (!thisTest) {
            thisTest = new SplitTest()
            thisTest.name = testName
            thisTest.dateStart = new Date()
            thisTest.dateFinish = new Date() + 7
            thisTest.status = SplitTest.PENDING
            if (!thisTest.hasErrors() && thisTest.save(flush:true)) {
                println "Created new split test"
            } else {
                println "Unable to create split test:"
                thisTest.errors.fieldErrors.each { e ->
                    println e.toString()
                }
                return null
            }
        }
        return thisTest
    }

    def SplitTestVariantHit loadCurrentSplitTestVariantHit(String testName) {
        Long currentVariantHitId = sessionStorageService.getSession().getAt("splitTest_${sanitizeString(testName)}")
        if (currentVariantHitId != null) {
            def tmpVariantHit = SplitTestVariantHit.findById(currentVariantHitId)
            if (tmpVariantHit) {
                return tmpVariantHit
            } else {
                return null
            }
        }
    }

    def LinkedHashMap getStatistics() {
        LinkedHashMap theseTests = [:]
        for (splitTest in SplitTest.list(sort:"id", order:"desc")) {
            StatisticAnalyzer statisticAnalyzer = getStatisticsForTest(splitTest.id)
            if (statisticAnalyzer.contenders.size > 0) {
                theseTests[splitTest.name] = statisticAnalyzer
            }
        }
        return theseTests
    }

    def StatisticAnalyzer getStatisticsForTest(Long splitTestId) {
        SplitTest splitTest = SplitTest.findById(splitTestId)
        StatisticAnalyzer statisticAnalyzer = new StatisticAnalyzer()
        for (splitTestVariant in splitTest.splitTestVariants) {
            StatisticAnalyzerItem contender = new StatisticAnalyzerItem()
            contender.id = splitTestVariant.id
            contender.name = splitTestVariant.name
            contender.hitCount = splitTestVariant.totalHitCount()
            contender.convertCount = splitTestVariant.totalConvertCount()
            contender.actionOneCount = splitTestVariant.totalActionOneCount()
            contender.actionTwoCount = splitTestVariant.totalActionTwoCount()
            contender.actionThreeCount = splitTestVariant.totalActionThreeCount()
            statisticAnalyzer.addContender(contender)
        }
        return statisticAnalyzer
    }

    def String sanitizeString(String val) {
        return (val =~ /\W/).replaceAll('_')
    }

    def getStats() {
        for (splitTest in SplitTest.list()) {
            println "Name: ${splitTest.name}"
            for (splitTestVariant in splitTest.splitTestVariants) {
                println "-- Option: ${splitTestVariant.name}"
                println "     Hits: ${splitTestVariant.totalHitCount()}"
                println "     Conv: ${splitTestVariant.totalConvertCount()}"
                println "      One: ${splitTestVariant.totalActionOneCount()}"
                println "      Two: ${splitTestVariant.totalActionTwoCount()}"
                println "    Three: ${splitTestVariant.totalActionThreeCount()}"
                println "   % Conv: ${splitTestVariant.percentConverted()}"
            }
        }
    }
}
