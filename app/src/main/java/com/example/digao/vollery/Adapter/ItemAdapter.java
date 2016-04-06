package com.example.digao.vollery.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.digao.vollery.Controller.AppController;
import com.example.digao.vollery.Model.Item;
import com.example.digao.vollery.R;

import java.util.List;

/**
 * Created by digao on 05/04/16.
 */
public class ItemAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Item> itens;
    ImageLoader imageLoader = AppController.getmInstance().getmImageLoader();
    public ItemAdapter(Activity activity, List<Item> itens){
        this.activity=activity;
        this.itens=itens;
    }

    @Override
    public int getCount() {
        return itens.size();
    }

    @Override
    public Object getItem(int position) {
        return itens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(inflater == null){
            inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView==null){
            convertView = inflater.inflate(R.layout.itens_act, null);
        }

        if(imageLoader==null){
            imageLoader=AppController.getmInstance().getmImageLoader();
            NetworkImageView imageView = (NetworkImageView)convertView.findViewById(R.id.image_view);
            TextView title = (TextView)convertView.findViewById(R.id.tv_title);
            TextView genere = (TextView)convertView.findViewById(R.id.tv_genre);
            TextView rate = (TextView)convertView.findViewById(R.id.tv_rate);
            TextView year = (TextView)convertView.findViewById(R.id.tv_year);

            // pegando dados para a linha
            Item item = itens.get(position);
            imageView.setImageUrl(item.getImage(), imageLoader);

            //////
            title.setText(item.getTitle());
            rate.setText(String.valueOf(item.getRate()));
            String genereStr = "";
            for(String str: item.getGenre()){
                genereStr += str +",";
            }
            genereStr = genereStr.length() >0 ? genereStr.substring(0, genereStr.length()-2) : genereStr;
            genere.setText(genereStr);

            year.setText(String.valueOf(item.getYear()));

        }

        return convertView;
    }
}
