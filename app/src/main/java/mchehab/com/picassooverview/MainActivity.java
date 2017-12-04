package mchehab.com.picassooverview;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private ProgressBar progressBar;

    private final static String IMAGE_URL = "https://www.w3schools.com/w3css/img_lights.jpg";

    private boolean isImageDownloaded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        Button button = findViewById(R.id.buttonDownloadImage);
        button.setOnClickListener(e -> {
            imageView.setImageBitmap(null);
            progressBar.setVisibility(View.VISIBLE);
            downloadImage();
        });

        imageView = findViewById(R.id.imageView);

        if(savedInstanceState != null){
            isImageDownloaded = savedInstanceState.getBoolean("isImageDownloaded");
            if(isImageDownloaded)
                downloadImage();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("isImageDownloaded", isImageDownloaded);
    }

    private void downloadImage(){
        isImageDownloaded = true;
        Picasso.with(this).load(IMAGE_URL).error(R.drawable.error).into(imageView, new Callback() {
            @Override
            public void onSuccess() {
                Log.d("SUCCESS", "inside success");
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError() {
                Log.d("ERROR", "inside error");
                progressBar.setVisibility(View.GONE);
            }
        });
    }
}