package com.supergeek.pollup.Loaders;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;
import android.widget.Toast;

import com.supergeek.pollup.MainActivity;
import com.supergeek.pollup.models.SignInModel;

import org.json.JSONException;
import org.json.JSONObject;

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

public class SignInLoader extends AsyncTaskLoader<SignInModel> {
    String ur="http://geekyboy.16mb.com/pollsignin.php";
    String response;
    SignInModel model;
    URL url;
    Context context;
    public static int success;
    public SignInLoader(Context context,SignInModel model) {
        super(context);
        this.context=context;
        this.model=model;
    }

    @Override
    public SignInModel loadInBackground() {
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
                    .appendQueryParameter("email", model.getEmail())
                    .appendQueryParameter("password", model.getPassword());
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
            checkstatus();
            Log.e("abcd","2");
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("abcd",e.getMessage());
        }
        return null;
    }
    public  String readfromstream(InputStream inputstream)  {
        Log.e("abcd","103");
        StringBuilder string=new StringBuilder();
        if(inputstream!=null) {
            InputStreamReader inputreader = new InputStreamReader(inputstream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputreader);
            String line = null;
            try {
                line = reader.readLine();
                while (line != null) {
                    string.append(line);
                    line = reader.readLine();
                }
                Log.e("abcd","200");
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("abcd","201");
            }

        }
        return string.toString();

    }
    public void checkstatus(){
        try {Log.e("abcdef",response);
            JSONObject jsonObject=new JSONObject(response);
             success=jsonObject.getInt("success");

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("abcd",e.getMessage());
        }
    }
}
