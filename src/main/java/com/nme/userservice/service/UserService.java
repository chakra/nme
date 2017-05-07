package com.nme.userservice.service;

import com.nme.userservice.entity.Nmeuser;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * @author Roland Krüger
 */
public interface UserService {

    Nmeuser findUserByUsername(String username);

    Nmeuser findByRegistrationConfirmationToken(String confirmationToken);

    List<Nmeuser> getUserList(int page, int size, Sort.Direction sort);

    void delete(Long id);

    Nmeuser save(Nmeuser nmeuser);

    Nmeuser findById(Long userId);
}
