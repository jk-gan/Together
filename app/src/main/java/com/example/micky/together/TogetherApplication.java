package com.example.micky.together;

import android.app.Application;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParsePush;
import com.parse.SaveCallback;


/**
 * Created by JKGan on 5/17/15.
 */
public class TogetherApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

		/*
		 * Add Parse initialization code here
		 */

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "XRGu8hmElf1tVf2JdNydy5GJey5HhUJ7BKSQMAF4", "upT7cr6CUEqu2Jylx2fOUJClImfyY7N48OXJBDi5");


        ParseACL defaultACL = new ParseACL();

        // If you would like all objects to be private by default, remove this
        // line.
        defaultACL.setPublicReadAccess(true);

        ParseACL.setDefaultACL(defaultACL, true);

        ParsePush.subscribeInBackground("", new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Log.d("com.parse.push", "successfully subscribed to the broadcast channel.");
                } else {
                    Log.e("com.parse.push", "failed to subscribe for push", e);
                }
            }
        });
    }
}
