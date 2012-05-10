package com.ono.ab

class SecurityFilters {
    def filters = {
        basicAuth(controller:'(splitTest|splitTestVariant)', action:'*') {
            before = {
                if (!session.splitTestAuthorized) {
                    redirect(controller: 'splitTestAuth', action: 'login')
                }
            }
        }
    }
}
