package uk.ac.brunel.cs2001.food.server;

/**
 * Created by simonkent on 24/01/2017.
 *
 */

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 A simple Servlet that demonstrates how a servlet works.
 */
public class HelloJDBC extends HttpServlet
{
    private static final Properties properties = new Properties();

    private static String IP;
    private static String DB;
    private static String USERNAME;
    private static String PASSWORD;

    public HelloJDBC() {
        InputStream propStream = HelloJDBC.class.getClassLoader().getResourceAsStream("db.properties");

        try {
            properties.load(propStream);
        } catch (IOException e) {
            System.err.println("Cannot Load db.properties.  Please make sure you have defined your database properties in db.properties.");
        }

        IP = properties.getProperty("uk.ac.brunel.food.server.mysql_ip");
        DB = properties.getProperty("uk.ac.brunel.food.server.mysql_db");
        USERNAME = properties.getProperty("uk.ac.brunel.food.server.mysql_username");
        PASSWORD = properties.getProperty("uk.ac.brunel.food.server.mysql_password");
    }

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

                String url = "jdbc:mysql://" + IP + "/" + DB +"?useSSL=false";
                Logger.getLogger(this.getClass().getName()).info("Connecting to url");

                Connection con = DriverManager.getConnection(url, USERNAME, PASSWORD);
                Statement select = con.createStatement();

                // Execute a query
                rs = select.executeQuery("SELECT * FROM colour");

                while (rs.next()) {
                    String name = rs.getString("colour");
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

