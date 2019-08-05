package com.cwh.bbs.controller;

import com.cwh.bbs.pojo.AccessToken;
import com.cwh.bbs.pojo.GitHubUser;
import com.cwh.bbs.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

    @Autowired
    private GitHubProvider gitHubProvider;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping(value = "/callback", produces = "application/json;utf-8")
    public String callback(@RequestParam String code, @RequestParam String state) {
        AccessToken accessToken = new AccessToken();
        accessToken.setClient_id("Iv1.dc39a0b167ac9f23");
        accessToken.setClient_secret("8383263e856164032567d22e0827e64f396b8b6d");
        accessToken.setCode(code);
        accessToken.setRedirect_uri("http://localhost:8888/callback");
        accessToken.setState(state);
        String accessToken1 = gitHubProvider.getAccessToken(accessToken);
        System.out.println(accessToken1);
        GitHubUser user = gitHubProvider.getUser(accessToken1);
        System.out.println(user.getName());
        return "index";
    }
}