package com.supergeek.pollup;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

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
    PieChart pieChart,pieChart2;
    int option=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        pieChart    = (PieChart) findViewById(R.id.piechart);
        pieChart2=(PieChart)findViewById(R.id.piechart2);

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
        float a,b,c,d,e,f,g;
        a=b=c=d=e=f=g=0;
       switch (option){
           case 1:
            a = PostDetailLoader.m1;
               b = PostDetailLoader.f1;
               d=PostDetailLoader.a11;
               e=PostDetailLoader.a21;
               f=PostDetailLoader.a31;

               break;
           case 2:
               a = PostDetailLoader.m2;
               b = PostDetailLoader.f2;
               d=PostDetailLoader.a12;
               e=PostDetailLoader.a22;
               f=PostDetailLoader.a32;
               break;
           case 3:
               a = PostDetailLoader.m3;
               b = PostDetailLoader.f3;
               d=PostDetailLoader.a13;
               e=PostDetailLoader.a23;
               f=PostDetailLoader.a33;
               break;
           case 4:
               a = PostDetailLoader.m4;
               b = PostDetailLoader.f4;
               d=PostDetailLoader.a14;
               e=PostDetailLoader.a24;
               f=PostDetailLoader.a34;
               break;

        }


        c = b + a;
        g=d+e+f;

        a=(a*100)/c;
        b=(b*100)/c;
        d=(d*100)/g;
        e=(e*100)/g;
        f=(f*100)/g;

Log.e("hil",a+"");
        Log.e("hil",b+"");
        ArrayList<Entry> yvalues = new ArrayList<Entry>();
        yvalues.add(new Entry(a, 0));
        yvalues.add(new Entry(b, 1));


        PieDataSet dataSet = new PieDataSet(yvalues, "Gender");

        ArrayList<String> xVals = new ArrayList<String>();

        xVals.add("Male");
        xVals.add("Female");

        PieData data = new PieData(xVals, dataSet);

        data.setValueFormatter(new PercentFormatter());
        pieChart.setData(data);
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);



        ArrayList<Entry> yvalues2 = new ArrayList<Entry>();
        yvalues2.add(new Entry(d, 0));
        yvalues2.add(new Entry(e, 1));
        yvalues2.add(new Entry(f, 2));


        PieDataSet dataSet2 = new PieDataSet(yvalues2, "Age group");

        ArrayList<String> xVals2 = new ArrayList<String>();

        xVals2.add("Less than 25");
        xVals2.add("25-50");
        xVals2.add("Above 50");
        PieData data2 = new PieData(xVals2, dataSet2);

        data2.setValueFormatter(new PercentFormatter());
        pieChart2.setData(data2);
        dataSet2.setColors(ColorTemplate.COLORFUL_COLORS);

        Log.e("hil","2");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.optionsmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
         super.onOptionsItemSelected(item);
        switch(item.getItemId()){
            case R.id.option1:
                option=1;
                break;
            case R.id.option2:
                option=2;
                break;
            case R.id.option3:
                option=3;
                break;
            case R.id.option4:
                option=4;
                break;

        }
        loadonpiechart();
return true;
    }
}
