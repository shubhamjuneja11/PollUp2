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
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.supergeek.pollup.Loaders.PostDetailLoader;
import com.supergeek.pollup.models.PostDetailModel;

import java.util.ArrayList;

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
        loadonpiechart();
    }

    @Override
    public void onLoaderReset(Loader<PostDetailModel> loader) {

    }

    public void loadonpiechart(){

Log.e("hil","1");
        float a,b,c;
        b=PostDetailLoader.f1;
        a=PostDetailLoader.m1;
        c=b+a;

        a=a*100/c;
        b=b*100/c;
Log.e("hil",a+"");
        Log.e("hil",b+"");
        ArrayList<Entry> yvalues = new ArrayList<Entry>();
        yvalues.add(new Entry(a, 0));
        yvalues.add(new Entry(b, 1));


        PieDataSet dataSet = new PieDataSet(yvalues, "Election Results");

        ArrayList<String> xVals = new ArrayList<String>();

        xVals.add("Male");
        xVals.add("Female");

        PieData data = new PieData(xVals, dataSet);

        data.setValueFormatter(new PercentFormatter());
        pieChart.setData(data);
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        Log.e("hil","2");
    }
}
