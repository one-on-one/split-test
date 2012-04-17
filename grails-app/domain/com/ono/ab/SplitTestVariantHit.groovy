package com.ono.ab

class SplitTestVariantHit {

    static belongsTo = [ splitTestVariant: SplitTestVariant ]

    SplitTestVariant splitTestVariant
    Boolean didConvert
    Boolean didConvertActionOne
    Boolean didConvertActionTwo
    Boolean didConvertActionThree
    Date dateCreated

    static constraints = {
        splitTestVariant(nullable:false)
        didConvert(nullable:true)
        didConvertActionOne(nullable:true)
        didConvertActionTwo(nullable:true)
        didConvertActionThree(nullable:true)
    }

    static mapping = {
        version false
    }

    def void markAsConverted() {
        this.didConvert = true
        this.save(flush:true)
    }

    def void markActionOneAsConverted() {
        this.didConvertActionOne = true
        this.save(flush:true)
    }

    def void markActionTwoAsConverted() {
        this.didConvertActionTwo = true
        this.save(flush:true)
    }

    def void markActionThreeAsConverted() {
        this.didConvertActionThree = true
        this.save(flush:true)
    }
}
