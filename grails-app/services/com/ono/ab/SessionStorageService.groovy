package com.ono.ab

import javax.servlet.http.HttpSession
import org.springframework.web.context.request.RequestContextHolder

class SessionStorageService {

    static transactional = false
    static scope = "singleton"

    def HttpSession getSession() {
        return RequestContextHolder.currentRequestAttributes().getSession()
    }
}
