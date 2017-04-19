package com.supergeek.pollup;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.BoolRes;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.supergeek.pollup.Loaders.NewPostLoader;
import com.supergeek.pollup.adapters.NewPostAdapter;
import com.supergeek.pollup.models.NewPostModel;
import com.supergeek.pollup.models.PostDetailModel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<NewPostModel>{
RecyclerView recyclerView;
    ArrayList<NewPostModel> data;
    NewPostAdapter adapter;
    String response;
    int o,position,id;
    int age=21;
    String gender="M";
    String ur="http://geekyboy.16mb.com/polldetail.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=(RecyclerView)findViewById(R.id.recycler);
        data=new ArrayList<>();
        adapter=new NewPostAdapter(data);


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        startloading();

    }

    @Override
    public Loader<NewPostModel> onCreateLoader(int id, Bundle args) {
        return new NewPostLoader(this,data);
    }

    @Override
    public void onLoadFinished(Loader<NewPostModel> loader, NewPostModel data) {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(Loader<NewPostModel> loader) {

    }
    public void startloading(){
        ConnectivityManager connectivity=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo network=connectivity.getActiveNetworkInfo();
        if(network!=null&&network.isConnected())
        {
            try { Log.e("abcd","8");
                LoaderManager loaderManager = getSupportLoaderManager();
                loaderManager.restartLoader(1, null, this).forceLoad();
            }
            catch (Exception e){}

        }
    }
    public void setoption(View view){
        int id=view.getId();

        switch (id)
        {
            case R.id.option1:o=1;
                break;
            case R.id.option2:o=2;
                break;
            case R.id.option3:o=3;
                break;
            case R.id.option4:o=4;
                break;

        }
        senddata();
    }
    public void senddata(){
         position=NewPostAdapter.position;
       //  id=data.get(position).getId();
        new MyWorker().execute();

    }

    class MyWorker extends AsyncTask<Void,Void,Boolean>{

        @Override
        protected Boolean doInBackground(Void... params) {
            URL url= null;
            try {
                url = new URL(ur);
                connect(url);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        return true;
        }
    }
    public boolean connect(URL url){
        try{
            PostDetailModel option=new PostDetailModel(id,age,o,gender);
            InputStream inputstream=null;
            HttpURLConnection connection=null;
            connection=(HttpURLConnection)url.openConnection();
            connection.setRequestMethod("POST");
            connection.setReadTimeout(10000);
            connection.setReadTimeout(15000);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            Uri.Builder builder = new Uri.Builder()
                    .appendQueryParameter("id",String.valueOf(option.getId()))
                    .appendQueryParameter("gender",String.valueOf(option.getGender()))
             .appendQueryParameter("age",String.valueOf(option.getAge()))
                    .appendQueryParameter("option",String.valueOf(option.getOption()));

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


        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
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
    public void chechstats(View view){
        Intent intent =new Intent(this,StatsActivity.class);
        intent.putExtra("id",id);
        startActivity(intent);
    }
}
