package com.example;
import java.net.URLEncoder;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

public class ParseData {
    //method to display n song names
    public ArrayList<String> getTopSongs(int n, JSONObject jsonObj){
        JSONObject songName = new JSONObject();
        JSONArray first = new JSONArray();
        ArrayList<String> songs = new ArrayList<String>();
        for(int f = 0; f<n; f++){
            songName = jsonObj.getJSONObject("response");
            first = songName.getJSONArray("hits");
            songName = first.getJSONObject(f);
            songName = songName.getJSONObject("result");
            String title = songName.getString("title");
            songs.add(title);
            }
            return songs;
    }
    //method to display release dates of songs
    public ArrayList<String> getReleaseDates(int n, JSONObject jsonObj){
        JSONObject releaseDate = new JSONObject();
        JSONArray second = new JSONArray();
        ArrayList<String> dates = new ArrayList<String>();
        for(int f = 0; f<n; f++){
            releaseDate = jsonObj.getJSONObject("response");
            second = releaseDate.getJSONArray("hits");
            releaseDate = second.getJSONObject(f);
            releaseDate = releaseDate.getJSONObject("result");
            String title = releaseDate.getString("release_date_for_display");
            dates.add(title);
        }
        return dates;
    }

    
    }
