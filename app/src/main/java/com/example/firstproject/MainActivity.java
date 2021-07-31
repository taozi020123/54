package com.example.firstproject;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.Mat;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private ImageView imageView2,imageView3,imageView;
    private Mat srcmat1,srcmat2,dstmat;
    private Bitmap bitmap;
    @Override
    protected void onDestroy() {
        super.onDestroy();
        srcmat1.release();
        srcmat2.release();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        opencv();
        button = (Button) findViewById(R.id.button);
        imageView = (ImageView) findViewById(R.id.imageView);
        imageView2 = (ImageView) findViewById(R.id.imageView2);
        imageView3 = (ImageView) findViewById(R.id.imageView3);
        srcmat1=new Mat();
        srcmat2=new Mat();
        dstmat=new Mat();
        try {
            srcmat1= Utils.loadResource(this,R.drawable.qq);
            srcmat2=Utils.loadResource(this,R.drawable.qq1);
        }catch (IOException e){e.printStackTrace();}
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Core.bitwise_or(srcmat1,srcmat2,dstmat);
                        bitmap=Bitmap.createBitmap(dstmat.width(),dstmat.height(),Bitmap.Config.ARGB_8888);
                Utils.matToBitmap(dstmat,bitmap);
                imageView3.setImageBitmap(bitmap);
            }
        });
    }
    private void opencv(){
        boolean success= OpenCVLoader.initDebug();
        if(success){
            Toast.makeText(this.getApplicationContext(), "Loading opencv", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this.getApplicationContext(), "Warning", Toast.LENGTH_SHORT).show();
        }
    }

}