package io.swagger.petstore.ta.step;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.swagger.petstore.ta.util.TestUtil;
import net.minidev.json.JSONObject;
import org.junit.Assert;

import static io.swagger.petstore.ta.util.TestUtil.*;

public class FindPetStep implements Step {

    public FindPetStep() {
        When("^User search a pet which id is (.*)$", (String id) -> {
            state.setData("PetId", id, true);
            ValidatableResponse response = TestUtil.findPetByIdRequest(state.getData("PetstoreUrl"), id);
            state.setData("FindPetByIdResponse", response, true);
        });

        Then("The pet is found and search result returned successfully", () -> {
            ValidatableResponse response = state.getDataLatest();
            response.assertThat().statusCode(200);
            response.assertThat().contentType(ContentType.JSON);
        });

        And("^The pet id are same$", () -> {
            JSONObject petData = state.getData("PetData");
            ValidatableResponse response = state.getDataLatest();
            Assert.assertEquals(petData.get("id"), extract("id", response));
        });

    }
}
