package com.nme.userservice.model;

import com.nme.userservice._internal.RestApiConstants;
import com.nme.userservice.controller.UserRegistrationRestController;
import com.nme.userservice.entity.Nmeuser;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

/**
 * @author Roland Krüger
 */
public class UserRegistrationResource extends ResourceSupport {

    private String username;
    private String email;
    private String registrationConfirmationToken;

    protected UserRegistrationResource() {
    }

    public UserRegistrationResource(Nmeuser nmeuser) {
        username = nmeuser.getUsername();
        email = nmeuser.getEmail();
        registrationConfirmationToken = nmeuser.getRegistrationConfirmationToken();

        add(ControllerLinkBuilder.linkTo(UserRegistrationRestController.class).slash(nmeuser
                .getRegistrationConfirmationToken()).slash(RestApiConstants.CONFIRM).withRel(RestApiConstants.CONFIRM));
    }

    public UserRegistrationResource(UserRegistrationApiData userRegistration) {
        this.username = userRegistration.getUsername();
        this.email = userRegistration.getEmail();
        this.registrationConfirmationToken = userRegistration.getRegistrationConfirmationToken();
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getRegistrationConfirmationToken() {
        return registrationConfirmationToken;
    }

    public void setRegistrationConfirmationToken(String registrationConfirmationToken) {
        this.registrationConfirmationToken = registrationConfirmationToken;
    }
}
