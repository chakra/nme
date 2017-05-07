package com.nme.userservice.repository;

import com.nme.userservice._internal.RestApiConstants;
import com.nme.userservice.entity.Authority;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends PagingAndSortingRepository<Authority, Long> {
    Authority findByAuthority(@Param(RestApiConstants.AUTHORITY_PARAM) String authority);

    @RestResource(exported = false)
    @Override Iterable<Authority> findAll();
}
