package com.example.getimage;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private String url = "https://api.androidhive.info/json/movies/2.jpg";
    private Button load;
    private  MyTask task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
        load=findViewById(R.id.button);
        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                task=new MyTask();
                task.execute(url);

            }
        });

    }

    private class MyTask extends AsyncTask<String, Void, Bitmap> {


        @Override
        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap image = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                image = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return image;
        }

        @Override
        protected void onPreExecute() {
            Toast.makeText(MainActivity.this, "Wait",
                    Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            Toast.makeText(MainActivity.this, "Downloaded",
                    Toast.LENGTH_SHORT).show();
            imageView.setImageBitmap(bitmap);
        }

    }
}
