package webservice.home.com.br.myapplication.view;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import java.util.ArrayList;

import webservice.home.com.br.myapplication.adapter.Adapter;
import webservice.home.com.br.myapplication.Information.FilmeObj;
import webservice.home.com.br.myapplication.R;
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
        String campos[] = {CreateDB.tabela.TITLE, CreateDB.tabela.PLOT, CreateDB.tabela.YEAR, CreateDB.tabela.DIRECTOR, CreateDB.tabela.ACTORS, CreateDB.tabela.GENRE, CreateDB.tabela.RUNTIME, CreateDB.tabela.RATED, CreateDB.tabela.RELEASED, CreateDB.tabela.LANGUAGE};
        Cursor cursor = crud.CarregaDados(CreateDB.TABELA, campos);

        FilmeObj filmeObj = new FilmeObj();
        ArrayList<FilmeObj> list = new ArrayList<>();

         for (int i = 0; i < cursor.getCount(); i++) {

        filmeObj.setTitle(cursor.getString(0));
       filmeObj.setPlot(cursor.getString(1));
       filmeObj.setYear(cursor.getString(2));
       filmeObj.setDirector(cursor.getString(3));
             filmeObj.setActors(cursor.getString(4));
             filmeObj.setGenre(cursor.getString(5));
             filmeObj.setRuntime(cursor.getString(6));
             filmeObj.setRated(cursor.getString(7));
             filmeObj.setReleased(cursor.getString(8));
             filmeObj.setLanguage(cursor.getString(9));


        // String imdbrating = cursor.getString(9);
    list.add(filmeObj);
        cursor.moveToNext();
    }


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.listaReciclavel2ID);
        recyclerView.setAdapter(new Adapter(list, ActivitySavedFiles.this));
        RecyclerView.LayoutManager manager = new LinearLayoutManager(ActivitySavedFiles.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);

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
