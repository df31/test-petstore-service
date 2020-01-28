package io.swagger.petstore.ta.step;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.swagger.petstore.ta.model.PetTypes;
import io.swagger.petstore.ta.util.TestUtil;
import org.junit.Assert;

import static io.swagger.petstore.ta.util.TestUtil.*;

public class FindPetStep implements Step {

    public FindPetStep() {
        When("^User search a pet by (.*)$", (String id) -> {
            state.setData("PetId", id, true);
            ValidatableResponse response = TestUtil.findPetByIdRequest(state.getData("PetstoreUrl"), id);
            state.setData("FindPetByIdResponse", response, true);
        });

        When("^User search the newly added pet$", () -> {
            PetTypes pet = state.getData("Pet");
            ValidatableResponse response = TestUtil.findPetByIdRequest(state.getData("PetstoreUrl"), pet.getPetId());
        });

        Then("The pet is found and search result returned successfully", () -> {
            ValidatableResponse response = state.getDataLatest();
            response.assertThat().statusCode(200);
            response.assertThat().contentType(ContentType.JSON);
        });

        And("^The pet id are same$", () -> {
            PetTypes pet = state.getData("Pet");
            ValidatableResponse response = state.getDataLatest();
            Assert.assertEquals(pet.getPetId(), extract("id", response).toString());
        });

    }
}
