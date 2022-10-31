package com.example;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.HashMap;
public class ParseData{
    //method to display n song names
    public ArrayList<String> getRandomSongs(int n, JSONObject jsonObj){
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
    //method to save artirtId;
    public void getArtistId(JSONObject jsonObj){
        JSONObject artist = new JSONObject();
        JSONArray third = new JSONArray();
        artist = jsonObj.getJSONObject("response");
        third = artist.getJSONArray("hits");
        artist = third.getJSONObject(0);
        artist = artist.getJSONObject("result");
        artist = artist.getJSONObject("primary_artist");
        int artistId = artist.getInt("id");
    }
    //metod to display alternate names of the artist
    public JSONArray getAlternateNames(JSONObject obj2){
        JSONObject alternateNames = new JSONObject();
        JSONArray fourth = new JSONArray();
        alternateNames = obj2.getJSONObject("response");
        alternateNames = alternateNames.getJSONObject("artist");
        fourth = alternateNames.getJSONArray("alternate_names");
        return fourth;
    }
    //method to display their social media handles
    public String getSoicalMedias(JSONObject obj2){
        JSONObject socialMedia = new JSONObject();
        socialMedia = obj2.getJSONObject("response");
        socialMedia = socialMedia.getJSONObject("artist");
        String facebook = socialMedia.getString("facebook_name");
        String instagram = socialMedia.getString("instagram_name");
        String twitter = socialMedia.getString("twitter_name");
        return "facebook: " + facebook + " " + "instagram: " + instagram + " " + "twitter: " + twitter;
    }
    //method to display their top n songs and their corresponding page views
    public HashMap<String, Integer> getTopSongs(int p, JSONObject obj3){
     JSONObject topSongs = new JSONObject();
     JSONArray store = new JSONArray();
     JSONArray views = new JSONArray();
     ArrayList popularSongs = new ArrayList();
     ArrayList pageViews = new ArrayList();
     HashMap<String, Integer> hotSongs = new HashMap<String, Integer>();
     for(int g = 0; g < p; g++){
        topSongs = obj3.getJSONObject("response");
        store = topSongs.getJSONArray("songs");
        topSongs = store.getJSONObject(g);
        String topTitle = topSongs.getString("title");
        topSongs = topSongs.getJSONObject("stats");
        int viewsList = topSongs.getInt("pageviews");
        hotSongs.put(topTitle, viewsList);
     }
     return hotSongs;
    }

    //method to connect to AI
    
}
    
    
