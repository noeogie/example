package org.eogie.example;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class JsonExample {

    public static void main(String[] args) {
        new JsonExample().start();
    }

    public void start() {
        String jsonString = "{admin:{age:30,name:Kim},doors:[{doorId:1,doorName:\"main door\"},{doorId:2,doorName:\"1f door\"},{doorId:3,doorName:\"2f door\"}]}";
        JsonObject json = JsonParser.parseString(jsonString).getAsJsonObject();
        System.out.println("json = " + json);

        JsonObject admin = json.getAsJsonObject("admin");
        System.out.println("admin = " + admin);

        JsonArray doors = json.getAsJsonArray("doors");
        System.out.println("doors = " + doors);

        // JSON for each
        for (JsonElement door : doors) {
            System.out.println("doorId = " + door.getAsJsonObject().get("doorId").getAsInt());
            System.out.println("doorName = " + door.getAsJsonObject().get("doorName").getAsString());

            // JSON to Map
            Map<String, Object> doorMap = new Gson().fromJson(door, new TypeToken<Map<String, Object>>() {}.getType());
            System.out.println("doorMap = " + doorMap);
        }

        // JSON stream process
        Stream<JsonElement> stream = StreamSupport.stream(doors.spliterator(), false);
        List<JsonElement> list = stream.collect(Collectors.toList());
        System.out.println("list = " + list);

        // JSON to List
        List<JsonElement> list2 = new Gson().fromJson(doors, new TypeToken<List<JsonElement>>() {}.getType());
        System.out.println("list2 = " + list2);
    }
}
