package com.ono.ab

class SplitTestAuthController {
    def grailsApplication

    def login() {
        if (session.splitTestAuthorized) {
            redirect(controller: 'splitTest')
        }
    }

    def authenticate() {
        println params.inspect()

        def username = grailsApplication.config.grails.plugins.splittest.username
        def password = grailsApplication.config.grails.plugins.splittest.password

        println username

        if (params.username == username && params.password == password) {
            session.splitTestAuthorized = true
            redirect(controller: 'splitTest')
        }
        else {
            flash.message = 'Invalid username and/or password'
            render view: 'login', model: [username: params.username]
        }
    }

    def logout() {
        session.splitTestAuthorized = null
        flash.message = 'Logged out successfully'
        redirect(action: 'login')
    }
}
