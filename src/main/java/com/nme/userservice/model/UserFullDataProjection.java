package com.nme.userservice.model;

import com.nme.userservice.entity.Authority;
import com.nme.userservice.entity.Nmeuser;
import com.nme.userservice.enums.UserProjections;
import org.springframework.data.rest.core.config.Projection;
import org.springframework.hateoas.Identifiable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

/**
 * @author Roland Krüger
 */
@Projection(name = UserProjections.Values.USER_FULL_DATA, types = Nmeuser.class)
public interface UserFullDataProjection extends Identifiable<Long> {

    boolean isAccountNonExpired();

    boolean isAccountNonLocked();

    boolean isCredentialsNonExpired();

    boolean isEnabled();

    String getUsername();

    String getRememberMeToken();

    String getRegistrationConfirmationToken();

    LocalDate getRegistrationDate();

    LocalDateTime getLastLogin();

    String getEmail();

    String getPassword();

    Collection<Authority> getAuthorities();
}
