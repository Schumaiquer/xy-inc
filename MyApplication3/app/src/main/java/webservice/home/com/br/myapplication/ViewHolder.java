package webservice.home.com.br.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ViewHolder extends RecyclerView.ViewHolder {
    final ImageView imageView;
    final Button button;

    public ViewHolder(View view) {
        super(view);
       imageView = (ImageView)view.findViewById(R.id.imageViewID);
        button = (Button) view.findViewById(R.id.buttonID);
    }

}
