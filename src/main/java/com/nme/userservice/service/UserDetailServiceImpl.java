package com.nme.userservice.service;

import com.google.common.base.Preconditions;
import com.nme.userservice.controller.UserServiceAPI;
import com.nme.userservice.enums.UserProjections;
import com.nme.userservice.model.UserApiData;
import com.nme.userservice.resources.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.util.Optional;

/**
 * Implementation of the {@link UserDetailsService} interface which accesses the user micro-service to obtain {@link UserDetails}
 * objects. The user details services uses a {@link UserMicroserviceEndpointProvider} instance to get the user service's
 * endpoint URL.
 *
 * @author Roland Krüger
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private UserMicroserviceEndpointProvider endpointProvider;

    /**
     * Constructor which is autowired with an instance of {@link UserMicroserviceEndpointProvider}.
     */
    @Autowired
    public UserDetailServiceImpl(UserMicroserviceEndpointProvider endpointProvider) {
        Preconditions.checkNotNull(endpointProvider);
        this.endpointProvider = endpointProvider;
    }

    /**
     * Load a {@link UserDetails} object from the user micro-service for the specified username. Note that this method
     * in addition to a {@link UsernameNotFoundException} throws an unchecked {@link RestClientException} to indicate
     * errors that occurred during the communication process with the remote user micro-service.
     *
     * @param username name of the user to be loaded
     * @return a {@link UserDetails} object
     * @throws UsernameNotFoundException when the given username is not known to the service
     * @throws RestClientException when some error occurred while calling the REST service
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, RestClientException {
        com.nme.userservice.resources.UserService userService = UserServiceAPI.init(endpointProvider.getUserMicroserviceEndpoint());
        Optional<UserApiData> loadedUser = userService.users().search().findByUsername(username).getData().stream().findFirst();
        if (!loadedUser.isPresent()) {
            throw new UsernameNotFoundException("Nmeuser " + username + " not found");
        }
        return loadedUser.get().getResource().useProjection(UserProjections.FULL_DATA).read();
    }
}
