package demo_RestAssured;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import SerialPOJO.AddPlace;
import SerialPOJO.Location;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class specBuilderTest {

	public static void main(String[] args) {
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
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
//Request Specific Builder	
	RequestSpecification req= new RequestSpecBuilder()
							.setBaseUri("https://rahulshettyacademy.com")
							.addQueryParam("key","qaclick123")
							.setContentType(ContentType.JSON).build();
//Response Specific Builder
	ResponseSpecification resspec=new ResponseSpecBuilder()
								.expectStatusCode(200)
								.expectContentType(ContentType.JSON)
								.build();
		
RequestSpecification resp=given()
				.spec(req)
				.body(ap);

Response response=resp.when()
				.post("/maps/api/place/add/json")
			.then()
				.spec(resspec)
				.extract().response();
	String reponseString = response.asString();
	System.out.println(reponseString);
	
	JsonPath js =new JsonPath(reponseString);
	String PlaceId=js.getString("place_id");
	System.out.println("PlaceID : "+PlaceId);
		

	}

}
