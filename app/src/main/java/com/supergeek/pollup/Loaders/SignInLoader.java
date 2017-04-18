package com.supergeek.pollup.Loaders;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.supergeek.pollup.models.SignInModel;

/**
 * Created by Junejas on 4/19/2017.
 */

public class SignInLoader extends AsyncTaskLoader<SignInModel> {
    public SignInLoader(Context context) {
        super(context);
    }

    @Override
    public SignInModel loadInBackground() {
        return null;
    }
}
