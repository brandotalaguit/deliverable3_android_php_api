package com.example.deliverable3_android_php_api_crud;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private UserApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("ENV", "Base URL: " + BuildConfig.BASE_URL);

        apiService = new UserApiService(this);

        // example
        apiService.createUser("Cardo Dalisay", "juandelacruz@umak.edu.ph", "password123");
//        apiService.getUser(1);
//        apiService.fetchUsers();
    }
}