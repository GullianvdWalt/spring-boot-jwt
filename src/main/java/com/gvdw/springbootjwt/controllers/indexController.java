package com.gvdw.springbootjwt.controllers;

import com.gvdw.springbootjwt.dtos.AuthRequest;
import com.gvdw.springbootjwt.utilities.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created By Gullian on Apr, 2021
 */
@RestController
public class indexController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/")
    public String welcome(){
        return "Welcome my friend";
    }
    @PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception{
        try {
            // Validate username and password
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword()));
        }catch(Exception e){
            throw new Exception("Invalid username or password");
        }

        // Auth is successful, generate jwt
        return jwtUtil.generateToken(authRequest.getUsername());
    }
}
