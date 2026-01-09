package Activity;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class Activity2 {

    private static final String BASE_URI = "https://petstore.swagger.io/v2";

    @Test(priority = 1)
    public void addNewUserFromFile() {
        Response response = given()
                .baseUri(BASE_URI)
                .header("Content-Type", "application/json")
                .body(new File("src/test/resources/userInfo.json"))
                .when()
                .post("/user");

        response.then()
                .statusCode(200)
                .body("code", equalTo(200))
                .body("message", equalTo("6059"));
    }

    @Test(priority = 2)
    public void getUserInfo() throws IOException {
        File outputJSON = new File("src/test/java/Activity/userGETResponse.json");

        Response response = given()
                .baseUri(BASE_URI)
                .pathParam("username", "samudra")
                .when()
                .get("/user/{username}");

        // Write response to file
        FileWriter writer = new FileWriter(outputJSON);
        writer.write(response.getBody().asPrettyString());
        writer.close();

        response.then()
                .statusCode(200)
                .body("id", equalTo(6059))
                .body("username", equalTo("samudra"))
                .body("firstName", equalTo("Justin"))
                .body("lastName", equalTo("Case"))
                .body("email", equalTo("justincase@mail.com"))
                .body("phone", equalTo("9812763450"));
    }

    @Test(priority = 3)
    public void deleteUser() {
        Response response = given()
                .baseUri(BASE_URI)
                .pathParam("username", "samudra")
                .when()
                .delete("/user/{username}");

        response.then()
                .statusCode(200)
                .body("code", equalTo(200))
                .body("message", equalTo("samudra"));
    }
}
