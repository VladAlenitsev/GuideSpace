package com.guidespace.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        if (userService.authenticate(username, password)) {
            List<GrantedAuthority> grantedAuths = new ArrayList<>();
            if(userService.getUser(username).getUser_role_id()==1){
                grantedAuths.add(new SimpleGrantedAuthority("UNVERIFIED"));
                return new UsernamePasswordAuthenticationToken(authentication.getName(), authentication.getCredentials(), grantedAuths);
            }
            else if(userService.getUser(username).getUser_role_id()==2){
                grantedAuths.add(new SimpleGrantedAuthority("ADMIN"));
                return new UsernamePasswordAuthenticationToken(authentication.getName(), authentication.getCredentials(), grantedAuths);
            }
            else if(userService.getUser(username).getUser_role_id()==4){
                grantedAuths.add(new SimpleGrantedAuthority("VERIFIED"));
                return new UsernamePasswordAuthenticationToken(authentication.getName(), authentication.getCredentials(), grantedAuths);
            }
            else if(userService.getUser(username).getUser_role_id()==6){
                grantedAuths.add(new SimpleGrantedAuthority("INFOADDER"));
                return new UsernamePasswordAuthenticationToken(authentication.getName(), authentication.getCredentials(), grantedAuths);
            }
            else {
                grantedAuths.add(new SimpleGrantedAuthority("UNVERIFIED"));
                return new UsernamePasswordAuthenticationToken(authentication.getName(), authentication.getCredentials(), grantedAuths);
            }
        } else {
            return null;
        }
//        List<GrantedAuthority> grantedAuths = new ArrayList<>();
//        grantedAuths.add(new SimpleGrantedAuthority("USER"));
//        return new UsernamePasswordAuthenticationToken(authentication.getName(), authentication.getCredentials(), grantedAuths);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(aClass);
    }

}
