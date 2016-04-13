package com.example.digao.vollery.Utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by digao on 12/04/16.
 */
public class Functions {
    Context mContext;

    public Functions(Context context) {
        mContext = context;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public String read(){
        String eol = System.getProperty("line.separator");
        String retorno;
        try (BufferedReader input = new BufferedReader(new InputStreamReader(mContext.openFileInput("conteudo.json")));){
            String line;
            StringBuffer buffer = new StringBuffer();
            while ((line = input.readLine()) != null) {
                buffer.append(line + eol);
            }
            retorno = buffer.toString();
        } catch (Exception e) {
            // we do not care
            retorno = e.getMessage();
        }
        return retorno;
    }


    public void writeFile(String response){
        String fileName = "conteudo.json";
        FileOutputStream outputStream = null;
        try {
            outputStream = mContext.openFileOutput(fileName, Context.MODE_PRIVATE);
            outputStream.write(response.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }





    public void consultaWebservice(String urlConteudo){
        RequestQueue queue = Volley.newRequestQueue(mContext);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlConteudo,
                new Response.Listener<String>() {
                    @Override
                    public String onResponse(String response) {
                        writeFile(response);

                        return response;
                    }
                }, new Response.ErrorListener() {
            @Override
            public String onErrorResponse(VolleyError error) {

                return "erro "+error.toString();
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