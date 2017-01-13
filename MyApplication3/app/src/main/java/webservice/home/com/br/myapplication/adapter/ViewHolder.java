package webservice.home.com.br.myapplication.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import webservice.home.com.br.myapplication.R;

public class ViewHolder extends RecyclerView.ViewHolder {
    final ImageView imageView;
    final ImageButton button;
    final TextView textView1;
    final TextView textView2;
    final TextView textView3;
    final TextView textView4;
    final TextView textView5;
    final TextView textView6;
    final TextView textView7;
    final TextView textView8;
    final TextView textView9;
    final TextView textView10;
    final TextView textView11;
    final Button button2;


    final ScrollView scrollView;

    public ViewHolder(View view) {
        super(view);

        scrollView = (ScrollView)view.findViewById(R.id.scrollViewID);

       imageView = (ImageView)view.findViewById(R.id.imageViewID);
        button = (ImageButton) view.findViewById(R.id.imageButtonID);
        textView1 = (TextView)view.findViewById(R.id.textID);
        textView2 = (TextView)view.findViewById(R.id.text2ID);

        //ano
        textView3 = (TextView)view.findViewById(R.id.text4ID);
        //Diretor
        textView4 = (TextView)view.findViewById(R.id.text6ID);
        //Estrela
        textView5 = (TextView)view.findViewById(R.id.text7ID);

        textView6 =(TextView)view.findViewById(R.id.text9ID);
        textView7 =(TextView)view.findViewById(R.id.text11ID);
        textView8 = (TextView)view.findViewById(R.id.text13ID);

        textView9 = (TextView)view.findViewById(R.id.text15ID);
        textView10 = (TextView)view.findViewById(R.id.text17ID);
        textView11 = (TextView)view.findViewById(R.id.text19ID);

        button2 = (Button)view.findViewById(R.id.ButtonID2);
    }

}
