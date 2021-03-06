package com.nme.userservice.service;

import com.google.common.base.Preconditions;
import com.nme.userservice.entity.Authority;
import com.nme.userservice.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Roland Krüger
 */
@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    AuthorityRepository authorityRepository;

    @Override
    public Authority findByAuthority(String authority) {
        return authorityRepository.findByAuthority(authority);
    }

    @Override
    public List<Authority> getAuthorityList(int page, int size, Sort.Direction sort) {
        return authorityRepository.findAll(new PageRequest(page, size, sort, "authority")).getContent();
    }

    @Override
    public Authority create(Authority authority) {
        return authorityRepository.save(authority);
    }

    @Override
    public Authority update(Authority authority) {
        Preconditions.checkArgument(authority.getId() != null, "given Authority object has no id");
        Preconditions.checkArgument(authorityRepository.exists(authority.getId()), "given Authority does not exist");
        return authorityRepository.save(authority);
    }

    @Override
    public void delete(Long authorityId) {
        authorityRepository.delete(authorityId);
    }

    @Autowired
    public void setAuthorityRepository(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }
}
