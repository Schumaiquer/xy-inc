package webservice.home.com.br.myapplication;

import android.content.Context;
import android.content.Intent;
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
        final FilmeObj filmeObj = filme.get(position);
        holder.imageView.setImageBitmap(filmeObj.getImagem());
        holder.textView1.setText(filmeObj.getTitle());
        holder.textView2.setText(filmeObj.getPlot());

        holder.textView3.setText(filmeObj.getYear());
        holder.textView4.setText(filmeObj.getDirector());
        holder.textView5.setText(filmeObj.getImdbRating());

        holder.textView6.setText(filmeObj.getActors());
        holder.textView7.setText(filmeObj.getGenre());
        holder.textView8.setText(filmeObj.getRuntime());

        holder.textView9.setText(filmeObj.getRated());
        holder.textView10.setText(filmeObj.getReleased());
        holder.textView11.setText(filmeObj.getLanguage());


        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context,MainActivityExpand.class);
                intent.putExtra("image",filmeObj.getImagem());

               context.startActivity(intent);

            }
        });


  /*      holder.scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        // Disallow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;

                    case MotionEvent.ACTION_UP:
                        // Allow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }

                // Handle ListView touch events.
                v.onTouchEvent(event);
                return true;
            }
        });}
*/}
    @Override
    public int getItemCount() {
        return filme.size();
    }
}


