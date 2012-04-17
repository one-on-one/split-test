package com.ono.ab

class SplitTestTagLib {

    def splitTestService

    static namespace = "ab"

    def splitTest = { attrs, body ->
//        try {
            String splitTestName = attrs.name
            SplitTest splitTest = splitTestService.findOrCreateSplitTestByName(splitTestName)
            if (splitTest && splitTest.isLive()) {
                SplitTestVariantHit splitTestVariantHit = splitTestService.loadSplitTestVariantHit(splitTestName)
                if (splitTestVariantHit != null) {
                    println "Rendering contents of split test variant for ${splitTest?.name}."
                    out << splitTestVariantHit.splitTestVariant.code.decodeHTML()
                } else {
                    println "Split test hit is null. Rendering default content."
                    out << body()
                }
            } else {
                println "Split test ${splitTest?.name} is not active. Rendering default content."
                out << body()
            }
//        } catch (Exception e) {
//            println "Skipping A/B Test b/c of Exception: ${e.message}"
//            e.printStackTrace()
//            out << body()
//        }
    }

}
