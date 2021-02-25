package com.cinherited.gatewayservice.controllers.impl;

import com.cinherited.gatewayservice.clients.LeadClient;
import com.cinherited.gatewayservice.clients.StatsClient;
import com.cinherited.gatewayservice.controllers.interfaces.IGatewayController;
import com.cinherited.gatewayservice.dtos.AuthenticationRequest;
import com.cinherited.gatewayservice.dtos.AuthenticationResponse;
import com.cinherited.gatewayservice.dtos.LeadDTO;
import com.cinherited.gatewayservice.security.MyUserDetailsService;
import com.cinherited.gatewayservice.services.interfaces.ILeadsServices;
import com.cinherited.gatewayservice.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/copernicus")
public class GatewayController implements IGatewayController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private LeadClient leadClient;

    @Autowired
    ILeadsServices leadsServices;

    private static String leadsAuthOk;


    @Override
    @GetMapping("/leads/all")
    public List<LeadDTO> findAllLeads() {
//        authOk = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJmb28iLCJleHAiOjE2MTQwNTY3MzEsImlhdCI6MTYxNDAyMDczMX0.3nP6Vo3WmeuO7X15tdkGL6ACl0UZHuefC4F0dZJScnc";
//        return
        return leadsServices.findAll("Bearer " + leadsAuthOk);
    }

//    @RequestMapping(value = "/leads/authenticate", method = RequestMethod.POST)
//    public void authWithLeadsService(){
//        AuthenticationRequest authenticationRequest = new AuthenticationRequest("foo", "foo");
//        ResponseEntity<?> responseEntity=  leadClient.createAuthenticationToken(authenticationRequest);
//        String auth = Objects.requireNonNull(responseEntity.getBody()).toString();
//        leadsAuthOk = auth.substring(5, auth.length() - 1);
//        System.out.println(auth);
//
//    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }


        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }


    public static String getLeadsAuthOk() {
        return leadsAuthOk;
    }

    public static void setLeadsAuthOk(String theAuthOk) {
        leadsAuthOk = theAuthOk;
    }
}
