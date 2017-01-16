package webservice.home.com.br.myapplication.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import java.util.concurrent.ExecutionException;

import uk.co.senab.photoview.PhotoViewAttacher;
import webservice.home.com.br.myapplication.R;
import webservice.home.com.br.myapplication.connection.Utils;

public class MainActivityExpand extends AppCompatActivity {

    private ImageView imageView;
    private Bitmap bitmap2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity_expand);

        GetImage image = new GetImage(this);
        Bundle bundle = getIntent().getExtras();

        if (bundle.get("link") != null) {
// pega o link de cada imagem
            String link = (String) bundle.get("link");

            image.execute(link);
            try {
                bitmap2 = image.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            imageView = (ImageView) findViewById(R.id.imageView2);
            imageView.setImageBitmap(bitmap2);
            // zoom da imagem
            PhotoViewAttacher photoView = new PhotoViewAttacher(imageView);
            photoView.update();
        }
    }

    // passar para o baixa imagem o nosso link atraves destes metodos
    private class GetImage extends AsyncTask<String, Void, Bitmap> {

        private Context context;
        private ProgressDialog load;


        @Override
        protected void onPreExecute() {
            load = ProgressDialog.show(context, "", "Loading...", true);
        }

        public GetImage(Context context) {
            this.context = context;
        }


        @Override
        protected Bitmap doInBackground(String... params) {
            Utils util = new Utils();
            Bitmap bitmap;

            bitmap = util.baixarImagem(params[0]);

            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            load.dismiss();
        }

    }


}

