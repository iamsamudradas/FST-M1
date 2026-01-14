package project;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static org.hamcrest.Matchers.*;


public class FTSproject {
	
	// Declare request specification
		RequestSpecification requestSpec;
		// Declare response specification
		ResponseSpecification responseSpec;
		
		//Holds SSH public Key
		String SSH = "ssh-ed25519 AAAAC3NzaC1lZDI1NTE5AAAAIOnYvfzpYl++dDvghOOtK4JvxqUVaXsMwgxjDM5ZcSWq azuread\\\\samudradas@IBM-BJPKC64";
		
		// Holds generated key id
	    int keyId;
	    
		@BeforeClass
		public void setUp() {
			// Create request specification
			requestSpec = new RequestSpecBuilder()
				// Set content type
				.addHeader("Authorization", "Bearer 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")
				// Set base URL
				.setBaseUri("https://api.github.com")
				// Build request specification
				.build();
		}
		
		// ---------------- POST ----------------
	    @Test(priority = 1)
	    public void addSSHKey() {

	        String requestBody = "{\n" +
	                "  \"title\": \"TestAPIKey\",\n" +
	                "  \"key\": \"" + SSH + "\"\n" +
	                "}";

	        Response response =
	                given()
	                    .spec(requestSpec)
	                    .body(requestBody)
	                .when()
	                    .post("/user/keys")
	                    .then()
	                    .statusCode(201)
	                    .body("id", notNullValue())
	                    .extract()
	                    .response();

	        keyId = response.path("id");
	        System.out.println(keyId);

	        Reporter.log("Created SSH Key ID: " + keyId, true);
	    }
	    
	 // ---------------- GET ----------------
	    @Test(priority = 2)
	    public void getSSHKeyById() {

	        Response response =
	                given()
	                    .spec(requestSpec)
	                    .pathParam("keyId", keyId)
	                .when()
	                    .get("/user/keys/{keyId}")
	                .then()
	                    .statusCode(200)
	                    .body("id", equalTo(keyId))
	                    .body("title", equalTo("TestAPIKey"))
	                    .extract()
	                    .response();

	        Reporter.log("GET Response: " + response.asPrettyString(), true);
	    }
	    
	 // ---------------- DELETE ----------------
	    @Test(priority = 3)
	    public void deleteSSHKey() {

	        Response response =
	                given()
	                    .spec(requestSpec)
	                    .pathParam("keyId", keyId)
	                .when()
	                    .delete("/user/keys/{keyId}")
	                .then()
	                    .statusCode(204)
	                    .extract()
	                    .response();

	        Reporter.log("Deleted SSH Key ID: " + keyId, true);
	    }

}
