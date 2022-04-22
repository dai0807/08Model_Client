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
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.model2.mvc.service.domain.User;



public class RestHttpClientApp {
	
	// main Method
	public static void main(String[] args) throws Exception{
		
		////////////////////////////////////////////////////////////////////////////////////////////
		// 주석을 하나씩 처리해가며 실습
		////////////////////////////////////////////////////////////////////////////////////////////
		
//		System.out.println("\n====================================\n");
//		// 1.1 Http Get 방식 Request : JsonSimple lib 사용
 	//	RestHttpClientApp.getUserTest_JsonSimple();
		
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
	 	   RestHttpClientApp.checkDuplication_JsonSimpe() ; 
//	  RestHttpClientApp.checkDuplication_Codehaus();
	}
	
	
//================================================================//
	//1.1 Http Protocol GET Request : JsonSimple 3rd party lib 사용
	public static void getUserTest_JsonSimple() throws Exception{
		
		// HttpClient : Http Protocol 의 client 추상화 
		HttpClient httpClient = new DefaultHttpClient();
		
		String url= 	"http://127.0.0.1:8080/user/json/getUser/user01";
				
		// HttpGet : Http Protocol 의 GET 방식 Request
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-Type", "application/json");
		
		// HttpResponse : Http Protocol 응답 Message 추상화
		HttpResponse httpResponse = httpClient.execute(httpGet);
		
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
	
	
	//1.2 Http Protocol GET Request : JsonSimple + codehaus 3rd party lib 사용
	public static void getUserTest_Codehaus() throws Exception{
		
		// HttpClient : Http Protocol 의 client 추상화 
		HttpClient httpClient = new DefaultHttpClient();
		
		String url= 	"http://127.0.0.1:8080/user/json/getUser/admin";

		// HttpGet : Http Protocol 의 GET 방식 Request
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-Type", "application/json");
		
		// HttpResponse : Http Protocol 응답 Message 추상화
		HttpResponse httpResponse = httpClient.execute(httpGet);
		
		//==> Response 확인
		System.out.println(httpResponse);
		System.out.println();

		//==> Response 중 entity(DATA) 확인
		HttpEntity httpEntity = httpResponse.getEntity();
		
		//==> InputStream 생성
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		//==> 다른 방법으로 serverData 처리 
		//System.out.println("[ Server 에서 받은 Data 확인 ] ");
		//String serverData = br.readLine();
		//System.out.println(serverData);
		
		//==> API 확인 : Stream 객체를 직접 전달 
		JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
		System.out.println(jsonobj);
	
		ObjectMapper objectMapper = new ObjectMapper();
		 User user = objectMapper.readValue(jsonobj.toString(), User.class);
		 // 코드하우스 오브젝트 매버로 , 값을 읽어서 투스트링으로 바꿔서, User 객체로 변환 )
		 System.out.println(user);
	}
//================================================================//	
	
//================================================================//
	//2.1 Http Protocol POST Request : FromData 전달 / JsonSimple 3rd party lib 사용
	public static void LoginTest_JsonSimple() throws Exception{
		
		// HttpClient : Http Protocol 의 client 추상화 
		HttpClient httpClient = new DefaultHttpClient();
		
		String url = "http://127.0.0.1:8080/user/json/login";
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");
		
		//[ 방법 1 : String 사용]
//			String data =  "{\"userId\":\"admin\",\"password\":\"1234\"}";
//			HttpEntity httpEntity01 = new StringEntity(data,"utf-8");
		
		//[ 방법 2 : JSONObject 사용]
		JSONObject json = new JSONObject();
		json.put("userId", "admin");
		json.put("password", "1234");
		HttpEntity httpEntity01 = new StringEntity(json.toString(),"utf-8");

		httpPost.setEntity(httpEntity01);
		HttpResponse httpResponse = httpClient.execute(httpPost);
		
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
	
	
	//2.2 Http Protocol POST 방식 Request : FromData전달 
	//==> JsonSimple + codehaus 3rd party lib 사용
	public static void LoginTest_Codehaus() throws Exception{
		
		// HttpClient : Http Protocol 의 client 추상화 
		HttpClient httpClient = new DefaultHttpClient();
		
		String url = "http://127.0.0.1:8080/user/json/login";
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");
		
//		//[ 방법 1 : String 사용]
//		String data =  "{\"userId\":\"admin\",\"password\":\"1234\"}";
//		HttpEntity httpEntity01 = new StringEntity(data,"utf-8");
	
//		//[ 방법 2 : JSONObject 사용]
//		JSONObject json = new JSONObject();
//		json.put("userId", "admin");
//		json.put("password", "1234");
//		HttpEntity httpEntity01 = new StringEntity(json.toString(),"utf-8");
		
		//[ 방법 3 : codehaus 사용]
		User user01 =  new User();
		user01.setUserId("admin");
		user01.setPassword("1234");
		ObjectMapper objectMapper01 = new ObjectMapper();
		//Object ==> JSON Value 로 변환
		String jsonValue = objectMapper01.writeValueAsString(user01);
		
		System.out.println(jsonValue);
		
		HttpEntity httpEntity01 = new StringEntity(jsonValue,"utf-8");
		
		httpPost.setEntity(httpEntity01);
		HttpResponse httpResponse = httpClient.execute(httpPost);
		
		//==> Response 확인
		System.out.println(httpResponse);
		System.out.println();

		//==> Response 중 entity(DATA) 확인
		HttpEntity httpEntity = httpResponse.getEntity();
		
		//==> InputStream 생성
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		//==> 다른 방법으로 serverData 처리 
		//System.out.println("[ Server 에서 받은 Data 확인 ] ");
		//String serverData = br.readLine();
		//System.out.println(serverData);
		
		//==> API 확인 : Stream 객체를 직접 전달 
		JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
		System.out.println(jsonobj);
	
		ObjectMapper objectMapper = new ObjectMapper();
		 User user = objectMapper.readValue(jsonobj.toString(), User.class);
		 System.out.println(user);
		 
		 
		 System.out.println();
		 System.out.println();

		 System.out.println("로그아웃이 될까요?  ");
		 System.out.println("logout! ,,,, ");
			 url = "http://127.0.0.1:8080/user/json/logout";
			 httpPost = new HttpPost(url);
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-Type", "application/json");
			  httpResponse = httpClient.execute(httpPost);

		 
		 
		 
		 
		 
	}	
	
	
	
	public static void addUserTest_Codehaus() throws Exception{
		
		// HttpClient : Http Protocol 의 client 추상화 
 		
		String url= 	"http://127.0.0.1:8080/user/json/addUser";
		// HttpClient : Http Protocol 의 client 추상화 
		HttpClient httpClient = new DefaultHttpClient();
		
 		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");
 
		//추가할 객체 생성 
		User user =  new User();
		user.setUserId("test");
		user.setPassword("test");
		user.setUserName("test되라고");

		user.setAddr("ㅇㅇㅇ") ; 
		
		//코드 하우스 생성 
		ObjectMapper objectMapper01 = new ObjectMapper();

		//Object ==> JSON Value 로 변환
		String jsonValue = objectMapper01.writeValueAsString(user);

		System.out.println(jsonValue); //  JSON으로 변한 거 출력 
 
		HttpEntity httpEntity01 = new StringEntity(jsonValue,"utf-8"); // JSON value를 투스트링해서 String Enttiy로 넣기

		httpPost.setEntity(httpEntity01); // Post방식으로 가기전 eneity 세팅
		HttpResponse httpResponse = httpClient.execute(httpPost); // HtttpClinet. Post로 세팅한거 실행 시키고 Respone로 받음
		
		//==> Response 확인
		System.out.println(httpResponse);
		System.out.println();

		//==> Response 중 entity(DATA) 확인
		HttpEntity httpEntity = httpResponse.getEntity(); // getEntity에서 데이터 받음 
		
		//==> InputStream 생성
		InputStream is = httpEntity.getContent(); // 끌어오기 
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		
		System.out.println("[ Server 에서 받은 Data 확인 ] ");
		String serverData = br.readLine();
		System.out.println(serverData);
		
		//==> 내용읽기(JSON Value 확인)
		JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
		System.out.println(jsonobj);
		
	}
	
	
	public static void updateUser_Codehaus() throws Exception{
		
		// HttpClient : Http Protocol 의 client 추상화 
 		
		String url= 	"http://127.0.0.1:8080/user/json/updateUser";
		// HttpClient : Http Protocol 의 client 추상화 
		HttpClient httpClient = new DefaultHttpClient();
		
 		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");
		
 
		User user =  new User();
		user.setUserId("test");
		user.setPassword("test");
		user.setUserName("테스트");
		user.setAddr("주소 변경 되었습니다!") ; 
		user.setPhone("010-1234-5678") ; 
		user.setEmail("bi111@bit.com") ;

		ObjectMapper objectMapper01 = new ObjectMapper();

		String jsonValue = objectMapper01.writeValueAsString(user);

		System.out.println(jsonValue); //  JSON으로 변한 거 출력 
 
		
		HttpEntity httpEntity01 = new StringEntity(jsonValue,"utf-8"); // JSON value를 투스트링해서 String Enttiy로 넣기

		httpPost.setEntity(httpEntity01); // Post방식으로 가기전 eneity 세팅
		HttpResponse httpResponse = httpClient.execute(httpPost); // HtttpClinet. Post로 세팅한거 실행 시키고 Respone로 받음
		
		//==> Response 확인
		System.out.println(httpResponse);
		System.out.println();

		//==> Response 중 entity(DATA) 확인
		HttpEntity httpEntity = httpResponse.getEntity(); // getEntity에서 데이터 받음 
		
		//==> InputStream 생성
		InputStream is = httpEntity.getContent(); // 끌어오기 
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		
		
		System.out.println("[ Server 에서 받은 Data 확인 ] ");
		String serverData = br.readLine();
		System.out.println(serverData);
		
		//==> 내용읽기(JSON Value 확인)
		JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
		System.out.println(jsonobj);
		
	}
	
	
	
	
	public static void  checkDuplication_JsonSimpe () throws Exception{
		
		HttpClient httpClient = new DefaultHttpClient();
		String url = "http://127.0.0.1:8080/user/json/checkDuplication";
		
		HttpPost httpPost = new HttpPost(url) ;
		httpPost.setHeader("Accept" , "application/json");
		httpPost.setHeader("Content-Type", "application/json");
		
		System.out.println("나와라 시바");
		//json simple  방법
		
		JSONObject json = new JSONObject()  ;
		json.put("userId" , "user21") ;
		
		HttpEntity httpEntiy01 = new StringEntity(json.toString(),"utf-8") ; // HttpEntiy는 Post 로 보내기전 데이터 세팅
		 httpPost.setEntity(httpEntiy01) ;
		 
		 HttpResponse httpResponse = httpClient.execute(httpPost) ;  // excute로 보내고 httpRespone로 받음
		 
		 System.out.println(httpResponse);
		 System.out.println();
		 
			//==> Response 중 entity(DATA) 확인
		HttpEntity httpEntity = httpResponse.getEntity();		 // HttpEntity 로 값 받기 
		
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"utf-8"));
		
		System.out.println("[ Server 에서 받은 Data 확인 ] ");
		String serverData = br.readLine();
		System.out.println(serverData);
		
		JSONObject jsonChekobj = (JSONObject)JSONValue.parse(serverData);
		System.out.println(jsonChekobj);	
		
		// 왜 서버에 안찍히는 거야 왜 
		
	}
	
	
	
	
////	
//public static void  checkDuplication_Codehaus() throws Exception{
//		
//		HttpClient httpClient = new DefaultHttpClient();
//		String url = "http://127.0.0.1:8080/user/json/checkDuplicationCode";
//		
//		HttpPost httpPost = new HttpPost(url) ;
//		httpPost.setHeader("Accept" , "application/json");
//		httpPost.setHeader("Content-Type", "application/json");
//		
//		User user =  new User();
//		user.setUserId("user21");
//		ObjectMapper objectMapper01 = new ObjectMapper();
//		String jsonValue = objectMapper01.writeValueAsString(user);
//		
//		HttpEntity httpEntity01 = new StringEntity(jsonValue,"utf-8"); 
//		
//		httpPost.setEntity(httpEntity01); // Post방식으로 가기전 eneity 세팅
//		HttpResponse httpResponse = httpClient.execute(httpPost); 
//		
// 
//			//==> Response 중 entity(DATA) 확인
//		HttpEntity httpEntity = httpResponse.getEntity();		 // HttpEntity 로 값 받기 
//		
//		InputStream is = httpEntity.getContent();
//		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
//		
//		System.out.println("[ Server 에서 받은 Data 확인 ] ");
//		String serverData = br.readLine();
//		System.out.println(serverData);
//		
//		JSONObject jsonChekobj = (JSONObject)JSONValue.parse(serverData);
//		System.out.println(jsonChekobj);	
//	
//		
//}
		
}