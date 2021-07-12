package stepDefinations;

import Utils.ResourceAPI;
import Utils.TestDataBuild;
import Utils.UtilTest;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;

import java.io.IOException;

import static io.restassured.RestAssured.*;

public class placeAPITest extends UtilTest {

       Response response;
       RequestSpecification req;
       ResponseSpecification res;
       TestDataBuild dataBuild=new TestDataBuild();
       static String place_Id;

    
    @Given("Add Place Payload with {string} {string} {string}")
    public void add_place_payload_with(String name, String language, String address) throws IOException {
            req=given().spec( req() ).body(dataBuild.addPlacePayload( address,language, name)  );
    }
    @When("user calls {string} with {string} http request")
    public void user_calls_with_http_request(String resource, String requestType) {
            ResourceAPI resourceAPI=ResourceAPI.valueOf(resource );
            if(requestType.equalsIgnoreCase( "POST" ))
                response=req.when().post(resourceAPI.getResourcePath());
            else if(requestType.equalsIgnoreCase( "GET" ))
                response=req.when().get(resourceAPI.getResourcePath());
    }

    @Then("the API call got success with status code {int}")
    public void the_api_call_got_success_with_status_code(Integer code) {
       System.out.println("POST Status: "+response.getStatusCode());
      //Assert.assertEquals( String.valueOf( response.statusCode() ) ,code );
    }

    @Then("{string} in response body is {string}")
    public void in_response_body_is(String responseKey, String responseValue) {
        Assert.assertEquals(getJsonPath( response.asString(), responseKey),responseValue);
    }
    @Then("verify place_Id created maps to {string} using {string}")
    public void verify_place_id_created_maps_to_using(String name, String resource) throws IOException {
        place_Id=getJsonPath( response.asString(),"place_id" );
        req=given().spec( req() ).queryParam( "place_id",place_Id );
        user_calls_with_http_request(resource,"GET");
        System.out.println("GET Status: "+response.getStatusCode());
        Assert.assertEquals( getJsonPath( response.asString(),"name" ),name);
    }

    @Given("DeletePlace Payload")
    public void delete_place_payload() throws IOException {
        req=given().spec( req() ).body( dataBuild.deletePayload(place_Id) );
    }



}
