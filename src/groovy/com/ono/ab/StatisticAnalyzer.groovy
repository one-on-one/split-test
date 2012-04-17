package com.ono.ab

class StatisticAnalyzer {

    ArrayList contenders = []

    def void addContender(StatisticAnalyzerItem contender) {
        contenders.push(contender)
    }

    def StatisticAnalyzerItem getChampion() {
        StatisticAnalyzerItem champion = null
        for (contender in contenders) {
            if (champion == null) {
                champion = contender
            } else {
                if (contender.convertPercentage() > champion.convertPercentage()) {
                    champion = contender
                }
            }
        }
        return champion
    }

    def ArrayList getContenders() {
        ArrayList returnableContenders = []
        StatisticAnalyzerItem champion = getChampion()
        for (contender in contenders) {
            if (contender != champion) {
                returnableContenders.push(contender)
            }
        }
        return returnableContenders.sort()
    }

}
