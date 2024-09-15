package demo_RestAssured;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import SerialPOJO.AddPlace;
import SerialPOJO.Location;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class SerializeTest {

	public static void main(String[] args) {
	AddPlace ap =new AddPlace();
	ap.setAccuracy(50);
	ap.setAddress("29,sidelayout,cohen 09");
	ap.setLanguage("French-IN");
	ap.setPhone_number("(+91) 983 893 3937");
	ap.setWebsite("https://rahulshettyacademy.com");
	ap.setName("Frontline House");
//types
	List<String> myList = new ArrayList<String>();
	myList.add("shoe park");
	myList.add("shop");
	ap.setTypes(myList);
//Location
	Location l = new Location();
	ap.setLocation(l);
	l.setLat(-38.383494);
	l.setLng(33.427362);
	
		RestAssured.baseURI="https://rahulshettyacademy.com";
String res=		given()
			.queryParam("key","qaclick123")
			.body(ap)
		.when()
			.post("/maps/api/place/add/json")
		.then()
			.assertThat().statusCode(200)
			.extract().response().asString();
	System.out.println(res);
	
	JsonPath js =new JsonPath(res);
	String PlaceId=js.getString("place_id");
	System.out.println("PlaceID : "+PlaceId);
		

	}

}
