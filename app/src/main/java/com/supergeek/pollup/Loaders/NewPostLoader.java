package com.supergeek.pollup.Loaders;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.supergeek.pollup.models.NewPostModel;

/**
 * Created by Junejas on 4/19/2017.
 */

public class NewPostLoader extends AsyncTaskLoader<NewPostModel> {
    public NewPostLoader(Context context) {
        super(context);
    }

    @Override
    public NewPostModel loadInBackground() {
        return null;
    }
}
