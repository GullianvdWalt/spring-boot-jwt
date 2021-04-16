package com.gvdw.springbootjwt.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created By Gullian on Apr, 2021
 */
@RestController
public class indexController {
    @GetMapping("/")
    public String welcome(){
        return "Welcome my friend";
    }
}
