package com.gauravrai.imageexample;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    ImageView myImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myImage = findViewById(R.id.myImage);

        String url = "https://cdn.vox-cdn.com/thumbor/iS9cu8-U-hh8rUGhUnc4ulkJ1wE=/0x0:2040x1360/1820x1213/filters:focal(860x1034:1186x1360)/cdn.vox-cdn.com/uploads/chorus_image/image/59377089/wjoel_180413_1777_android_001.1523625143.jpg";

//        new DownloadImage(myImage).execute(url);

        Picasso.get().load(url).into(myImage);

    }

    private class DownloadImage extends AsyncTask<String, Void, Bitmap> {

        ImageView bmImage;

        DownloadImage(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        @Override
        protected Bitmap doInBackground(String... urls) {

            String myURL = urls[0];
            Bitmap bmp = null;

            try {
                // This is where magic happens
                InputStream in = new java.net.URL(myURL).openStream();
                bmp = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }

            return bmp;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }

    }

}
