package com.viseo.c360.json;



import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers.DateDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.viseo.c360.formation.domain.formation.SessionFormation;
import com.viseo.c360.formation.rest.HelloWorldWS;
import com.viseo.c360.formation.rest.HelloWorldWS.Essai;
import com.viseo.c360.json.deserializer.formation.SessionFormationDeserializer;
import com.viseo.c360.json.serializer.helloworld.MonSerializer;


public class JSONMapper extends ObjectMapper {

    public JSONMapper() {
        SimpleModule module = new SimpleModule("JSONModule", new Version(2, 0, 0, null, null, null));
        module.addSerializer(Essai.class, new MonSerializer());
        module.addDeserializer(SessionFormation.class, new SessionFormationDeserializer());
        // Add more here ...
        registerModule(module);
    }
}