package com.guidespace.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Leonid29 on 22.09.2016.
 */

@Controller
public class AppController {


    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello Boys!";
    }
}
