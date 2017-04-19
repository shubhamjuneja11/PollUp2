package com.supergeek.pollup;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.supergeek.pollup.Loaders.AskPollLoader;
import com.supergeek.pollup.models.NewPostModel;

public class AskPollActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks {
EditText ques,o1,o2,o3,o4;
    String q,op1,op2,op3,op4;
    String author="shubham";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_poll);
        ques=(EditText)findViewById(R.id.ques);
        o1=(EditText)findViewById(R.id.option1);
        o2=(EditText)findViewById(R.id.option2);
        o3=(EditText)findViewById(R.id.option3);
        o4=(EditText)findViewById(R.id.option4);

    }
    public void submit(View view){


        q=ques.getText().toString();
        op1=o1.getText().toString();
        op2=o2.getText().toString();
        op3=o3.getText().toString();
        op4=o4.getText().toString();

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
    public Loader onCreateLoader(int id, Bundle args) {
        return new AskPollLoader(this,new NewPostModel(q,op1,op2,op3,op4,author));
    }

    @Override
    public void onLoadFinished(Loader loader, Object data) {
        onBackPressed();
    }

    @Override
    public void onLoaderReset(Loader loader) {

    }
}
