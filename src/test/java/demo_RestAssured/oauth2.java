package demo_RestAssured;

import static io.restassured.RestAssured.given;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.testng.Assert;
import io.restassured.path.json.JsonPath;
import pojoClasses.Api;
import pojoClasses.getcourseResponse;
import pojoClasses.webautomation;

public class oauth2 {

	public static void main(String[] args){

		String [] courseTitles = {"Selenium Webdriver Java","Cypress","Protractor"};
String response =

                given() 
                   		.formParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")

                        .formParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")

                        .formParams("grant_type", "client_credentials")

                        .formParams("scope", "trust")
                        .log().all()
                .when()
                    	.post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token")
                    	.asString();
	
				System.out.println(response);
				
				JsonPath jsonPath = new JsonPath(response);
				
				String accessToken = jsonPath.getString("access_token");
				
				System.out.println("Access Token : "+accessToken);
//get the token				
getcourseResponse gc = given()
						.queryParams("access_token", accessToken)
					.when()
						.get("https://rahulshettyacademy.com/oauthapi/getCourseDetails")
						.as(getcourseResponse.class);
				
				System.out.println(gc.getLinkedin());
				System.out.println(gc.getInstructor());
				System.out.println(gc.getCourses().getWebAutomation().get(2).getCourseTitle());
				System.out.println(gc.getCourses().getMobile().get(0).getPrice());
				
				List<Api> apicourses = gc.getCourses().getApi();
				
				for(int i=0;i<apicourses.size();i++) {
					if(apicourses.get(i).getCourseTitle().equalsIgnoreCase("Rest Assured Automation using Java")) {
						System.out.println("Price of "+apicourses.get(i).getCourseTitle()+" Course is :"+apicourses.get(i).getPrice());
					}
					
				}
				ArrayList<String> a =new ArrayList<String>();
				
			
				List<webautomation> webAucourses = gc.getCourses().getWebAutomation();
				
				for(int i=0;i<webAucourses.size();i++) {
					a.add(webAucourses.get(i).getCourseTitle());
				//	System.out.println(webAucourses.get(i).getCourseTitle()+": "+webAucourses.get(i).getPrice());
				//	System.out.println("Price of "+webAucourses.get(i).getCourseTitle()+" Course is :"+webAucourses.get(i).getPrice());
					
				}
			
				List<String> expectedList = Arrays.asList(courseTitles);
				Assert.assertTrue(a.equals(expectedList));		
	}
}

