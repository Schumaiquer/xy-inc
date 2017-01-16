package webservice.home.com.br.myapplication.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import webservice.home.com.br.myapplication.adapter.Adapter;
import webservice.home.com.br.myapplication.Information.FilmeObj;
import webservice.home.com.br.myapplication.R;
import webservice.home.com.br.myapplication.connection.Utils;

public class MainActivity extends AppCompatActivity {

    private FilmeObj filmeatual;
    private ImageView imageView;
    private ListView lista;
    private Button button;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView2);




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        MenuItem item = menu.findItem(R.id.menuSearch);


        final SearchView searchView = (SearchView) item.getActionView();


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            // pega itens aqui
            @Override
            public boolean onQueryTextSubmit(String query) {

                //searchView.clearFocus();

                String x = searchView.getQuery().toString().replace(' ', '+');
                GetJson download = new GetJson(MainActivity.this);
                GetJson2 download2 = new GetJson2(MainActivity.this);

                textView.setVisibility(View.INVISIBLE);

                download.execute("http://www.omdbapi.com/?s=" + x);
                try {
                    ArrayList<FilmeObj> list;

                    list = download.get();
                    download2.execute(list);

                    list = download2.getLista();

                    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.listaReciclavelID);
                    recyclerView.setAdapter(new Adapter(list, MainActivity.this));
                    RecyclerView.LayoutManager manager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
                    recyclerView.setLayoutManager(manager);


                    //  imageView.setImageBitmap(filmeatual.getImagem());
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // exibir texto tela
                // Toast.makeText(MainActivity.this,newText,Toast.LENGTH_SHORT).show();

                return false;

            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.save:
                Intent intent = new Intent(MainActivity.this, ActivitySavedFiles.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(menuItem);
        }

    }

    private class GetJson extends AsyncTask<String, Void, ArrayList<FilmeObj>> {
        private Context context;
        private ProgressDialog load;

        public GetJson(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            load = ProgressDialog.show(context, "", "Loading...", true);
        }

        @Override
        protected ArrayList<FilmeObj> doInBackground(String... params) {
            Utils util = new Utils();

            return util.getInformacaoArray(params[0]);
        }

        @Override
        protected void onPostExecute(ArrayList<FilmeObj> filmeObj) {
            load.dismiss();
        }
    }

    private class GetJson2 extends AsyncTask<ArrayList<FilmeObj>, Void, Void> {
        ArrayList<FilmeObj> list;
        private Context context;
        private ProgressDialog load;


        @Override
        protected void onPreExecute() {
            load = ProgressDialog.show(context, "", "Loading...", true);
        }

        public GetJson2(Context context) {
            list = new ArrayList<FilmeObj>();
            this.context = context;
        }


        @Override
        protected Void doInBackground(ArrayList<FilmeObj>... params) {
            Utils util = new Utils();

            for (FilmeObj f : params[0]) {
                f = util.getInformacao("http://www.omdbapi.com/?i=" + f.getImdbID());
                // f = util.getInformacao("http://www.omdbapi.com/?t=" + f.getTitle().replace(' ', '+'));

                list.add(f);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
            load.dismiss();
        }

        public ArrayList<FilmeObj> getLista() {
            return list;
        }
    }


}
