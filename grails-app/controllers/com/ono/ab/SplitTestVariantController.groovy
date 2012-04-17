package com.ono.ab

class SplitTestVariantController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        redirect(controller: "splitTest", action: "list")
    }

    def create() {
        def splitTestVariantInstance = new SplitTestVariant()
        if (params.id) {
            def splitTest = SplitTest.findById(params.id)
            splitTestVariantInstance.splitTest = splitTest
        }
        splitTestVariantInstance.properties = params
        return [splitTestVariantInstance: splitTestVariantInstance]
    }

    def save() {
        def splitTestVariantInstance = new SplitTestVariant(params)
        if (splitTestVariantInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'splitTestVariant.label', default: 'SplitTestVariant'), splitTestVariantInstance.id])}"
            redirect(controller: "splitTest", action: "show", id: splitTestVariantInstance?.splitTest?.id)
        }
        else {
            render(view: "create", model: [splitTestVariantInstance: splitTestVariantInstance])
        }
    }

    def show() {
        def splitTestVariantInstance = SplitTestVariant.get(params.id)
        if (!splitTestVariantInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'splitTestVariant.label', default: 'SplitTestVariant'), params.id])}"
            redirect(action: "list")
        }
        else {
            [splitTestVariantInstance: splitTestVariantInstance]
        }
    }

    def edit() {
        def splitTestVariantInstance = SplitTestVariant.get(params.id)
        if (!splitTestVariantInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'splitTestVariant.label', default: 'SplitTestVariant'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [splitTestVariantInstance: splitTestVariantInstance]
        }
    }

    def update() {
        def splitTestVariantInstance = SplitTestVariant.get(params.id)
        if (splitTestVariantInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (splitTestVariantInstance.version > version) {

                    splitTestVariantInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'splitTestVariant.label', default: 'SplitTestVariant')] as Object[], "Another user has updated this SplitTestVariant while you were editing")
                    render(view: "edit", model: [splitTestVariantInstance: splitTestVariantInstance])
                    return
                }
            }
            splitTestVariantInstance.properties = params
            if (!splitTestVariantInstance.hasErrors() && splitTestVariantInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'splitTestVariant.label', default: 'SplitTestVariant'), splitTestVariantInstance.id])}"
                redirect(action: "show", id: splitTestVariantInstance.id)
            }
            else {
                render(view: "edit", model: [splitTestVariantInstance: splitTestVariantInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'splitTestVariant.label', default: 'SplitTestVariant'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete() {
        def splitTestVariantInstance = SplitTestVariant.get(params.id)
        if (splitTestVariantInstance) {
            try {
                splitTestVariantInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'splitTestVariant.label', default: 'SplitTestVariant'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'splitTestVariant.label', default: 'SplitTestVariant'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'splitTestVariant.label', default: 'SplitTestVariant'), params.id])}"
            redirect(action: "list")
        }
    }

}
