package com.supergeek.pollup.Loaders;


import android.content.Context;
import android.net.Uri;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

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
    String u="http://geekyboy.16mb.com/getpolldetail.php";
    String response;
    public static int m1,m2,m3,m4,f1,f2,f3,f4,a11,a12,a13,a14,a21,a22,a23,a24,a31,a32,a33,a34;
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
            Log.e("abcd",response);
            JSONObject jsonObject=new JSONObject(response);
            m1=jsonObject.getInt("male1");
            m2=jsonObject.getInt("male2");
            m3=jsonObject.getInt("male3");
            m4=jsonObject.getInt("male4");

            f1=jsonObject.getInt("female1");
            f2=jsonObject.getInt("female2");
            f3=jsonObject.getInt("female3");
            f4=jsonObject.getInt("female4");

            a11=jsonObject.getInt("age11");
            a12=jsonObject.getInt("age12");
            a13=jsonObject.getInt("age13");
            a14=jsonObject.getInt("age14");

            a21=jsonObject.getInt("age21");
            a22=jsonObject.getInt("age22");
            a23=jsonObject.getInt("age23");
            a24=jsonObject.getInt("age24");

            a31=jsonObject.getInt("age31");
            a32=jsonObject.getInt("age32");
            a33=jsonObject.getInt("age33");
            a34=jsonObject.getInt("age34");

            Log.e("abcd","done");
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
