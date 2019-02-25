import java.io.BufferedReader;
import java.io. IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

//import wolfram Alpha 
// ?

public class test {
  public static void main(String[] args)  {
	String API_KEY = "LG7KVL-QJV2T43KWX" ;
	String problem = "3x-7=11"; //need to get from camera
	//String problemB = "x+7=11";
	String updateS = problem.replace ("=","%3D");
	updateS = updateS.replace ("+","%2B");
	updateS = updateS.replace ("/","%2F");
	System.out.println(updateS);
	
	String  urlString  = "http://api.wolframalpha.com/v2/query?appid="+ API_KEY + "&input=solve+"+updateS+"&output=xml&podstate=Result__Step-by-step+solution&format=plaintext";
//3x-7%3D11
	try {
		StringBuilder result = new StringBuilder();
		URL url = new URL(urlString); 
		URLConnection conn = url.openConnection();
		BufferedReader rd =  new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String line;
		while ((line = rd.readLine()) != null){
			result. append(line);
		}
		rd.close ();
		System.out.println(result);
 
	}
	catch(IOException e){
	System.out.println(e.getMessage());
}

  
  }

}
  //URL url = new URL("INSERT YOUR SERVER REQUEST");
//	System.out.println("Hello World");
