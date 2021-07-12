package Utils;

public enum ResourceAPI {
//Use enums when you have values that you know aren't going to change,
// like month days, days, colors, deck of cards, etc.
/*An enum can, just like a class, have attributes and methods.
The only difference is that enum constants are public, static and final
 (unchangeable - cannot be overridden).
An enum cannot be used to create objects, and it cannot extend other classes
 (but it can implement interfaces).
*/

    AddPlaceAPI("/maps/api/place/add/json"),
    GetPlaceAPI("/maps/api/place/get/json"),
    DeletePlaceAPI("/maps/api/place/delete/json");
    private String resource;
    ResourceAPI(String resource) {
        this.resource=resource;
    }

    public String getResourcePath()
    {
        return resource;
    }


}
