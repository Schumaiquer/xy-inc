package webservice.home.com.br.myapplication.adapter;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import webservice.home.com.br.myapplication.Information.FilmeObj;
import webservice.home.com.br.myapplication.R;
import webservice.home.com.br.myapplication.connection.Utils;
import webservice.home.com.br.myapplication.db.ControllerDB;
import webservice.home.com.br.myapplication.db.CreateDB;
import webservice.home.com.br.myapplication.view.MainActivityExpand;


public class Adapter extends RecyclerView.Adapter {

    private ArrayList<FilmeObj> filme;
    private Context context;
    private ControllerDB controllerDB;

    public Adapter(ArrayList<FilmeObj> filme, Context context) {
        this.filme = filme;
        this.context = context;
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
        if (filmeObj.getPoster().equals("N/A")) {
            holder.imageView.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.sem_foto_icone));
        } else {
            holder.imageView.setImageBitmap(filmeObj.getImagem());
        }

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

        if (!filmeObj.getPoster().equals("N/A")) {
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(context, MainActivityExpand.class);
                    intent.putExtra("link", filmeObj.getPoster());

                    context.startActivity(intent);
                }
            });

        }


        holder.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                  //  Toast.makeText(context,"Save",Toast.LENGTH_SHORT).show();

                class GetJson3 extends AsyncTask<String, Void, Void>{

                    FilmeObj list;
                    private Context context;
                    private ProgressDialog load;

                    @Override
                    protected void onPreExecute() {
                        load = ProgressDialog.show(context, "", "Loading...", true);
                    }

                    public GetJson3(Context context) {
                        list = new FilmeObj();
                        this.context = context;
                    }

                    @Override
                    protected Void doInBackground(String... params){
                    Utils utils = new Utils();

                        list = utils.getInformacao("http://www.omdbapi.com/?t=" + filmeObj.getTitle());

                        ContentValues values = new ContentValues();

                        values.put("TITLE",filmeObj.getTitle());
                        values.put("YEAR",filmeObj.getYear());
                        values.put("RATED",filmeObj.getRated());
                        values.put("RELEASED",filmeObj.getReleased());
                        values.put("RUNTIME",filmeObj.getRuntime());
                        values.put("GENRE",filmeObj.getGenre());
                        values.put("DIRECTOR",filmeObj.getDirector());
                        values.put("ACTORS",filmeObj.getActors());
                        values.put("PLOT",filmeObj.getPlot());
                        values.put("LANGUAGE",filmeObj.getLanguage());
                        //  values.put("IMDBID",filmeObj.getImdbID());
                        //   values.put("IMDBRATING",filmeObj.getImdbRating());
                        values.put("POSTER",filmeObj.getPoster());

                        controllerDB.inserirDados(CreateDB.TABELA,values);

                        return null;
                    }

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

    @Override
    public int getItemCount() {
        return filme.size();
    }
}


