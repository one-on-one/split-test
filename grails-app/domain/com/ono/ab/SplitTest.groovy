package com.ono.ab

class SplitTest {

    static hasMany = [splitTestVariants: SplitTestVariant]

    static final int PENDING = 0
    static final int RUNNING = 1
    static final int PAUSED = 2
    static final int COMPLETED = 3

    String name
    int status = PENDING
    Date dateStart = null
    Date dateFinish = null
    Date dateCreated

    static constraints = {
        name(blank: false, unique: true)
        status(blank: false, inList: [PENDING, RUNNING, PAUSED, COMPLETED])
        dateStart(blank: false)
        dateFinish(blank: false, validator: { val, obj ->
            return (val > obj.properties['dateStart'])
        })
    }

    static mapping = {
        version false
        sort name: 'asc'
    }

    def Boolean isLive() {
        if (
                (this.status == RUNNING) &&
                        (this.dateStart <= new Date()) &&
                        (this.dateFinish > new Date())) {
            return true
        } else {
            return false
        }
    }

    def SplitTestVariant leastHitVariant() {
        SplitTestVariant ret = null
        def activeSplitTestVariants = SplitTestVariant.findAllByIsActiveAndSplitTest(true, this)
        for (splitTestVariant in activeSplitTestVariants) {
            if (ret == null) {
                ret = splitTestVariant
            }
            if (splitTestVariant.totalHitCount() < ret.totalHitCount()) {
                ret = splitTestVariant
            }
        }
        return ret
    }

    def String displayStatus() {
        switch (status) {
            case PENDING:
                return "<span class='label label-warning'>Pending</span>"
                break
            case RUNNING:
                return "<span class='label label-success'>Running</span>"
                break
            case PAUSED:
                return "<span class='label'>Paused</span>"
                break
            case COMPLETED:
                return "<span class='label label-info'>Completed</span>"
                break
            default:
                return "<span class='label label-inverse'>Invalid</span>"
        }
    }

    def SplitTest archive() {
        String originalName = this.name
        this.name = "${this.name} - ${new Date().toString()}"
        this.status = SplitTest.COMPLETED
        if (!this.hasErrors() && this.save(flush:true)) {
            // Duplicate the test into a new one
            SplitTest newTest = new SplitTest()
            newTest.name = originalName
            newTest.dateStart = this.dateStart
            newTest.dateFinish = this.dateFinish
            newTest.status = RUNNING
            if (!newTest.hasErrors() && newTest.save(flush:true)) {
                for (splitTestVariant in this.splitTestVariants) {
                    SplitTestVariant newSplitTestVariant = new SplitTestVariant()
                    newSplitTestVariant.name = splitTestVariant.name
                    newSplitTestVariant.isActive = splitTestVariant.isActive
                    newSplitTestVariant.code = splitTestVariant.code
                    newSplitTestVariant.splitTest = newTest
                    if (!newSplitTestVariant.hasErrors() && newSplitTestVariant.save(flush:true)) {
                        println "Cloned Split Test Variant"
                    } else {
                        println "Unable to create split test variant:"
                        newSplitTestVariant.errors.fieldErrors.each { e ->
                            println e.toString()
                        }
                    }
                }
            } else {
                println "Unable to create split test:"
                newTest.errors.fieldErrors.each { e ->
                    println e.toString()
                }
            }
        } else {
            println "Unable to update original split test:"
            this.errors.fieldErrors.each { e ->
                println e.toString()
            }
        }
        return this
    }
}
