package webservice.home.com.br.myapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private FilmeObj filmeatual;
    private ImageView imageView;
    private ListView lista;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView)findViewById(R.id.imageViewID);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        MenuItem item = menu.findItem(R.id.menuSearch);
        final SearchView searchView = (SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

        // pega itens aqui
            @Override
            public boolean onQueryTextSubmit(String query) {

                searchView.clearFocus();
                String x = searchView.getQuery().toString().replace(' ','+');
                GetJson download = new GetJson();

                download.execute("http://www.omdbapi.com/?s="+x);
                try {
                    ArrayList <FilmeObj> list;

                    list = download.get();

                    RecyclerView recyclerView = (RecyclerView)findViewById(R.id.listaReciclavelID);
                    recyclerView.setAdapter(new Adapter(list, MainActivity.this));
                    RecyclerView.LayoutManager manager = new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL, false);
                    recyclerView.setLayoutManager(manager);
                   //  imageView.setImageBitmap(filmeatual.getImagem());
                }

                catch (ExecutionException e){
                    e.printStackTrace();
                }
                catch (InterruptedException e){
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


    private class GetJson extends AsyncTask<String, Void, ArrayList<FilmeObj>> {


        @Override
        protected ArrayList<FilmeObj> doInBackground(String... params) {
            Utils util = new Utils();

            return util.getInformacaoArray(params[0]);
        }
    }


}
