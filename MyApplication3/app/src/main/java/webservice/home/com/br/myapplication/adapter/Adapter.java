package webservice.home.com.br.myapplication.adapter;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import webservice.home.com.br.myapplication.Information.FilmeObj;
import webservice.home.com.br.myapplication.R;
import webservice.home.com.br.myapplication.db.ControllerDB;
import webservice.home.com.br.myapplication.db.CreateDB;
import webservice.home.com.br.myapplication.view.ActivitySavedFiles;
import webservice.home.com.br.myapplication.view.MainActivity;
import webservice.home.com.br.myapplication.view.MainActivityExpand;


public class Adapter extends RecyclerView.Adapter {

    private ArrayList<FilmeObj> filme;
    private Context context;
    private ControllerDB controllerDB;
    private Activity activity;

    public Adapter(ArrayList<FilmeObj> filme, Context context, Activity activity) {
        this.filme = filme;
        this.context = context;
        this.activity = activity;
        controllerDB = new ControllerDB(context);
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

        //   if (filmeObj.getPoster().equals("N/A")) {
        //        holder.imageView.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.sem_foto_icone));
        // } else {
        holder.imageView.setImageBitmap(filmeObj.getImagem());
        // }

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

     //   if (!filmeObj.getPoster().equals("N/A")) {
        if(context.getClass().equals(MainActivity.class)) {
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(context, MainActivityExpand.class);
                    intent.putExtra("link", filmeObj.getPoster());
                    context.startActivity(intent);

                }
            });
        }
       // }


        if (context.getClass().equals(MainActivity.class)) {
            holder.button2.setText("Save");

        } else {
            holder.button2.setText("Remove");
        }

        holder.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (context.getClass().equals(MainActivity.class)) {
                    ContentValues values = new ContentValues();

                    values.put("TITLE", filmeObj.getTitle());
                    values.put("YEAR", filmeObj.getYear());
                    values.put("RATED", filmeObj.getRated());
                    values.put("RELEASED", filmeObj.getReleased());
                    values.put("RUNTIME", filmeObj.getRuntime());
                    values.put("GENRE", filmeObj.getGenre());
                    values.put("DIRECTOR", filmeObj.getDirector());
                    values.put("ACTORS", filmeObj.getActors());
                    values.put("PLOT", filmeObj.getPlot());
                    values.put("LANGUAGE", filmeObj.getLanguage());
                    values.put("IMAGE", bitmaptoblob(filmeObj.getImagem()));
                    values.put("IMDBID", filmeObj.getImdbID());
                    values.put("IMDBRATING", filmeObj.getImdbRating());
                    values.put("POSTER", filmeObj.getPoster());

                    long resultado = controllerDB.inserirDados(CreateDB.TABELA, values);
                    if (resultado == -1) {
                        Toast.makeText(context, "This movie is already saved!", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(context, "Save", Toast.LENGTH_SHORT).show();

                    }


                } else {
                    final String where = CreateDB.tabela.TITLE + "=" + "'" + filmeObj.getTitle() + "'";
                    controllerDB.DeletaDados(CreateDB.TABELA, where);
                    Toast.makeText(context, "Removed!", Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(context,ActivitySavedFiles.class);
                   context.startActivity(i);
                    activity.finish();


                }
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
*/
    }



    public byte[] bitmaptoblob(Bitmap bitmap) {

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

    @Override
    public int getItemCount() {
        return filme.size();
    }
}


