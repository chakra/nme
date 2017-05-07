package com.nme.userservice.service;

import com.nme.userservice.entity.Nmeuser;
import com.nme.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Roland Krüger
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public Nmeuser findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Nmeuser findByRegistrationConfirmationToken(String confirmationToken) {
        return userRepository.findByRegistrationConfirmationToken(confirmationToken);
    }

    @Override
    public List<Nmeuser> getUserList(int page, int size, Sort.Direction sort) {
        return userRepository.findAll(new PageRequest(page, size, sort, "username")).getContent();
    }

    @Override
    public void delete(Long id) {
        userRepository.delete(id);
    }

    @Override
    public Nmeuser save(Nmeuser nmeuser) {
        return userRepository.save(nmeuser);
    }

    @Override
    public Nmeuser findById(Long userId) {
        return userRepository.findOne(userId);
    }
}
