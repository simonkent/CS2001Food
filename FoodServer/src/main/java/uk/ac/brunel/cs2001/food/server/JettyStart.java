package uk.ac.brunel.cs2001.food.server;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.servlet.ServletHttpContext;

public class JettyStart
{
    public static void main(String[] args) throws Exception
    {
        //A server running at http://localhost:8085 is created
        Server server = new Server();
        server.addListener(":8085");

        ServletHttpContext context = (ServletHttpContext) server.getContext("/");
        context.addServlet("/Hello", "uk.ac.brunel.cs2001.food.server.Hello");

        server.start();
    }
}