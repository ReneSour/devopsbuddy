package com.devopsbuddy.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Rene on 15/07/2017.
 */
@Controller
public class LoginController {
    public static final String LOGIN_VIEW_NAME = "user/login";

    @GetMapping(value = "/login")
    public String login() {
        return LOGIN_VIEW_NAME;
    }

}

