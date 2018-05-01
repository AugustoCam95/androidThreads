package com.example.jose.androidthreads;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.InputStream;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView imageView;
    Button button;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        imageView = (ImageView) findViewById(R.id.imageView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        button.setOnClickListener(this);

    }



    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                progressBar.setVisibility(View.VISIBLE);
                DoIt loadImage = new DoIt();
                loadImage.execute("http://inacio.com.br/wp-content/uploads/2013/02/logo_ufc-virtual.jpg");
        }
    }

    public class DoIt extends AsyncTask<String, Void, Bitmap> {

         public Bitmap loadImageFromNet(String url) {
            try {
                Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(url).getContent());
                return bitmap;
            } catch (Exception except) {
                except.printStackTrace();
            }
            return null;
        }


        @Override
        protected Bitmap doInBackground(String[] params) {
            return loadImageFromNet(params[0]);
        }


        protected void onExecute(Bitmap result) {
            imageView.setImageBitmap(result);
            progressBar.setVisibility(View.INVISIBLE);
            Log.v("tag", "Rolling after ");
        }

    }

}
