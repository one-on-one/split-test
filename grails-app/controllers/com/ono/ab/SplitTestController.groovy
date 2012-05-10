package com.ono.ab

class SplitTestController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def splitTestService

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        params.sort = (params.sort ? params.sort : 'id')
        params.order = (params.order ? params.order : 'desc')
        [ splitTestInstanceList: SplitTest.list(params), splitTestInstanceTotal: SplitTest.count() ]
    }

    def create() {
        render "N/A"
    }

    def save() {
        def splitTestInstance = new SplitTest(params)
        if (splitTestInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'splitTest.label', default: 'SplitTest'), splitTestInstance.id])}"
            redirect(action: "show", id: splitTestInstance.id)
        }
        else {
            render(view: "create", model: [splitTestInstance: splitTestInstance])
        }
    }

    def show() {
        def splitTestInstance = SplitTest.get(params.id)
        if (!splitTestInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'splitTest.label', default: 'SplitTest'), params.id])}"
            redirect(action: "list")
        }
        else {
            StatisticAnalyzer statisticAnalyzer = splitTestService.getStatisticsForTest(splitTestInstance.id)
            [ splitTestInstance: splitTestInstance, statisticAnalyzer: statisticAnalyzer ]
        }
    }

    def edit() {
        def splitTestInstance = SplitTest.get(params.id)
        if (!splitTestInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'splitTest.label', default: 'SplitTest'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [splitTestInstance: splitTestInstance]
        }
    }

    def update() {
        println params.inspect()
        def splitTestInstance = SplitTest.get(params.id)
        if (splitTestInstance) {
            splitTestInstance.properties = params
            if (!splitTestInstance.hasErrors() && splitTestInstance.save(flush: true)) {
                flash.message = "Split test has been updated successfully"
                redirect(action: "show", id: splitTestInstance.id)
            }
            else {
                render(view: "edit", model: [splitTestInstance: splitTestInstance])
            }
        }
        else {
            flash.message = "Split test was not found with provided ID"
            redirect(action: "list")
        }
    }

    def delete() {
        def splitTestInstance = SplitTest.get(params.id)
        if (splitTestInstance) {
            for (splitTestVariant in splitTestInstance.splitTestVariants) {
                for (splitTestVariantHit in splitTestVariant.splitTestVariantHits) {
                    splitTestVariantHit.delete(flush: true)
                }
                splitTestVariant.delete(flush: true)
            }
            try {
                splitTestInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'splitTest.label', default: 'SplitTest'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'splitTest.label', default: 'SplitTest'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'splitTest.label', default: 'SplitTest'), params.id])}"
            redirect(action: "list")
        }
    }

    def archive() {
        SplitTest originalSplitTest = SplitTest.get(params.id)
        def orig = originalSplitTest.archive()
        flash.message = "Archived split test as ${orig.name}"
        redirect(action: "list")
    }

    def instructions() {

    }
}
