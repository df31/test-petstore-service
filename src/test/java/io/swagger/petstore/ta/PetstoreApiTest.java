package io.swagger.petstore.ta;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", glue = "io.swagger.petstore.ta", strict = true)
public class PetstoreApiTest {
}

