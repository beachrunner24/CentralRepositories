package com.logisticare.oauth.tester.controller

import com.logisticare.oauth.tester.service.OAuthService
import com.logisticare.oauth.tester.service.StaticValues
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.*

@Slf4j
@Controller
class OAuthController {
    @Autowired
    OAuthService oAuthService

    @GetMapping("/")
    String getIndexPage(ModelMap model) {
        def uri = System.getenv("authorizationUrl")
        def redirectUri = System.getenv("redirectUri")
        log.debug("auth url: " + StaticValues.OAuth_URL(uri, redirectUri))
        model.addAttribute("uri",StaticValues.OAuth_URL(uri, redirectUri))
        return "index"
    }

    @RequestMapping(method = RequestMethod.GET, value = "/oauth/callback", produces = "application/json")
    String callBack(@RequestParam("code") String code, Model model) {
        log.info("code: ${code}")
        model.addAttribute("pre",oAuthService.oAuthResponse(code))
        return "oauth"
    }
}