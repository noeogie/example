package org.eogie.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.api.Request;
import org.eclipse.jetty.client.util.StringContentProvider;
import org.eclipse.jetty.http.HttpHeader;
import org.eclipse.jetty.http.HttpMethod;

public class JettyPostClient {

    public static void main(String[] args) {
        new JettyPostClient().start();
    }

    public void start() {
        HttpClient httpClient = new HttpClient();

        try {
            httpClient.start();

            JsonObject body = new JsonObject();
            body.addProperty("data1", 1234);
            body.addProperty("data2", 1234);
            body.addProperty("data3", 1234);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            Request request = httpClient.newRequest("http://127.0.0.1:8080/example").method(HttpMethod.POST);
            request.header(HttpHeader.CONTENT_TYPE, "application/json");
            request.content(new StringContentProvider(gson.toJson(body)));

            ContentResponse response = request.send();

            JsonObject json = JsonParser.parseString(response.getContentAsString()).getAsJsonObject();
            int data1 = json.get("result").getAsJsonObject().get("data1").getAsInt();

            System.out.println("response = " + response.getContentAsString());
            System.out.println("data = " + data1);

            httpClient.stop();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
