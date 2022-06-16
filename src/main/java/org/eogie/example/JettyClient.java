package org.eogie.example;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.http.HttpMethod;

import java.util.List;

public class JettyClient {

    public static void main(String[] args) {
        new JettyClient().start();
    }

    public void start() {
        HttpClient httpClient = new HttpClient();

        try {
            httpClient.start();
            ContentResponse response = httpClient
                    .newRequest("http://127.0.0.1:8080/example")
                    .method(HttpMethod.GET)
                    .send();

            JsonObject json = JsonParser.parseString(response.getContentAsString()).getAsJsonObject();
            int count = json.get("result").getAsJsonObject().get("count").getAsInt();
            List<Integer> list = new Gson().fromJson(json.get("list").getAsJsonArray(), new TypeToken<List<Integer>>() {}.getType());

            System.out.println("response = " + response.getContentAsString());
            System.out.println("count = " + count);
            System.out.println("list = " + list);

            httpClient.stop();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
