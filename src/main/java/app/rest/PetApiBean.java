package app.rest;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ws.rs.core.Response;

import demo.app.rest.api.V1Api;
import demo.app.rest.api.V2Api;
import demo.app.rest.model.PetV1;
import demo.app.rest.model.PetV2;

@Singleton
public class PetApiBean implements V1Api, V2Api {

    private Map<String, PetV2> pets = new HashMap<>();

    @PostConstruct
    private void init() {
        PetV2 dog = new PetV2();
        dog.setId(1L);
        dog.setName("Fido");
        dog.setTag("dog");
        dog.setIsDoge(true);
        pets.put(String.valueOf(1L), dog);

        PetV2 cow = new PetV2();
        cow.setId(2L);
        cow.setName("Jagoda");
        cow.setTag("cow");
        cow.setIsDoge(false);
        pets.put(String.valueOf(2L), cow);
    }


    public Response showPetByIdV1(String petId) {
        return Response.ok(getPetV1(petId)).build();
    }

    public Response showPetByIdV2(String petId) {
        return Response.ok(getPetV2(petId)).build();
    }

    private PetV2 getPetV2(final String petId) {
        return Optional.ofNullable(pets.get(petId)).orElse(new PetV2());
    }

    private PetV1 getPetV1(final String petId){
        final PetV2 petV2 = getPetV2(petId);
        PetV1 petV1 = new PetV1();
        petV1.setId(petV2.getId());
        petV1.setName(petV2.getName());
        petV1.setTag(petV2.getTag());
        return petV1;

    }

}
