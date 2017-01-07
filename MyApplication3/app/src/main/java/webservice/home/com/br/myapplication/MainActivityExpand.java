package webservice.home.com.br.myapplication;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivityExpand extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity_expand);


        Bundle bundle = getIntent().getExtras();

        Bitmap bitmap = (Bitmap) bundle.get("image");
        imageView = (ImageView) findViewById(R.id.imageView2);

        imageView.setImageBitmap(bitmap);
    }
}
