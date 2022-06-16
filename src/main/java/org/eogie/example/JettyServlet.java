package org.eogie.example;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class JettyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
}
