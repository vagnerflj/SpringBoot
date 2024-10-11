package com.example.demo.integrationsTests.testContainers.swagger;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.demo.config.TestConfig;
import com.example.demo.integrationsTests.testContainers.AbstractIntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class SwaggerIntegrationTest  extends AbstractIntegrationTest {

	@Test
	@DisplayName("JUnit test Shoul Display Swagger Ui Page")
	void testShoulDisplaySwaggerUiPage() {
		 var content = given()
				.basePath("/swagger-ui/index.html")
				.port(TestConfig.SERVER_PORT)
				.when()
				.get()
				.then()
				.statusCode(200)
				.extract().body().asString();

		 assertTrue(content.contains("Swagger-ui"));
	}

}
