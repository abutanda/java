//github version
import java.io.BufferedReader;
import java.io. IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

import java.net.HttpURLConnection;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
//import wolfram Alpha 
// ?

public class test {
	public static void main(String[] args)  {
		String API_KEY = "LG7KVL-QJV2T43KWX" ;
		String problem = "3x-7=11"; //need to get from camera
		//String problemB = "x+7=11";
                String steps;
		String updateS = problem.replace ("=","%3D");
		updateS = updateS.replace ("+","%2B");
		updateS = updateS.replace ("/","%2F");
		System.out.println(updateS);

		String  urlString  = "http://api.wolframalpha.com/v2/query?appid="+ API_KEY + "&input=solve+"+updateS+"&podstate=Result__Step-by-step+solution&format=plaintext";
		System.out.println(urlString);
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
			//print out xml 
			//System.out.println(result);
			try{
		    Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(result.toString())));
				NodeList errNodes = doc.getElementsByTagName("queryresult");
				if (errNodes.getLength() > 0) {
					Element err = (Element)errNodes.item(0);
                    			steps= err.getElementsByTagName("plaintext").item(2).getTextContent();
					System.out.println("possible solution  -"+ steps);
			String[] tokens= steps.split(":");
			for (int i=0; i<tokens.length;i++){

			//String dir1= tokens[0];
			//String dir2= tokens[1];
			//String dir3= tokens[2];
			System.out.println(tokens[i]);
			//System.out.println(dir2);
			//System.out.println(dir3);
			}
				} else { 
					// success
				}
			}catch (Exception e){
				System.out.println("Error parsing");
			}
		}
		catch(IOException e){
			System.out.println(e.getMessage());
		}



	}
}
//https://chillyfacts.com/java-send-url-http-request-read-xml-response/
