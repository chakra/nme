package com.nme.userservice.controller;

import com.nme.userservice._internal.RestApiConstants;
import com.nme.userservice.entity.Nmeuser;
import com.nme.userservice.model.UserApiData;
import com.nme.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * @author Roland Krüger
 */
@RestController
@RequestMapping(RestApiConstants.LOGIN_USER_RESOURCE)
public class UserLoginRestController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "{userId}", method = RequestMethod.POST)
    public ResponseEntity loginUser(@PathVariable Long userId) {
        Nmeuser nmeuser = userService.findById(userId);

        if (nmeuser == null ||
                !nmeuser.isEnabled() ||
                !nmeuser.isAccountNonExpired() ||
                !nmeuser.isAccountNonLocked() ||
                !nmeuser.isCredentialsNonExpired()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        nmeuser.setLastLogin(LocalDateTime.now());
        userService.save(nmeuser);

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity loginUser(@RequestBody UserApiData userApiData) {
        //User user = userService.findById(userId);
        Nmeuser user = userService.findUserByUsername(userApiData.getUsername());

        if (user == null ||
                !user.isEnabled() ||
                !user.isAccountNonExpired() ||
                !user.isAccountNonLocked() ||
                !user.isCredentialsNonExpired()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        user.setLastLogin(LocalDateTime.now());
        userService.save(user);

        return new ResponseEntity(HttpStatus.OK);
    }



}
