package com.supergeek.pollup;

import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.PieChart;
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

    }


    @Override
    public Loader<PostDetailModel> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<PostDetailModel> loader, PostDetailModel data) {

    }

    @Override
    public void onLoaderReset(Loader<PostDetailModel> loader) {

    }
}
