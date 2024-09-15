package demo_RestAssured;

import org.testng.Assert;
import org.testng.annotations.Test;

import files.payloads;
import io.restassured.path.json.JsonPath;

public class sumValidation {
	

//	6. Verify if Sum of all Course prices matches with Purchase Amount
	@Test
	public void sumOfCourse()
	{
		
	int sum =0;
	JsonPath js =new JsonPath(payloads.CoursePrices());
	int count =js.getInt("courses.size()");
	
	for(int i=0;i<count;i++) {
		String ctitle =js.getString("courses["+i+"].title");
		int CoursePrice=js.getInt("courses["+i+"].price");
		int copies = js.getInt("courses["+i+"].copies");
		int each_coursePrice= CoursePrice * copies;
		System.out.println(""+ctitle+" Course Purchased Amount: "+each_coursePrice);
		sum = sum + each_coursePrice;
	}
	System.out.println("Total Sum of Courses: "+sum);
	
	int purchaseAmt= js.get("dashboard.purchaseAmount");
	Assert.assertEquals(sum, purchaseAmt);
	}
	

}
