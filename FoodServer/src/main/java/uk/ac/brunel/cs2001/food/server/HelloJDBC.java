package uk.ac.brunel.cs2001.food.server;

/**
 * Created by simonkent on 24/01/2017.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 A simple Servlet that demonstrates how a servlet works.
 */
public class HelloJDBC extends HttpServlet
{
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException
    {
        httpServletResponse.setContentType("text/plain");
        PrintWriter out = httpServletResponse.getWriter();
        out.println(pullDataFromDatabase());
        out.close();
    }

    private String pullDataFromDatabase() {


            String returnString = "My favourite colour is ";
            try {
                //Statement stmt;
                ResultSet rs;

                //Register the JDBC driver for MySQL
                Class.forName("com.mysql.jdbc.Driver");

                String url = "jdbc:mysql://134.83.83.25:47000/grp36_st";

                Connection con = DriverManager.getConnection(url, "l2grp36", "l2grp36");
                Statement select = con.createStatement();

                // Execute a query
                rs = select.executeQuery("SELECT * FROM COLOUR");

                while (rs.next()) {
                    String name = rs.getString("CL_NAME");
                    returnString += name;
                }
            } catch (ClassNotFoundException e) {
                returnString = "Something went wrong!";
            } catch (SQLException e) {
                returnString = "Something went wrong with database: ";
                returnString += e.getLocalizedMessage();
            }

        return returnString;
    }
}

