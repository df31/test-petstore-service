package io.swagger.petstore.ta.step;

import io.cucumber.java8.En;
import io.restassured.response.ValidatableResponse;

import java.util.Objects;

public interface Step extends En {
    String PET_STORE_URL = "https://petstore.swagger.io/v2/pet";
    String ADD_PET_ENDPOINT = "pet";

    default <T> T extract(String jsonKey, ValidatableResponse response) {
        T t = response.extract().jsonPath().get(jsonKey);
        Objects.requireNonNull(t, jsonKey + "key not found in " + response.extract().body().asString());
        return t;
    }
}
