package com.abel.airtimevtu.controller;

import com.abel.airtimevtu.model.AirtimeRequest;
import com.abel.airtimevtu.model.AirtimeResponse;
import com.abel.airtimevtu.model.AppResponse;
import com.abel.airtimevtu.model.JwtRequest;
import com.abel.airtimevtu.model.JwtResponse;
import com.abel.airtimevtu.service.AirtimeVtuService;
import com.abel.airtimevtu.utility.JWTUtility;
import com.abel.airtimevtu.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class HomeController {

    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private AirtimeVtuService airtimeVtuService;

    private final HttpServletRequest httpServletRequest;

    @GetMapping("/")
    public String home() {
        return "Welcome to Xpress Payments!!";
    }

    @PostMapping("/airtime/fulfil")
    public AppResponse<AirtimeResponse> airtime(@RequestBody AirtimeRequest airtimeRequest) throws IOException {


       return  airtimeVtuService.fulfil(airtimeRequest);

    }

    @PostMapping("/authenticate")
    public JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) throws Exception{

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getUsername(),
                            jwtRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }

        final UserDetails userDetails
                = userService.loadUserByUsername(jwtRequest.getUsername());

        final String token =
                jwtUtility.generateToken(userDetails);

        return new JwtResponse(token);
    }
}
