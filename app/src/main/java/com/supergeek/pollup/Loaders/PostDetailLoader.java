package com.supergeek.pollup.Loaders;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.net.Uri;

import com.supergeek.pollup.models.PostDetailModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import static com.supergeek.pollup.Loaders.SignupLoader.readfromstream;

/**
 * Created by Junejas on 4/19/2017.
 */

public class PostDetailLoader extends AsyncTaskLoader<PostDetailModel> {
    String u="http://geekyboy.16mb.com/polldetail.php";
    String response;
    int m1,m2,m3,m4,f1,f2,f3,f4,a1,a2,a3;
    public PostDetailLoader(Context context) {
        super(context);
    }

    @Override
    public PostDetailModel loadInBackground() {
        try {
            URL url=new URL(u);
            InputStream inputstream;
            HttpURLConnection connection;
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setReadTimeout(10000);
            connection.setReadTimeout(15000);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            Uri.Builder builder = new Uri.Builder()
                    .appendQueryParameter("id","1");

            String query = builder.build().getEncodedQuery();

            OutputStream os = connection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(query);
            writer.flush();
            writer.close();
            os.close();
            connection.connect();

            inputstream = connection.getInputStream();
            response = readfromstream(inputstream);
            loaddata();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void  loaddata(){

        try {
            JSONObject jsonObject=new JSONObject(response);
            m1=jsonObject.getInt("male1");
            m2=jsonObject.getInt("male2");
            m3=jsonObject.getInt("male3");
            m4=jsonObject.getInt("male4");

            f1=jsonObject.getInt("female1");
            f2=jsonObject.getInt("female2");
            f3=jsonObject.getInt("female3");
            f4=jsonObject.getInt("female4");

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
