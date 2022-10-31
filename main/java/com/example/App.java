package com.example;
import java.net.URLEncoder;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;

public final class App {

  public static void main(String[] args) throws Exception {
    // host url
    String host = "https://genius.p.rapidapi.com/search";
    String charset = "Utf-8";

    // headers for request
    String x_rapidapi_host = "genius.p.rapidapi.com";
    String x_rapidapi_key = "9e90cf06cdmsh3e261b357fda1e1p1baac8jsn90362635c065";

    // placeholder will be replaced with user input
    String s = "The Weeknd";
    Scanner input = new Scanner(System.in);
    System.out.println("Enter artist's first name");
    String firstName = input.nextLine();
    System.out.println("Enter artist's last name, if applicable");
    String lastName = input.nextLine();

    // format query
    String query = String.format("s=%s",
        URLEncoder.encode(s, charset));

    // sending request and seeing response
    HttpResponse<JsonNode> response = Unirest.get(host + "?" + query)
        .header("x-rapidapi-host", x_rapidapi_host)
        .header("x-rapidapi-key", x_rapidapi_key)
        .asJson();
    // System.out.println(response.getStatus());
    // System.out.println(response.getHeaders().get("Content-Type"));

    // Host, charset and headers vars should be the same needs to be replaced with
    // whatever the id is of the artist the user gives
    String i = "727466";

    // Format query for preventing encoding problems
    query = String.format("i=%s",
        URLEncoder.encode(i, charset));
    // Json response
    // have user input first name and last name separately. Save first name and last
    // name into 2 different variables. Use the variable's name to print the string
    // unique to each user input.
    String myUrl = "https://genius.p.rapidapi.com/search?q=" + firstName;
    if (lastName != "") {
      myUrl = myUrl + "%20" + lastName;
    }
    response = Unirest.get(myUrl)
        .header("x-rapidapi-host", x_rapidapi_host)
        .header("x-rapidapi-key", x_rapidapi_key)
        .asJson();

    JSONObject jsonObj = response.getBody().getObject();

    // store artist's id in a String
    JSONObject artist = new JSONObject();
    JSONArray third = new JSONArray();
    artist = jsonObj.getJSONObject("response");
    third = artist.getJSONArray("hits");
    artist = third.getJSONObject(0);
    artist = artist.getJSONObject("result");
    artist = artist.getJSONObject("primary_artist");
    int artistId = artist.getInt("id");

    // SECOND API CALL ARTIST API
    String url2 = "https://genius.p.rapidapi.com/artists/" + artistId;
    String query2 = String.format("s=%s",
        URLEncoder.encode(s, charset));

    HttpResponse<JsonNode> response2 = Unirest.get(host + "?" + query)
        .header("x-rapidapi-host", x_rapidapi_host)
        .header("x-rapidapi-key", x_rapidapi_key)
        .asJson();
    // System.out.println(response2.getStatus());
    // System.out.println(response2.getHeaders().get("Content-Type"));

    String z = "727466";

    query2 = String.format("z=%s",
        URLEncoder.encode(z, charset));
    response2 = Unirest.get(url2)
        .header("x-rapidapi-host", x_rapidapi_host)
        .header("x-rapidapi-key", x_rapidapi_key)
        .asJson();
    JSONObject obj2 = response2.getBody().getObject();

    // THIRD API CALL
    String url3 = "https://genius.p.rapidapi.com/artists/" + artistId + "/songs?sort=popularity";
    String query3 = String.format("s=%s",
        URLEncoder.encode(s, charset));

    HttpResponse<JsonNode> response3 = Unirest.get(host + "?" + query)
        .header("x-rapidapi-host", x_rapidapi_host)
        .header("x-rapidapi-key", x_rapidapi_key)
        .asJson();
    // System.out.println(response3.getStatus());
    // System.out.println(response3.getHeaders().get("Content-Type"));

    String y = "727466";

    query3 = String.format("y=%s",
        URLEncoder.encode(y, charset));
    response3 = Unirest.get(url3)
        .header("x-rapidapi-host", x_rapidapi_host)
        .header("x-rapidapi-key", x_rapidapi_key)
        .asJson();
    JSONObject obj3 = response3.getBody().getObject();

    ParseData parser = new ParseData();
    Scanner question = new Scanner(System.in);
    System.out.println(
        "Enter Information to retrieve(Ex: social medias, alternate names, top songs, random songs, release dates, pageviews of most popular songs. Specify how many songs.");
    //int value = Integer.parseInt(quest.replaceAll("[^0-9]", ""));
    while (true) {
      String quest = input.nextLine();
      if(quest.length() > 0){
        if(quest.contains("social")){
          System.out.println(parser.getSoicalMedias(obj2));
        }
        else if(quest.contains("name")){
          System.out.println(parser.getAlternateNames(obj2));
        }
        else if(quest.contains("date")){
          int value = Integer.parseInt(quest.replaceAll("[^0-9]", ""));
          System.out.println(parser.getReleaseDates(value, jsonObj));
        }
        else if(quest.contains("top")){
          int value = Integer.parseInt(quest.replaceAll("[^0-9]", ""));
          System.out.println(parser.getTopSongs(value, obj3));
        }
        else if(quest.contains("random")){
          int value = Integer.parseInt(quest.replaceAll("[^0-9]", ""));
          System.out.println(parser.getRandomSongs(value, jsonObj));
        }
      }
      else{
        break;
      }
    }
  }
}