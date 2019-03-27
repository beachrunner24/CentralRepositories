package com.logisticare.oauth.tester

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class Accessor {
    @Value('${oauth.clientId}')
    String clientId
    @Value('${oauth.clientSecret}')
    String clientSecret
    @Value('${oauth.refreshToken}')
    String refreshToken
    @Value('${oauth.grantType}')
    String grantType
}