package com.simonkent.cs2001food;

import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by simonkent on 22/01/2018.
 */
public class JDBCFoodManager implements FavouriteFoodManager {

    private String food = "";
    @Override
    public String getFavouriteFood()  {
        AsyncTask<JDBCFoodManager, Void, String> task = new MakeServletCall().execute(this);

        while (food==null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return food;
    }

    private class MakeServletCall extends AsyncTask<JDBCFoodManager, Void, String> {
        JDBCFoodManager manager;
        protected String doInBackground(JDBCFoodManager... manager) {
            this.manager = manager[0];
            URL url = null;
            try {
                url = new URL("http://8c46f192.ngrok.io/JDBC");
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

                String bob = readStream(in);
                return bob;

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
