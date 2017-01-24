package uk.ac.brunel.cs2001.food.server;

/**
 * Created by simonkent on 24/01/2017.
 */

    import javax.servlet.ServletException;
    import javax.servlet.http.HttpServlet;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;
    import java.io.IOException;
    import java.io.PrintWriter;

    public class Hello extends HttpServlet
    {
        protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException
        {
            httpServletResponse.setContentType("text/plain");
            PrintWriter out = httpServletResponse.getWriter();
            out.println("Hello CS2001");
            out.close();
        }
    }
