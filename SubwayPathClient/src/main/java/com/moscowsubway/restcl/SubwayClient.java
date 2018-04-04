/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moscowsubway.restcl;

import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.json.*;

public class SubwayClient {

    public SubwayClient() {
        
    }
    
    public ArrayList<Station> getAllStations() {
        ArrayList<Station> stations = new ArrayList<>();
        NewJerseyClient njClient = new NewJerseyClient();
        String stationsStr = njClient.getAllStations();
        JSONObject jsonObj = new JSONObject(stationsStr);
        JSONArray jsonStns = jsonObj.getJSONArray("list");
        for(int i = 0; i < jsonStns.length(); i++) 
        {
            JSONObject jStn = jsonStns.getJSONObject(i);
            Station station = new Station();
            station.setName(jStn.getString("name"));
            station.setId(jStn.getInt("id"));
            station.setLineNum(jStn.getInt("lineNum"));
            stations.add(station);
        }
        return stations;
    }
    
    public ArrayList<Station> getPath(int src, int dst) {
        ArrayList<Station> stations = new ArrayList<>();
        NewJerseyClient njClient = new NewJerseyClient();
        String stationsStr = njClient.getPath(src, dst);
        JSONObject jsonObj = new JSONObject(stationsStr);
        JSONArray jsonStns = jsonObj.getJSONArray("list");
        for(int i = 0; i < jsonStns.length(); i++) 
        {
            JSONObject jStn = jsonStns.getJSONObject(i);
            Station station = new Station();
            station.setName(jStn.getString("name"));
            station.setId(jStn.getInt("id"));
            station.setLineNum(jStn.getInt("lineNum"));
            javafx.scene.image.Image image = new javafx.scene.image.Image(getClass().getResource("/imgs/l" + station.getLineNum() + ".png").toExternalForm());
            ImageView imageView = new ImageView(image);
            station.setImage(imageView);
            stations.add(station);
        }
        return stations;
    }
}
