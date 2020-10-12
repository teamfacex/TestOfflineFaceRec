package com.example.TestFaceRec;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.app.Utils.FaceRecongintion;
import com.app.Utils.onFaceRecognitionListener;

import java.util.ArrayList;
import java.util.List;


public class TestFaceJavaActivity extends AppCompatActivity implements onFaceRecognitionListener {


    Button register, search;
    FaceRecongintion faceRecongintion;

    List<String> vectorlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_face_java);

        register = findViewById(R.id.register);
        search = findViewById(R.id.search);


        vectorlist = new ArrayList<>();

        // intilize SDK

        faceRecongintion = new FaceRecongintion(this, R.id.main_view, this);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //for registartion callbackmethods onRegisterSuccess,onRegisterError
                faceRecongintion.FaceRegistrtion();
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //for search or Recongintion callbackmethods onSearchSuccess,onSearchError

                faceRecongintion.FaceSearch();

            }
        });


    }

    @Override
    public void onRegisterSuccess(String s, Bitmap bitmap) {


        // s ---output string from sdk

        // adding to existing vector list here vectors for use search function


        vectorlist.add(s);

        //to update face search input


        faceRecongintion.addVectors(vectorlist);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(TestFaceJavaActivity.this, "Registration success", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onRegisterError(final String s) {

        // s ---output error from sdk

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(TestFaceJavaActivity.this, "Registration failure :" + s, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onSearchSuccess(int i, final String s, Bitmap bitmap) {


        // s ---distance for matched user  from sdk

        // i --- position in provided input array  Here vectorlist is my input array



        //to update face search input



        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(TestFaceJavaActivity.this, "Search success" + " distance is " + s, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onSearchError(final String s) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(TestFaceJavaActivity.this, "Search Error " + s, Toast.LENGTH_SHORT).show();
            }
        });
    }
}