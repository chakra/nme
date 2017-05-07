package com.nme.userservice.controller;

import com.google.common.base.Preconditions;
import com.nme.userservice._internal.RestApiConstants;
import com.nme.userservice.entity.Nmeuser;
import com.nme.userservice.model.UserApiData;
import com.nme.userservice.service.AuthorityService;
import com.nme.userservice.service.UserService;
import com.nme.userservice.service.exception.UserNotFoundException;
import com.nme.userservice.service.exception.UsernameAlreadyInUseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import static com.google.common.base.MoreObjects.firstNonNull;
import static com.google.common.base.Strings.isNullOrEmpty;
import static org.springframework.http.HttpStatus.OK;

/**
 * @author Roland Krüger
 */
@RestController
@ExposesResourceFor(Nmeuser.class)
@RequestMapping(RestApiConstants.UPDATE_USER_RESOURCE)
public class UpdateUserRestController {

    @Autowired
    private UserService userService;

    @Autowired
    AuthorityService authorityService;

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<Nmeuser> updateUser(@PathVariable Long id, @RequestBody final UserApiData user) {
        Preconditions.checkNotNull(user);
        final Nmeuser nmeuserFromDB = loadUpdatedUserFromDatabase(id);

        if (usernameUpdateRequested(user, nmeuserFromDB)) {
            if (user.getUsername() != null && usernameIsAvailable(user)) {
                nmeuserFromDB.setUsername(user.getUsername());
            } else {
                throw new UsernameAlreadyInUseException();
            }
        }

        nmeuserFromDB.setAccountNonExpired(user.isAccountNonExpired());
        nmeuserFromDB.setAccountNonLocked(user.isAccountNonLocked());
        nmeuserFromDB.setCredentialsNonExpired(user.isCredentialsNonExpired());
        nmeuserFromDB.setEmail(user.getEmail());
        nmeuserFromDB.setEnabled(user.isEnabled());
        nmeuserFromDB.setRememberMeToken(user.getRememberMeToken());

        nmeuserFromDB.getAuthorities().clear();
        nmeuserFromDB.getAuthorities().addAll(
                user.getAuthorities()
                        .stream()
                        .map(authority -> authorityService.findByAuthority(authority.getAuthority()))
                        .filter(authority -> authority != null)
                        .distinct()
                        .collect(Collectors.toList()));

        if (!isNullOrEmpty(firstNonNull(user.getPassword(), ""))) {
            nmeuserFromDB.setUnencryptedPassword(user.getPassword());
        }

        nmeuserFromDB.setLastModified(LocalDateTime.now());

        return new ResponseEntity<>(userService.save(nmeuserFromDB), OK);
    }

    private Nmeuser loadUpdatedUserFromDatabase(Long userId) {
        final Nmeuser nmeuserFromDB = userService.findById(userId);
        if (nmeuserFromDB == null) {
            throw new UserNotFoundException("Cannot update user with ID " + userId + ". Nmeuser does not exist.");
        }
        return nmeuserFromDB;
    }

    private boolean usernameIsAvailable(UserApiData user) {
        return userService.findUserByUsername(user.getUsername()) == null;
    }

    private boolean usernameUpdateRequested(UserApiData user, Nmeuser nmeuserFromDB) {
        return !nmeuserFromDB.getUsername().equals(user.getUsername());
    }
}
