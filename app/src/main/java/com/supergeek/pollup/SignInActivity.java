package com.supergeek.pollup;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.supergeek.pollup.Loaders.SignInLoader;
import com.supergeek.pollup.models.SignInModel;

public class SignInActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks{
AutoCompleteTextView textView;
    EditText text;
    String email,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        setup();
    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        getValues();
        return new SignInLoader(this,new SignInModel(email,password));
    }

    @Override
    public void onLoadFinished(Loader loader, Object data) {
            if(SignInLoader.success==1){
                Intent intent=new Intent(this,MainActivity.class);
                startActivity(intent);
            }
    }

    @Override
    public void onLoaderReset(Loader loader) {

    }
    public void setup(){
        textView=(AutoCompleteTextView)findViewById(R.id.email);
        text=(EditText)findViewById(R.id.password);

    }
    public void signin(View view){
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
    public void getValues(){
        email=textView.getText().toString();
        password=text.getText().toString();
    }
}
