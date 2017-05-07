package com.nme.userservice.entity;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chakrabhandari on 8/01/2017.
 */
public class CustomListSerializer extends StdSerializer<List<Authority>> {

    public CustomListSerializer() {
        this(null);
    }

    public CustomListSerializer(Class<List> t) {
        super((java.lang.Class)t);
    }

    @Override
    public void serialize(List<Authority> authorities, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        List<Long> ids = new ArrayList<>();
        for (Authority authority : authorities) {
            ids.add(authority.getId());
        }
        jsonGenerator.writeObject(ids);
    }
}
