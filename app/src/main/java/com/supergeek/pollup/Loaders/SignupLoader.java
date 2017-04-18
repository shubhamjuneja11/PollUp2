package com.supergeek.pollup.Loaders;


import android.content.Context;
import android.net.Uri;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.supergeek.pollup.models.SignupModel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Created by Junejas on 4/19/2017.
 */

public class SignupLoader extends AsyncTaskLoader<SignupModel> {
    SignupModel model;
    String ur="http://geekyboy.16mb.com/pollsignup.php";
    String response;
   URL url;
    public SignupLoader(Context context,SignupModel model) {
        super(context);
        this.model=model;
    }

    @Override
    public SignupModel loadInBackground() {

        try {
            //response=MyLoader.makerequest(new URL(url));
            Log.e("abcd","1");
            url=new URL(ur);
            InputStream inputstream;
            HttpURLConnection connection;
            connection=(HttpURLConnection)url.openConnection();
            connection.setRequestMethod("POST");
            connection.setReadTimeout(10000);
            connection.setReadTimeout(15000);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            Uri.Builder builder = new Uri.Builder()
                    .appendQueryParameter("name",model.getName())
                    .appendQueryParameter("email", model.getEmail())
                    .appendQueryParameter("password", model.getPassword())
             .appendQueryParameter("age", String.valueOf(model.getAge()))
             .appendQueryParameter("gender", model.getGender());
            String query = builder.build().getEncodedQuery();

            OutputStream os = connection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(query);
            writer.flush();
            writer.close();
            os.close();

            connection.connect();

            inputstream=connection.getInputStream();
            response=readfromstream(inputstream);
            Log.e("abcd","2");
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("abcd",e.getMessage());
        }
        return null;
    }
    public static String readfromstream(InputStream inputstream) throws IOException {

        StringBuilder string=new StringBuilder();
        if(inputstream!=null) {
            InputStreamReader inputreader = new InputStreamReader(inputstream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputreader);
            String line = reader.readLine();
            while (line != null) {
                string.append(line);
                line = reader.readLine();
            }
        }
        return string.toString();
    }
}
