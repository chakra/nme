package com.nme.userservice.repository;

import com.nme.userservice._internal.RestApiConstants;
import com.nme.userservice.entity.Nmeuser;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Roland Krüger
 */
@Repository
public interface UserRepository extends JpaRepository<Nmeuser, Long> {

    Nmeuser findByUsername(@Param(RestApiConstants.USERNAME_PARAM) String username);

    @Query("select u from Nmeuser u where u.username = :username and u.enabled = true ")
    Nmeuser findUserForLogin(@Param(RestApiConstants.USERNAME_PARAM) String username);

    @RestResource(exported = false)
    Nmeuser findByRegistrationConfirmationToken(String registrationConfirmationToken);

    @RestResource(exported = false)
    @Override
    <S extends Nmeuser> S save(S entity);

    @Override
    @RestResource(exported = false)
    List<Nmeuser> findAll();

    @Override
    @RestResource(exported = false)
    List<Nmeuser> findAll(Iterable<Long> iterable);

    @Override
    @RestResource(exported = false)
    List<Nmeuser> findAll(Sort sort);

    @Override
    @RestResource(exported = false)
    <S extends Nmeuser> List<S> save(Iterable<S> iterable);

    @Override
    <S extends Nmeuser> S saveAndFlush(S s);
}
