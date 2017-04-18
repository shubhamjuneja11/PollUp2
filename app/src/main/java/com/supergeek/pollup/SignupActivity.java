package com.supergeek.pollup;

import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;

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
        email=em.getText().toString();
        password=pass.getText().toString();
        name=nam.getText().toString();
        age=Integer.valueOf(ag.getText().toString());
        temp=spinner.getSelectedItemPosition();
        if(temp==0)
            gender="male";
        else gender="female";

    }

    @Override
    public Loader<SignupModel> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<SignupModel> loader, SignupModel data) {

    }

    @Override
    public void onLoaderReset(Loader<SignupModel> loader) {

    }
}
