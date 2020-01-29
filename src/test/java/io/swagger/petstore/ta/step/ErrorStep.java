package io.swagger.petstore.ta.step;

import io.restassured.response.ValidatableResponse;
import org.junit.Assert;

import static io.swagger.petstore.ta.util.TestUtil.*;

public class ErrorStep implements Step {
    public ErrorStep() {
        Then("^User get error response with (.*) and (.*)$", (String errorCode, String errorMessage) -> {
            ValidatableResponse response = state.getDataLatest();
            response.assertThat().statusCode(Integer.parseInt(errorCode));
            Assert.assertTrue(errorMessage, extract("message", response).toString().contains(errorMessage));
        });
    }
}
