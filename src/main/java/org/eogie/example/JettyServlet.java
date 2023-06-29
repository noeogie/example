package org.eogie.example;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

public class JettyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            JsonArray list = new JsonArray();
            list.add(123);
            list.add(456);
            list.add(789);

            JsonObject count = new JsonObject();
            count.addProperty("count", 100);

            JsonObject res = new JsonObject();
            res.add("result", count);
            res.add("list", list);

            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println(res);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            String body = request.getReader().lines().collect(Collectors.joining());
            JsonObject req = JsonParser.parseString(body).getAsJsonObject();

            JsonObject res = new JsonObject();
            res.add("result", req);

            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println(res);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
