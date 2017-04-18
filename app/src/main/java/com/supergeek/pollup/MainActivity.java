package com.supergeek.pollup;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.supergeek.pollup.Loaders.NewPostLoader;
import com.supergeek.pollup.adapters.NewPostAdapter;
import com.supergeek.pollup.models.NewPostModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<NewPostModel>{
RecyclerView recyclerView;
    ArrayList<NewPostModel> data;
    NewPostAdapter adapter;
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
}
