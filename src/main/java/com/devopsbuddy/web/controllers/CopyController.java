package com.devopsbuddy.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Rene on 1/04/2017.
 */
@Controller
public class CopyController {

    @RequestMapping("/about")
    public String about(){
        return "copy/about";
    }

}
