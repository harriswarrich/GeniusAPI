package com.example;
import java.net.URLEncoder;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.util.Scanner;

public final class App {
    private App() {
    }
    public static void main(String[] args) throws Exception {
        //host url
        String host = "https://genius.p.rapidapi.com/search";
        String charset = "Utf-8";
        
        //headers for request
        String x_rapidapi_host = "genius.p.rapidapi.com";
        String x_rapidapi_key = "9e90cf06cdmsh3e261b357fda1e1p1baac8jsn90362635c065";

        //placeholder will be replaced with user input
        String s = "The Weeknd";
        Scanner input = new Scanner(System.in);
        System.out.println("Enter artist's first name");
        String firstName = input.nextLine();
        System.out.println("Enter artist's last name, if applicable");
        String lastName = input.nextLine();

        //format query
        String query = String.format("s=%s",
        URLEncoder.encode(s, charset));

        //sending request and seeing response
        HttpResponse <JsonNode> response = Unirest.get(host + "?" + query)
        .header("x-rapidapi-host", x_rapidapi_host)
        .header("x-rapidapi-key", x_rapidapi_key)
        .asJson();
        System.out.println(response.getStatus());
        System.out.println(response.getHeaders().get("Content-Type"));

        // Host, charset and headers vars should be the same        needs to be replaced with whatever the id is of the artist the user gives
        String i = "f0cd37a82a3995b8763014dfc1356260";

        // Format query for preventing encoding problems
      query = String.format("i=%s",
      URLEncoder.encode(i, charset));      
      // Json response
      // have user input first name and last name separately. Save first name and last name into 2 different variables. Use the variable's name to print the string unique to each user input.
      String myUrl = "https://genius.p.rapidapi.com/search?q=" + firstName;
      if(lastName != "")
      {
        myUrl = myUrl + "%20"+ lastName;
      }
      response = Unirest.get(myUrl)
     .header("x-rapidapi-host", x_rapidapi_host)
     .header("x-rapidapi-key", x_rapidapi_key)
     .asJson();

        //Prettifying
     Gson gson = new GsonBuilder().setPrettyPrinting().create();
     JsonParser jp = new JsonParser();
     JsonElement je = jp.parse(response.getBody().toString());
     String prettyJsonString = gson.toJson(je);
     System.out.println(prettyJsonString);
    }
}
