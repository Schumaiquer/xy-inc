package webservice.home.com.br.myapplication.view;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import java.util.ArrayList;

import webservice.home.com.br.myapplication.Information.FilmeObj;
import webservice.home.com.br.myapplication.R;
import webservice.home.com.br.myapplication.adapter.Adapter;
import webservice.home.com.br.myapplication.db.ControllerDB;
import webservice.home.com.br.myapplication.db.CreateDB;

public class ActivitySavedFiles extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activitysavedfiles);

        getSupportActionBar().setTitle("My Saved Movies");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        ControllerDB crud = new ControllerDB(getBaseContext());
        String campos[] = {CreateDB.tabela.TITLE, CreateDB.tabela.PLOT, CreateDB.tabela.YEAR, CreateDB.tabela.DIRECTOR, CreateDB.tabela.ACTORS, CreateDB.tabela.GENRE, CreateDB.tabela.RUNTIME, CreateDB.tabela.RATED, CreateDB.tabela.RELEASED, CreateDB.tabela.IMDBID, CreateDB.tabela.IMDBRATING, CreateDB.tabela.LANGUAGE, CreateDB.tabela.IMAGE};

        Cursor cursor = crud.CarregaDados(CreateDB.TABELA, campos);

      //  FilmeObj filmeObj = new FilmeObj();
        ArrayList<FilmeObj> list = new ArrayList<FilmeObj>();

        for (int i = 0; i < cursor.getCount(); i++) {

            String title = cursor.getString(0);
            String plot = cursor.getString(1);
            String year = cursor.getString(2);
            String director = cursor.getString(3);
            String actors = cursor.getString(4);
            String genre = cursor.getString(5);
            String runtime = cursor.getString(6);
            String rated = cursor.getString(7);
            String released = cursor.getString(8);
            String imdbid = cursor.getString(9);
            String imdbrating = cursor.getString(10);
            String language = cursor.getString(11);
           Bitmap imagem = blobtobitmap(cursor.getBlob(12));

            /*
            filmeObj.setTitle(cursor.getString(0));
            filmeObj.setPlot(cursor.getString(1));
            filmeObj.setYear(cursor.getString(2));
            filmeObj.setDirector(cursor.getString(3));
            filmeObj.setActors(cursor.getString(4));
            filmeObj.setGenre(cursor.getString(5));
            filmeObj.setRuntime(cursor.getString(6));
            filmeObj.setRated(cursor.getString(7));
            filmeObj.setReleased(cursor.getString(8));
            filmeObj.setImdbID(cursor.getString(9));
            filmeObj.setImdbRating(cursor.getString(10));
            filmeObj.setLanguage(cursor.getString(11));
            filmeObj.setImagem(blobtobitmap(cursor.getBlob(12)));
            list.add(filmeObj);
            */

            // String imdbrating = cursor.getString(9);
            list.add(new FilmeObj(title,plot,year,director,actors,genre,runtime,rated,released,imdbid,imdbrating,language,imagem));
            cursor.moveToNext();
        }


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.listaReciclavel2ID);
        recyclerView.setAdapter(new Adapter(list, ActivitySavedFiles.this));
        RecyclerView.LayoutManager manager = new LinearLayoutManager(ActivitySavedFiles.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);

    }

    public Bitmap blobtobitmap(byte[] blob) {

        return BitmapFactory.decodeByteArray(blob, 0, blob.length);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(menuItem);
        }

    }


}
