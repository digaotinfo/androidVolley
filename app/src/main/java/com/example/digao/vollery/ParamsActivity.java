package com.example.digao.vollery;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.*;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class ParamsActivity extends AppCompatActivity {
    final String url = "http://www.aplicativos.dreamhosters.com/mmgpApp/app-conteudo";

    private TextView textOk;
    private TextView textError;

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.params_act);

        textOk = (TextView)findViewById(R.id.textOk);
        textError = (TextView)findViewById(R.id.textError);

        Intent intent = getIntent();

        String eol = System.getProperty("line.separator");
        try (BufferedReader input = new BufferedReader(new InputStreamReader(openFileInput("conteudo.json")));){
            String line;
            StringBuffer buffer = new StringBuffer();
            while ((line = input.readLine()) != null) {
                buffer.append(line + eol);
            }
        } catch (Exception e) {
            // we do not care
        }


//        textOk.setText(json);
    }

    private void getStringRequest(String url) {
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public String onResponse(String response) {
                        textOk.setText("OK ======>>>> "+response);
                        return response;
                    }
                }, new Response.ErrorListener() {
            @Override
            public String onErrorResponse(VolleyError error) {
                textError.setText("erro "+error.toString());
                return null;
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
