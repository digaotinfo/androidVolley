package com.example.digao.vollery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import com.example.digao.vollery.Utils.Constantes;
import com.example.digao.vollery.Utils.Functions;

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





        functions = new Functions(getApplicationContext());
        functions.consultaWebservice(Constantes.urlConteudo);

        String lendoJSON = functions.read();
        textOk.setText("OK JSON LOCAL ======>>>> "+lendoJSON);

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
