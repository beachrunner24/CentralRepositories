package com.logisticare.oauth.tester.service

import com.logisticare.oauth.tester.Accessor
import groovy.json.JsonOutput
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.stereotype.Component
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.client.HttpStatusCodeException
import org.springframework.web.client.RestTemplate

@Component
@Slf4j
class OAuthService {

    @Autowired
    Accessor accessor
    @Autowired
    RestTemplate restTemplate

    String oAuthResponse(String code) {
        def authorizationString = StaticValues.BasicHeader("${StaticValues.clientId}:${StaticValues.clientSecret}")
        log.debug("Auth String: ${authorizationString}")

        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>()
        map.add("grant_type", accessor.grantType)
        map.add("code", code)
        map.add("redirect_uri", System.getenv("redirectUri"))

        try {
            def request = new HttpEntity<MultiValueMap<String, String>>(map, StaticValues.CreateHttpHeaders(authorizationString))
            log.debug("Token URL: " + System.getenv("tokenUrl"))
            return restTemplate.postForEntity(System.getenv("tokenUrl"), request, String).body
        }
        catch (HttpStatusCodeException ex) {
            log.error("HttpStatusCodeException exception for oauth token: ${ex.statusCode}: ${ex.responseBodyAsString} - ${ex.getMessage()}", ex)
            return JsonOutput.toJson([httpStatusCode: "${ex.statusCode}",message: "${ex.responseBodyAsString}"])
        }
    }
}