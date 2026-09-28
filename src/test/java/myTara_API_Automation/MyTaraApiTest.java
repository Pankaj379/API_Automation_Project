package myTara_API_Automation;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import base.BaseTest;
import config.ConfigReader;
import endpoints.Endpoints;
import io.restassured.response.Response;
import payload.FamilyMemberPayload;
import payload.LoginPayload;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MyTaraApiTest extends BaseTest {

    private String accessToken;
    private int patientId;
    private int familyMemberId;


    // ==========================================
    // LOGIN
    // ==========================================

    @BeforeClass
    public void login() {

        LoginPayload loginPayload =
                new LoginPayload(
                        ConfigReader.get("username"),
                        ConfigReader.get("password")
                );

        Response response =
                given()
                    .contentType("application/json")
                    .body(loginPayload)
                .when()
                    .post(Endpoints.LOGIN)
                .then()
                    .statusCode(200)
                    .extract()
                    .response();

        // Extract access token
        accessToken =
                response.jsonPath()
                        .getString("data.token.accessToken");

        // Extract patient ID
        patientId =
                response.jsonPath()
                        .getInt("data.patientProfile.patientId");

        // Extract family member ID
        familyMemberId =
                response.jsonPath()
                        .getInt("data.familyMembers[0].patientId");

        Assert.assertNotNull(accessToken);
        Assert.assertFalse(accessToken.isEmpty());

        System.out.println("Login successful");
        System.out.println("Patient ID: " + patientId);
        System.out.println("Family Member ID: " + familyMemberId);
    }


    // ==========================================
    // DASHBOARD
    // ==========================================

    @Test(priority = 1)
    public void getDashboard() {

        Response response =
                given()
                    .auth()
                    .oauth2(accessToken)

                    .queryParam("patientId", patientId)

                .when()
                    .get(Endpoints.DASHBOARD)

                .then()
                    .statusCode(202)
                    .extract()
                    .response();

        response.then().log().body();

        System.out.println(
                "Dashboard API executed successfully");
    }


    // ==========================================
    // UPDATE FAMILY MEMBER
    // ==========================================

    @Test(priority = 2)
    public void updateFamilyMember() {

        FamilyMemberPayload payload =
                new FamilyMemberPayload(
                        93,
                        "FRIEND",
                        "Shawn",
                        null,
                        "Frost",
                        "2022-02-19",
                        null,
                        "B+",
                        false,
                        true,
                        true,
                        true
                );

        Response response =
                given()

                    .auth()
                    .oauth2(accessToken)

                    .contentType("application/json")

                    .pathParam(
                            "familyMemberId",
                            familyMemberId
                    )

                    .body(payload)

                .when()

                    .post(
                        Endpoints.UPDATE_FAMILY_MEMBER
                    )

                .then()

                    .log()
                    .ifValidationFails()

                    .statusCode(200)

                    .extract()
                    .response();

        response.then().log().body();

        System.out.println(
                "Family member updated successfully");
    }
}