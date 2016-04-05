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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;


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

        final String url = "http://www.aplicativos.dreamhosters.com/mmgpApp/app-conteudo";
        getStringRequest(url);
        buttonParams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                getStringRequest(url);
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
        }) {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String>  params = new HashMap<String, String>();
                params.put("name", "Alif");
                params.put("domain", "http://itsalif.info");

                return params;
            }
        };
        queue.add(stringRequest);//>>> ele que chama o metodo
    }

}
