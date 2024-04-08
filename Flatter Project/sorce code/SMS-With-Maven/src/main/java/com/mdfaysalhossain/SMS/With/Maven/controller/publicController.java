package com.mdfaysalhossain.SMS.With.Maven.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class publicController {


    @GetMapping("public")
    public String home(){


        return  "publicHead";
    }

}
