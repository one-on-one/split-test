package com.ono.ab

class SplitTestVariant {

    static belongsTo = [ splitTest: SplitTest ]
    static hasMany = [ splitTestVariantHits: SplitTestVariantHit ]

    SplitTest splitTest
    String name
    Boolean isActive = true
    String code

    static constraints = {
        name(blank:false, unique:'splitTest')
        isActive(blank:false)
        code(blank:false, maxSize: 5000)
    }

    static mapping = {
        code type: "text"
        version false
    }

    def Integer totalHitCount() {
        return totalCount(false, false, false, false)
    }

    def Integer totalConvertCount() {
        return totalCount(true, false, false, false)
    }

    def Integer totalActionOneCount() {
        return totalCount(false, true, false, false)
    }

    def Integer totalActionTwoCount() {
        return totalCount(false, false, true, false)
    }

    def Integer totalActionThreeCount() {
        return totalCount(false, false, false, true)
    }

    def Integer totalCount(Boolean includeDidConvert = false,
                           Boolean includeDidConvertActionOne = false,
                           Boolean includeDidConvertActionTwo = false,
                           Boolean includeDidConvertActionThree = false) {
        def c = SplitTestVariantHit.createCriteria()
        def totalHitCount = c.get {
            projections {
                countDistinct('id')
                and {
                    eq("splitTestVariant", this)
                    if (includeDidConvert == true) {
                        eq("didConvert", true)
                    }
                    if (includeDidConvertActionOne == true) {
                        eq("didConvertActionOne", true)
                    }
                    if (includeDidConvertActionTwo == true) {
                        eq("didConvertActionTwo", true)
                    }
                    if (includeDidConvertActionThree == true) {
                        eq("didConvertActionThree", true)
                    }

                }
            }
        }
        return totalHitCount
    }
}
