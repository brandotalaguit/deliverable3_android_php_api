package com.example.deliverable3_android_php_api_crud;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class UserApiService {
    private Context context;

    private static final String BASE_URL = BuildConfig.BASE_URL;

    public UserApiService(Context context) {
        this.context = context;
    }

    // create
    public void createUser(String name, String email, String password) {
        String url = BASE_URL + "/create_user.php";

        // Implement the logic to create a user using Volley or any other HTTP library
        JSONObject postData = new JSONObject();
        try {
            postData.put("username", name);
            postData.put("email", email);
            postData.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(
            Request.Method.POST,
            url,
            postData,
            response -> {
                // Handle the response
                Log.d("UserApiService", "User created: " + response.toString());
            },
            error -> {
                // Handle the error
                Log.e("ErrApiService", "Error creating user: " + error.toString());
            }
        );

        VolleySingleton.getInstance(context).addToRequestQueue(request);
    }

    // read
    public void getUser(int userId) {
        String url = BASE_URL + "/get_user.php?id=" + userId;

        // Implement the logic to get a user using Volley or any other HTTP library
        JsonObjectRequest request = new JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            response -> {
                // Handle the response
                try {
                    String name = response.getString("name");
                    String email = response.getString("email");
                    Log.d("UserApiService", "Name: " + name + ", Email: " + email);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            },
            error -> {
                // Handle the error
                Log.e("UserApiService", "Error getting user: " + error.getMessage());
            }
        );

        VolleySingleton.getInstance(context).addToRequestQueue(request);
    }

    // fetch
    public void fetchUsers() {
        String url = BASE_URL + "/fetch_users.php";

        JsonArrayRequest request = new JsonArrayRequest(
            Request.Method.GET,
            url,
            null,
            response -> {
                // Handle the response
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject user = response.getJSONObject(i);
                        String name = user.getString("name");
                        String email = user.getString("email");
                        Log.d("UserApiService", "Name: " + name + ", Email: " + email);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            },
            error -> {
                // Handle the error
                Log.e("UserApiService", "Error fetching users: " + error.getMessage());
            }
        );

        VolleySingleton.getInstance(context).addToRequestQueue(request);
    }

}
