package com.example.digao.vollery;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.digao.vollery.Utils.CustomJSONObjectRequest;
import com.example.digao.vollery.Utils.CustomVolleyRequestQueue;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


//public class MainAct extends AppCompatActivity implements Response.Listener, Response.ErrorListener {
public class MainAct extends AppCompatActivity{
    public static final String REQUEST_TAG = "MainAct";
    private TextView mTextView;
    private Button mButton;
    private Button buttonParams;
    private TextView textOk;
    private TextView textError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_act);

        textOk = (TextView)findViewById(R.id.textOk);
        textError = (TextView)findViewById(R.id.textError);
        mTextView = (TextView) findViewById(R.id.textView);
        mButton = (Button) findViewById(R.id.button);

        buttonParams = (Button)findViewById(R.id.params);

        final String url = "http://api.androidhive.info/volley/person_object.json";

        buttonParams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getStringRequest(url);
            }
        });




    }

    private void getStringRequest(String url) {
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        textOk.setText("OK ======>>>> "+response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                    textError.setText("erro "+error.toString());
            }
        });

    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        mQueue = CustomVolleyRequestQueue.getInstance(this.getApplicationContext())
//                .getRequestQueue();
//        String url = "http://www.aplicativos.dreamhosters.com/mmgpApp/app-conteudo";
//        final CustomJSONObjectRequest jsonRequest = new CustomJSONObjectRequest(Request.Method
//                .POST, url,
//                new JSONObject(), this, this);
//        jsonRequest.setTag(REQUEST_TAG);
//
//        mButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mQueue.add(jsonRequest);
//            }
//        });
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        if (mQueue != null) {
//            mQueue.cancelAll(REQUEST_TAG);
//        }
//    }
//
//    @Override
//    public void onResponse(Object response) {
//        Log.i("", "================>>>> "+response);
//        mTextView.setText("Response is: " + response);
//        try {
//            mTextView.setText(mTextView.getText() + "\n\n" + ((JSONObject) response).getString
//                    ("name"));
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void onErrorResponse(VolleyError error) {
//        mTextView.setText(error.getMessage());
//    }
}
