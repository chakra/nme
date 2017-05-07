package com.nme.userservice.model;

import com.nme.userservice._internal.model.AbstractBaseApiData;
import com.nme.userservice.resources.EmptyResource;
import org.springframework.hateoas.Link;

/**
 * Empty API data class with no data fields. Used as dummy data type by {@link EmptyResource}s.
 *
 * @author Roland Krüger
 * @see EmptyResource
 */
public class EmptyApiData extends AbstractBaseApiData<EmptyResource> {
    @Override
    protected EmptyResource createNewResource(Link self) {
        return new EmptyResource(self);
    }
}
