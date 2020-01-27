package io.swagger.petstore.ta.step;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.minidev.json.JSONObject;

import java.util.*;
import java.util.stream.Stream;

import static io.swagger.petstore.ta.util.TestUtil.*;

public class AddPetStep implements Step {

    public AddPetStep() {
        Given("^User is in petstore swagger page$", () -> {
            state.setData("PetstoreUrl", PET_STORE_URL, true);
        });

        When("^User create a new pet$", () -> {

            List<String> pictureUrls = Arrays.asList("photo_in_twitter", "photo_in_facebook", "photo_in_google_drive");

            Map<Integer, String> tags = new HashMap<>();
            tags.put(0, "happy hour");
            tags.put(1, "meet with friends");
            tags.put(2, "waiting for walk");

            JSONObject petInfo = createPetInfo(103, "WonWon", 1, "Dog", pictureUrls, (HashMap<Integer, String>) tags);
            state.setData("PetData", petInfo, true);
            ValidatableResponse response = addPetRequest(state.getData("PetstoreUrl"), petInfo.toJSONString());
            state.setData("addPetResponse", response, true);
        });

        Then("^The pet is added in store successfully$", () -> {
            ValidatableResponse response = state.getDataLatest();
            response.assertThat().statusCode(200).and().contentType(ContentType.JSON);
        });
    }
}
