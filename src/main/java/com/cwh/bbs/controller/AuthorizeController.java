package com.cwh.bbs.controller;

import com.cwh.bbs.dto.AccessToken;
import com.cwh.bbs.dto.GitHubUser;
import com.cwh.bbs.mapper.UserMapper;
import com.cwh.bbs.pojo.User;
import com.cwh.bbs.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthorizeController {

    @Autowired
    private GitHubProvider gitHubProvider;

    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.url}")
    private String redirectUrl;

    @Resource
    private UserMapper userMapper;

    @GetMapping(value = "/callback", produces = "application/json;utf-8")
    public String callback(@RequestParam String code, @RequestParam String state, HttpServletResponse response) {
        AccessToken accessToken = new AccessToken();
        accessToken.setClient_id(clientId);
        accessToken.setClient_secret(clientSecret);
        accessToken.setCode(code);
        accessToken.setRedirect_uri(redirectUrl);
        accessToken.setState(state);
        String accessToken1 = gitHubProvider.getAccessToken(accessToken);
        GitHubUser user = gitHubProvider.getUser(accessToken1);
        if (user != null) {
            User user1 = new User();
            //生成token
            String token = UUID.randomUUID().toString();
            //将用户的数据插入的数据库中
            user1.setToken(token);
            user1.setName(user.getName());
            user1.setAccount_id(String.valueOf(user.getId()));
            user1.setCreate_time(System.currentTimeMillis());
            user1.setCreate_modified(user1.getCreate_modified());
            userMapper.insert(user1);
            //将token添加到cookie中
            response.addCookie(new Cookie("token", token));
            return "redirect:/";
        } else {
            //登录失败
            return "redirect:/";
        }
    }
}
