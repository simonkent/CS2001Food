package com.simonkent.cs2001food;

import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by simonkent on 22/01/2018.
 * A very simple example of using an HTTP connection to retrieve data from a Jetty Server
 */
public class ServletFoodManager implements FavouriteFoodManager {

    // This defines the server that the mobile is going to connect to
    // Be careful . . if you use localhost, the device will try to connect to itself.
    // Use the ip address on the network that both your server and your device are connected.
    // If you want to use a real Android Device, and connect to a public internet address, have a look at using ngrok
    private static final String IP = "XXX.XXX.XXX.XXX:8085";

    private String food = "";
    @Override
    public String getFavouriteFood()  {
        AsyncTask<ServletFoodManager, Void, String> task = new MakeServletCall().execute(this);

        while (food==null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return food;
    }

    private class MakeServletCall extends AsyncTask<ServletFoodManager, Void, String> {
        ServletFoodManager manager;
        protected String doInBackground(ServletFoodManager... manager) {
            this.manager = manager[0];
            URL url = null;
            try {
                url = new URL("http://" + IP + "/JDBC");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            HttpURLConnection urlConnection = null;
            try {
                urlConnection = (HttpURLConnection) url.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                InputStream in = null;
                try {
                    in = new BufferedInputStream(urlConnection.getInputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                String returnData = readStream(in);
                return returnData;

            } finally {
                urlConnection.disconnect();
            }
        }

        protected void onProgressUpdate(Integer... progress) {

        }

        protected void onPostExecute(String result) {
            manager.food = result;
        }

    }

    private String readStream(InputStream is) {
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            int i = is.read();
            while(i != -1) {
                bo.write(i);
                i = is.read();
            }
            return bo.toString();
        } catch (IOException e) {
            return "";
        }
    }
}
