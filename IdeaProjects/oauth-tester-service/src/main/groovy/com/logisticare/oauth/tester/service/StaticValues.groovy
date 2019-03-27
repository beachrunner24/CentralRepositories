package com.logisticare.oauth.tester.service

import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType

class StaticValues {
    public static String scopes = "trip_r+trip_u+trip_cancel+trip_reroute+driver_r+driver_w+vehicle_r+atms+brokerclient_r+callcenter_r+vdps_r+client"
    public static String clientId="d534d089-5210-4d47-b150-7de2c8267658"
    public static String clientSecret = "90bf3fe4-0e6e-463a-988a-0b118765ef2c"
    private final static String AuthHeader = "Authorization"

    static String BasicHeader(String authString) {
        "Basic ${authString.bytes.encodeBase64().toString()}"
    }

    static String OAuth_URL(uri, redirectUri){
        "${uri}?" + "response_type=code&client_id=${clientId}&scope=${scopes}&redirect_uri=${redirectUri}"
    }

    static HttpHeaders CreateHttpHeaders(String authHeader) {
        def headers = new HttpHeaders()
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED)
        headers.set(AuthHeader, authHeader)
        return headers
    }
}
