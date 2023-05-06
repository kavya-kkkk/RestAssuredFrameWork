package getrequest;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Putmethod {

  @Test
  public void updateUser() {
    String requestBody = "{\"name\": \"morpheus\",\"job\": \"zion resident\"}";
    given()
      .contentType("application/json")
      .body(requestBody)
    .when()
      .put("https://reqres.in/api/users/3")
    .then()
      .statusCode(200)
      .assertThat()
      .body("name", equalTo("John Smith"))
      .body("email", equalTo("john.smith@example.com"));
  }
}
