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

import java.util.Scanner;  // Import the Scanner class

public class test {
	public static void main(String[] args)  {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter solution");
        String userSolution = myObj.nextLine();  // Read user input
		String API_KEY = "LG7KVL-QJV2T43KWX" ;
		String problem = "3x-7=11"; //need to get from camera
		//String problemB = "x+7=11";
		String steps;
		String updateS = problem.replace ("=","%3D");
		updateS = updateS.replace ("+","%2B");
		updateS = updateS.replace ("/","%2F");
		//System.out.println(updateS);

		String  urlString  = "http://api.wolframalpha.com/v2/query?appid="+ API_KEY + "&input=solve+"+updateS+"&podstate=Result__Step-by-step+solution&format=plaintext";
		//System.out.println(urlString);
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
					//System.out.println("possible solution  -"+ steps);
					int stepindex1= steps.indexOf(":");
					int ansindex= steps.indexOf("|  |");	
					String solution = steps.substring(ansindex+4, steps.length());
					String temp = steps.substring(0,stepindex1);
					String temp2 = steps.substring(stepindex1+1, steps.length()-1);
					//int stepindex2= temp2.indexOf(":");
					System.out.println(temp);
					//System.out.println(temp2);
					System.out.println(solution);
                    System.out.println("User Solution is: " + userSolution);  // Output user input 
				}}catch (Exception e){
					System.out.println("Error parsing");
				}
		}catch(IOException e){
			System.out.println(e.getMessage());
		}



	}
}
//http://javadevnotes.com/java-string-split-tutorial-and-examples
//https://chillyfacts.com/java-send-url-http-request-read-xml-response/
