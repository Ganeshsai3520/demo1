package demo_RestAssured;

import files.payloads;
import io.restassured.path.json.JsonPath;

public class complexJsonParse {
	
	public static void main(String[] args) {
		JsonPath js =new JsonPath(payloads.CoursePrices());
		//print no of courses returned by API
		
		int count =js.getInt("courses.size()");
		System.out.println("no of courses returned by API: "+count);
	
//		2.Print Purchase Amount
		int purchaseAmt= js.get("dashboard.purchaseAmount");
		System.out.println("Purchase Amount: Rs."+purchaseAmt);
		
//		3. Print Title of the first course
		String firstcourse = js.getString("courses[0].title");
		System.out.println("Title of the first course: "+ firstcourse);
//		4. Print All course titles and their respective Prices
		for(int i=0;i<count;i++) {
			String CourseTitles=js.getString("courses["+i+"].title");	
			int CoursePrices=js.getInt("courses["+i+"].price");
			System.out.println(CourseTitles+": Rs."+CoursePrices);
		}
//		5. Print no of copies sold by RPA Course
		for(int i=0;i<count;i++) {
			String ctitle =js.getString("courses["+i+"].title");
			if(ctitle.equalsIgnoreCase("RPA")) {
				int copies = js.getInt("courses["+i+"].copies");
				System.out.println("no of copies sold by "+ctitle+" Course: "+copies);
				break;
			}
		}
		


	}

}
