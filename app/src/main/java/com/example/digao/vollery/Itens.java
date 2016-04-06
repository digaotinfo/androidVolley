package com.example.digao.vollery;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.*;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.digao.vollery.Adapter.ItemAdapter;
import com.example.digao.vollery.Controller.AppController;
import com.example.digao.vollery.Model.Item;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Itens extends AppCompatActivity {
    private static String url = "http://api.androidhive.info/json/movies.json";
    private ProgressDialog dialog;
    private List<Item> array = new ArrayList<Item>();
    private ListView listView;
    private ItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.itens_act);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        listView = (ListView) findViewById(R.id.listaTodosItens);
        adapter = new ItemAdapter(this, array);
        listView.setAdapter(adapter);

        dialog = new ProgressDialog(this);
        dialog.setMessage("Carregando...");
        dialog.show();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                hideDialog();
                ///////// parsing
                for (int i=0; i<response.length(); i++){
                    try {
                        JSONObject obj = response.getJSONObject(i);
                        Item item = new Item();
                        item.setTitle(obj.getString("title"));
                        item.setImage(obj.getString("image"));
                        item.setRate(((Number) obj.get("rating")).doubleValue());
                        item.setYear(obj.getInt("releaseYear"));


                        //////// genero Array json
                        JSONArray genereArray = obj.getJSONArray("genre");
                        ArrayList<String> genere = new ArrayList<String>();
                        for(int j=0; j<genereArray.length(); j++){
                            genere.add((String) genereArray.get(j));
                            item.setGenre(genere);


                            //// add para array
                            array.add(item);
                        }


                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                }
                adapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        AppController.getmInstance().addToRequesQueue(jsonArrayRequest);


    }

    public void hideDialog(){
        if(dialog != null){
            dialog.dismiss();
            dialog = null;
        }
    }


}
