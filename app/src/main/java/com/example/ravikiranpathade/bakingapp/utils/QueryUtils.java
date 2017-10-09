package com.example.ravikiranpathade.bakingapp.utils;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.Buffer;
import java.nio.charset.Charset;

/**
 * Created by ravikiranpathade on 10/9/17.
 */

public class QueryUtils {

    public static final String QUERY_URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";


    public QueryUtils() {
    }

    public static URL createURL(String URL){
        //URL = QUERY_URL;
        URL url = null;

        try{
            url = new URL(URL);

        }catch (Exception e){
            e.printStackTrace();
        }
        return url;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {

        StringBuilder output = new StringBuilder();

        if(inputStream!=null){
            InputStreamReader isReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));

            BufferedReader bufferdReader = new BufferedReader(isReader);

            String line = bufferdReader.readLine();



            while(bufferdReader.readLine()!=null){
                //Log.d("Line "," Entered");
              
                output.append(line);
                line = bufferdReader.readLine();

            }
        Log.d("Reponse ", output.toString());


        }
        return output.toString();
    }

    public static String makeHTTPrequest(URL url) throws IOException {
        String response = null;
        Log.d("URL",url.toString());
        if(url == null){
            return response;
        }
        HttpURLConnection connection = null;

        InputStream inputStream = null;

        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            if(connection.getResponseCode()==200){
                inputStream = connection.getInputStream();
                response = readFromStream(inputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(connection!=null){
                connection.disconnect();
            }
            if(inputStream!=null){
                inputStream.close();
            }
        }

        Log.d("Response ",response);
        return response;
    }


}


