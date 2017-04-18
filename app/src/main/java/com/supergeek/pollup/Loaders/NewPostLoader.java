package com.supergeek.pollup.Loaders;


import android.content.Context;
import android.net.Uri;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.supergeek.pollup.models.NewPostModel;

import org.json.JSONArray;
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
import java.util.ArrayList;

/**
 * Created by Junejas on 4/19/2017.
 */

public class NewPostLoader extends AsyncTaskLoader<NewPostModel> {
    String ur="http://geekyboy.16mb.com/displaypost.php";
    URL url;
    String response;
    ArrayList<NewPostModel> data;
    public NewPostLoader(Context context, ArrayList<NewPostModel> data) {
        super(context);
        this.data=data;
    }

    @Override
    public NewPostModel loadInBackground() {
        try {
            Log.e("abcd", "1");
            url = new URL(ur);
            InputStream inputstream;
            HttpURLConnection connection;
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setReadTimeout(10000);
            connection.setReadTimeout(15000);
            connection.setDoInput(true);
            connection.setDoOutput(true);


            connection.connect();

            inputstream = connection.getInputStream();
            response = readfromstream(inputstream);
            loaddata();
            Log.e("abcd", "2");
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("abcd", e.getMessage());
        }
        return null;
    }
    public  String readfromstream(InputStream inputstream) {
        Log.e("abcd", "103");
        StringBuilder string = new StringBuilder();
        if (inputstream != null) {
            InputStreamReader inputreader = new InputStreamReader(inputstream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputreader);
            String line = null;
            try {
                line = reader.readLine();
                while (line != null) {
                    string.append(line);
                    line = reader.readLine();
                }
                Log.e("abcd", "200");
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("abcd", "201");
            }

        }
        return string.toString();

    }
    public void loaddata(){
        try {
            Log.e("myres",response);
            JSONObject object=new JSONObject(response);
            JSONArray array1=object.getJSONArray("posts");
            for(int i=0;i<array1.length();i++){
                JSONObject array=array1.getJSONObject(i);
                String ques;
                String option1;
                String option2;
                String option3;
                String option4;
                String author;
                int id=array.getInt("id");
                ques=array.getString("ques");
                option1=array.getString("option1");
                option2=array.getString("option2");
                option3=array.getString("option3");
                option4=array.getString("option4");
                author=array.getString("author");

                data.add(new NewPostModel(id,ques,option1,option2,option3,option4,author));

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    }
