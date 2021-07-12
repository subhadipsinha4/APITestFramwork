Feature: Validating Place API's
  @AddPlace @Sanity
  Scenario: Verify if Place is being Succesfully added using AddPlaceAPI
    Given Add Place Payload with "Frontline house" "French-IN" "29 side layout cohen 09"
    When user calls "AddPlaceAPI" with "POST" http request
    Then the API call got success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_Id created maps to "Frontline house" using "GetPlaceAPI"


  @DeletePlace @Sanity
  Scenario: Verify if Delete Place functionality is working
    Given DeletePlace Payload
    When user calls "DeletePlaceAPI" with "POST" http request
    Then the API call got success with status code 200
    And "status" in response body is "OK"
