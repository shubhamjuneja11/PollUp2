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
import android.widget.EditText;
import android.widget.Spinner;

import com.supergeek.pollup.Loaders.SignupLoader;
import com.supergeek.pollup.models.SignupModel;

public class SignupActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<SignupModel>{
EditText em,pass,ag,nam;
    String email,password,name,gender;
    int age,temp;
    Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        setup();
    }

    public void setup(){
        em=(EditText)findViewById(R.id.email);
        pass=(EditText)findViewById(R.id.password);
        ag=(EditText)findViewById(R.id.age);
        nam=(EditText)findViewById(R.id.name);
        spinner=(Spinner)findViewById(R.id.spinner);
    }

    public void getValues(){
        Log.e("abcd","90");
        email=em.getText().toString();
        password=pass.getText().toString();
        name=nam.getText().toString();
        age=Integer.valueOf(ag.getText().toString());
        Log.e("email",email);
        Log.e("abcd","91");
        temp=spinner.getSelectedItemPosition();
        if(temp==0)
            gender="male";
        else gender="female";
        Log.e("abcd","92");

    }

    @Override
    public Loader<SignupModel> onCreateLoader(int id, Bundle args) {
        getValues();
        Log.e("abcd","6");
        return  new SignupLoader(this,new SignupModel(email,password,gender,name,age));

    }

    @Override
    public void onLoadFinished(Loader<SignupModel> loader, SignupModel data) {
        Intent intent=new Intent(this,SignInActivity.class);
        startActivity(intent);
    }






    @Override
    public void onLoaderReset(Loader<SignupModel> loader) {

    }
    public void signup(View view){
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
