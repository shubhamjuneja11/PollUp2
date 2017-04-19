package com.supergeek.pollup;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.github.mikephil.charting.charts.PieChart;
import com.supergeek.pollup.Loaders.PostDetailLoader;
import com.supergeek.pollup.models.PostDetailModel;

public class StatsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<PostDetailModel>{
    PieChart pieChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        pieChart    = (PieChart) findViewById(R.id.piechart);

        setpiechart();
    }

    public void setpiechart(){
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


    @Override
    public Loader<PostDetailModel> onCreateLoader(int id, Bundle args) {
       return new PostDetailLoader(this);

    }

    @Override
    public void onLoadFinished(Loader<PostDetailModel> loader, PostDetailModel data) {

    }

    @Override
    public void onLoaderReset(Loader<PostDetailModel> loader) {

    }
}
