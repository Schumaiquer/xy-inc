package webservice.home.com.br.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class Adapter extends RecyclerView.Adapter {

    private ArrayList<FilmeObj> filme;
    private Context context;

    public Adapter(ArrayList<FilmeObj> filme, Context context){
        this.filme = filme;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.lista_main, parent, false);

        ViewHolder holder = new ViewHolder(view);
        return holder;
    }



    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
            ViewHolder holder = (ViewHolder) viewHolder;
        FilmeObj filmeObj = filme.get(position);
        holder.imageView.setImageBitmap(filmeObj.getImagem());
    }

    @Override
    public int getItemCount() {
        return filme.size();
    }
}


