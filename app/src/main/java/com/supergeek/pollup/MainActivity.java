package com.supergeek.pollup;

import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.supergeek.pollup.models.NewPostModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<NewPostModel>{
RecyclerView recyclerView;
    ArrayList<NewPostModel> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=(RecyclerView)findViewById(R.id.recycler);


    }

    @Override
    public Loader<NewPostModel> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<NewPostModel> loader, NewPostModel data) {

    }

    @Override
    public void onLoaderReset(Loader<NewPostModel> loader) {

    }
}
