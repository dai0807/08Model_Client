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
		// �ּ��� �ϳ��� ó���ذ��� �ǽ�
		////////////////////////////////////////////////////////////////////////////////////////////
		
 //		// 1.1 Http Get ��� Request : JsonSimple lib ���
 	 	RestHttpClientApp2.getProductrTest_JsonSimple();
		
//		System.out.println("\n====================================\n");
//		// 1.2 Http Get ��� Request : CodeHaus lib ���
 	 	//RestHttpClientApp.getUserTest_Codehaus();
		
//		System.out.println("\n====================================\n");
//		// 2.1 Http Post ��� Request : JsonSimple lib ���
//		RestHttpClientApp.LoginTest_JsonSimple();
		
//		System.out.println("\n====================================\n");
//		// 1.2 Http Post ��� Request : CodeHaus lib ��� // ���⿡ �α׾ƿ� ���� 
	// 	RestHttpClientApp.LoginTest_Codehaus();		
	
		// addTest 
		// 	RestHttpClientApp.addUserTest_Codehaus();
		//userUpdate 
		// RestHttpClientApp.updateUser_Codehaus(); 
	 	//   RestHttpClientApp.checkDuplication_JsonSimpe() ; 
//	  RestHttpClientApp.checkDuplication_Codehaus();
	 	   
 
	 	   
	 	   
	 	   
	}
	
	
	public static void getProductrTest_JsonSimple() throws Exception{
		
		// HttpClient : Http Protocol �� client �߻�ȭ 
		HttpClient httpClient = new DefaultHttpClient();
		
		int prodNo = 10062 ;
		String tranCode ="003" ;
		String url= 	"http://127.0.0.1:8080/product/json/getProduct?prodNo="+prodNo+"&tranCode="+tranCode;
		System.out.println("url ::" + url );	
		// HttpGet : Http Protocol �� GET ��� Request
		HttpPost httpGet = new HttpPost(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-Type", "application/json");

		
		HttpResponse httpResponse = httpClient.execute(httpGet); // ������ 

		
		// HttpResponse : Http Protocol ���� Message �߻�ȭ
		JSONObject json = new JSONObject();
	
		HttpEntity httpEntity01 = new StringEntity(json.toString(),"utf-8");

		httpGet.setEntity(httpEntity01);
		
		//==> Response Ȯ��
		System.out.println(httpResponse);
		System.out.println();

		//==> Response �� entity(DATA) Ȯ��
		HttpEntity httpEntity = httpResponse.getEntity();
		
		//==> InputStream ����
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		System.out.println("[ Server ���� ���� Data Ȯ�� ] ");
		String serverData = br.readLine();
		System.out.println(serverData);
		
		//==> �����б�(JSON Value Ȯ��)
		JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
		System.out.println(jsonobj);
	
	}	
}