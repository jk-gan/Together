package com.example.micky.together;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;

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
    }
}
