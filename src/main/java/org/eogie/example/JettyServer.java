package org.eogie.example;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

public class JettyServer {
    public static void main(String[] args) {
        new JettyServer().start();
    }

    public void start() {
        ServletHandler handler = new ServletHandler();
        handler.addServletWithMapping(JettyServlet.class, "/example");

        Server server = new Server(8080);
        server.setHandler(handler);

        try {
            server.start();
            server.join();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
