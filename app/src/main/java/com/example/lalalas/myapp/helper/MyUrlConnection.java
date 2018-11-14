package com.example.lalalas.myapp.helper;

import android.util.Log;

import com.example.lalalas.myapp.model.AutentifikacijaResultVM;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyUrlConnection
{
    public enum HttpMethod
    {
        GET, POST, HEAD, OPTIONS, PUT, DELETE, TRACE
    }


    public static MyApiResult request(String urlString, HttpMethod httpMethod, String postData, String contentType) {

        HttpURLConnection connection = null;

        String charset = "UTF-8";

        try {
            URL url = new URL(urlString);

            connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);

            connection.setRequestProperty("Content-Type", contentType);
            connection.setRequestProperty("Accept", contentType);
            connection.setRequestProperty("Accept-Charset", charset);

            AutentifikacijaResultVM korisnik = MySession.getKorisnik();
            connection.setRequestProperty("MyAuthToken", korisnik !=null? korisnik.token:"");

            connection.setRequestMethod(httpMethod.toString());
            connection.setUseCaches(false);
            connection.setAllowUserInteraction(false);

            connection.setDoOutput(false);

            // Send the post body
            if (postData != null) {
                connection.setDoOutput(true);
                byte[] outputBytes = postData.getBytes(charset);
                OutputStream os = connection.getOutputStream();
                os.write(outputBytes);
                os.flush();
                os.close();
            }

            int statusCode = connection.getResponseCode();

            if (statusCode ==  200) {

                InputStream inputStream = new BufferedInputStream(connection.getInputStream());

                String response = convertToString(inputStream);

                return  MyApiResult.OK(response);
            } else {
                // Status code is not 200
                // Do something to handle the error
                //    InputStream inputStream = new BufferedInputStream(connection.getErrorStream());
                String response = connection.getResponseMessage();
                return MyApiResult.Error(statusCode, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return MyApiResult.Error(0, e.getMessage());
        }
        finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }



    private static String convertToString(InputStream in) throws IOException {
        BufferedReader reader = null;
        StringBuilder response = new StringBuilder();

        reader = new BufferedReader(new InputStreamReader(in));
        String line = "";
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }

        reader.close();

        return response.toString();
    }
}

