package client.app;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class RestHttpClientApp2 {
	public static void main(String[] args) throws Exception{
		
		////////////////////////////////////////////////////////////////////////////////////////////
		// 주석을 하나씩 처리해가며 실습
		////////////////////////////////////////////////////////////////////////////////////////////
		
 //		// 1.1 Http Get 방식 Request : JsonSimple lib 사용
 	 	RestHttpClientApp2.getProductrTest_JsonSimple();
		
//		System.out.println("\n====================================\n");
//		// 1.2 Http Get 방식 Request : CodeHaus lib 사용
 	 	//RestHttpClientApp.getUserTest_Codehaus();
		
//		System.out.println("\n====================================\n");
//		// 2.1 Http Post 방식 Request : JsonSimple lib 사용
//		RestHttpClientApp.LoginTest_JsonSimple();
		
//		System.out.println("\n====================================\n");
//		// 1.2 Http Post 방식 Request : CodeHaus lib 사용 // 여기에 로그아웃 넣음 
	// 	RestHttpClientApp.LoginTest_Codehaus();		
	
		// addTest 
		// 	RestHttpClientApp.addUserTest_Codehaus();
		//userUpdate 
		// RestHttpClientApp.updateUser_Codehaus(); 
	 	//   RestHttpClientApp.checkDuplication_JsonSimpe() ; 
//	  RestHttpClientApp.checkDuplication_Codehaus();
	 	   
 
	 	   
	 	   
	 	   
	}
	
	
	public static void getProductrTest_JsonSimple() throws Exception{
		
		// HttpClient : Http Protocol 의 client 추상화 
		HttpClient httpClient = new DefaultHttpClient();
		
		int prodNo = 10062 ;
		String tranCode ="003" ;
		String url= 	"http://127.0.0.1:8080/product/json/getProduct?prodNo="+prodNo+"&tranCode="+tranCode;
		System.out.println("url ::" + url );	
		// HttpGet : Http Protocol 의 GET 방식 Request
		HttpPost httpGet = new HttpPost(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-Type", "application/json");

		
		HttpResponse httpResponse = httpClient.execute(httpGet); // 보내기 

		
		// HttpResponse : Http Protocol 응답 Message 추상화
		JSONObject json = new JSONObject();
	
		HttpEntity httpEntity01 = new StringEntity(json.toString(),"utf-8");

		httpGet.setEntity(httpEntity01);
		
		//==> Response 확인
		System.out.println(httpResponse);
		System.out.println();

		//==> Response 중 entity(DATA) 확인
		HttpEntity httpEntity = httpResponse.getEntity();
		
		//==> InputStream 생성
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		System.out.println("[ Server 에서 받은 Data 확인 ] ");
		String serverData = br.readLine();
		System.out.println(serverData);
		
		//==> 내용읽기(JSON Value 확인)
		JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
		System.out.println(jsonobj);
	
	}	
}