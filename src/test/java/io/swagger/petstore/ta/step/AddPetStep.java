package io.swagger.petstore.ta.step;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.swagger.petstore.ta.model.PetTypes;
import net.minidev.json.JSONObject;

import java.util.*;

import static io.swagger.petstore.ta.util.TestUtil.*;

public class AddPetStep implements Step {

    public AddPetStep() {
        Given("^User is in petstore swagger page$", () -> {
            state.setData("PetstoreUrl", PET_STORE_URL, true);
        });

        When("^User add a new (.*)$", (PetTypes pet) -> {
            state.setData("Pet",pet,true);
            ValidatableResponse response = addPetRequest(state.getData("PetstoreUrl"), createPetInfo(pet).toJSONString());
            state.setData("addPetResponse", response, true);
        });

        Then("^The pet is added in store successfully$", () -> {
            ValidatableResponse response = state.getDataLatest();
            response.assertThat().statusCode(200).and().contentType(ContentType.JSON);
        });
    }
}
