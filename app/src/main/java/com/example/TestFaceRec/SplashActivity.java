package com.example.TestFaceRec;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.security.Permission;


public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        String[] permissionArrays = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissionArrays, 1111);
        } else {

            startActivity(new Intent(this, TestFaceJavaActivity.class));
        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        Log.e("permissions", "" + permissions.length);
        Log.e("grantResults", "" + grantResults.length);
        if (requestCode == 1111) {
            for (int i = 0; i < grantResults.length; i++) {
                String permission = permissions[i];


                if (grantResults[i] == PackageManager.PERMISSION_DENIED) {

                    if (permissions[i].contains("camera")) {
                        Toast.makeText(SplashActivity.this, "Please enable camera permission", Toast.LENGTH_LONG).show();

                    }


                } else {

                }
            }

            if (permissions.length == grantResults.length) {
                startActivity(new Intent(this, TestFaceJavaActivity.class));
            }
        }
    }
}