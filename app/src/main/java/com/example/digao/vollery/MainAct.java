package com.example.digao.vollery;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import com.example.digao.vollery.Utils.Constantes;
import com.example.digao.vollery.Utils.Functions;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainAct extends AppCompatActivity{
    public static final String REQUEST_TAG = "MainAct";
    private TextView mTextView;
    private Button mButton;
    private Button buttonParams;
    private TextView textOk;
    private TextView textError;
    private Button listVolley;
    public Object retorno;
    Functions functions;
    ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_act);

        textOk = (TextView)findViewById(R.id.textOk);
        textError = (TextView)findViewById(R.id.textError);
        mTextView = (TextView) findViewById(R.id.textView);

        mButton = (Button) findViewById(R.id.button);
        buttonParams = (Button)findViewById(R.id.params);
        listVolley = (Button)findViewById(R.id.listVolley);


        progress = ProgressDialog.show(this, "",
                "Aguarde...", true);

        functions = new Functions(getApplicationContext());



        /////////////////////////////////////////////////////////////
        // SALVAR CONTENT LOCAL MANDANDO PARAMETERS >>>
        Map<String, String> params = new HashMap<String, String>();
        params.put("k", "BC654654FASDFAS5465465");
        params.put("teste", "teste de parametro");
        functions.consultaWebservice(Constantes.urlConteudo, "conteudo", params);
        // <<< SALVAR CONTENT LOCAL MANDANDO PARAMETERS
        /////////////////////////////////////////////////////////////



        new android.os.Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {

                        String lendoJSON = functions.read("conteudo");
                        textOk.setText("OK JSON LOCAL ======>>>> "+lendoJSON);

                        progress.dismiss();

                    }
                },
                300);





        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        buttonParams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainAct.this, ParamsActivity.class);
                intent.putExtra("file", "conteudo.json");
                startActivity(intent);
            }
        });

        listVolley.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), Itens.class);
                startActivity(i);
            }
        });

    }

}
