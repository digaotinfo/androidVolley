package com.example.digao.vollery.Controller;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.example.digao.vollery.Utils.BitmapCache;

/**
 * Created by digao on 05/04/16.
 */
public class AppController extends Application {
    public static final String TAG = AppController.class.getSimpleName();
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    private static AppController mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance=this;
    }

    public static AppController getmInstance() {
        return mInstance;
    }

    public RequestQueue getmRequestQueue() {
        if(mRequestQueue==null){
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return mRequestQueue;
    }

    public ImageLoader getmImageLoader() {
        getmRequestQueue();
        if(mImageLoader==null){
            mImageLoader = new ImageLoader(this.getmRequestQueue(), new BitmapCache());
        }
        return this.mImageLoader;
    }

    public <T> void addToRequesQueue(Request<T> request, String tag){
        request.setTag((TextUtils.isEmpty(tag)) ? TAG : tag);
        getmRequestQueue().add(request);
    }

    public <T> void addToRequesQueue(Request<T> request){
        request.setTag(TAG);
        getmRequestQueue().add(request);
    }

    public void cancelPedingRequest(Object tag){
        if( mRequestQueue != null ){
            mRequestQueue.cancelAll(tag);
        }
    }



}
