package com.infoworld;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import java.io.PrintWriter;

import org.apache.commons.text.StringEscapeUtils;
import org.json.JSONException;

import lombok.extern.slf4j.Slf4j;

//import com.google.gson.JsonParseException;

@Slf4j
public class App {
	//private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
	
	public static void main(String[] args) {
		
		//Set Chatgpt endpoint and API KEY
		
		String endpoint = "https://api.openai.com/v1/chat/completions";
		String apiKey = "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";
		
		// Prompt user for input String
		//try {
			//Create a FileOutoutStream to write the file
			//FileOutputStream fileOutputStream = new FileOutputStream("chat_log.txt");
			
			//Create a PrintStream that writes to the file stream
			//PrintStream printStream = new PrintStream(fileOutputStream);
			
			//Redirect the standard output to the print stream
			//System.setOut(printStream);
		try(PrintWriter data = new PrintWriter("chat_gpt.txt")){
				for(int i = 0 ; i <3; i++) {
					
					try {
						
						BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
						//Scanner input = new Scanner(System.in);
						System.out.print("Enter your Message: ");
						String input = reader. readLine();
					
						
						
						//send input to chatGpt API and display response
						String responses = ChatBot.sendQuery(input, endpoint, apiKey);
						
						
						
						log.info("Response: {} \n", responses);
						String response = StringEscapeUtils.unescapeJava(responses);
						String result = response.substring(1,response.length()-1);
						data.println("You: "+input);
						data.println("Git: "+result);
						
					}catch(IOException e) {
						log.error("Error reading input: {}", e.getMessage());
					}catch(JSONException e) {
						log.error("Error parsing API response: {}", e.getMessage());
					}catch(Exception e) {
						log.error("Unexpected error: {}", e.getMessage());
						
					}
				
					
					
				}
				data.close();
				//printStream.close();
				//fileOutputStream.close();
				
		}catch(IOException e) {
			//e.printStackTrace();
		}
	}
}
