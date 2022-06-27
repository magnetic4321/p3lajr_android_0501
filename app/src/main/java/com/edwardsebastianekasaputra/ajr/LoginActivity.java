package com.edwardsebastianekasaputra.ajr;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.edwardsebastianekasaputra.ajr.auth.AuthResponse;
import com.edwardsebastianekasaputra.ajr.auth.UserPreferences;
import com.edwardsebastianekasaputra.ajr.models.User;
import com.edwardsebastianekasaputra.ajr.role.customer.CustomerActivity;
import com.edwardsebastianekasaputra.ajr.role.driver.DriverActivity;
import com.edwardsebastianekasaputra.ajr.role.manager.ManagerActivity;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.sql.Driver;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements Serializable {

    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";

    private UserPreferences userPreferences;
    private RequestQueue queue;

    private EditText etEmail, etPassword;
    private Button btnLogin;

    private static String URL_LOGIN = "http://192.168.1.3:8000/api/login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userPreferences = new UserPreferences(LoginActivity.this);
        queue = Volley.newRequestQueue(this);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                login(email, password);
            }
        });
    }

    private void login(final String email, final String password) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String message = jsonObject.getString("message");
                            String token = jsonObject.getString("access_token");

                            Gson gson = new Gson();
                            AuthResponse authResponse = gson.fromJson(response, AuthResponse.class);
                            User user = authResponse.getUser();
                            userPreferences.setUser(user.getId(), user.getNama(),
                                    user.getEmail(), user.getPassword(), token);

                            if (message.equals("Customer Authenticated")) {
                                Toast.makeText(LoginActivity.this, "Customer Authenticated"  ,Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, CustomerActivity.class);
                                startActivity(intent);
                            }else if(message.equals("Driver Authenticated")){
                                Toast.makeText(LoginActivity.this, "Driver Authenticated"  ,Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, DriverActivity.class);
                                startActivity(intent);
                            }else if(message.equals("Manager Authenticated")){
                                Toast.makeText(LoginActivity.this, "Manager Authenticated"  ,Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, ManagerActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(LoginActivity.this, "Test Sakpole"  ,Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, ManagerActivity.class);
                                startActivity(intent);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this, "Error! " + error.toString(), Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }
                })
        {
            @Override
            public Map<String, String> getParams() throws AuthFailureError{
                Map<String, String> params= new HashMap<String, String>();
                params.put(KEY_EMAIL, etEmail.getText().toString());
                params.put(KEY_PASSWORD, etPassword.getText().toString());
                return params;
            }
        };
        queue.add(stringRequest);
    }

    private void setLoading(boolean isLoading) {
        LinearLayout layoutLoading = findViewById(R.id.loading_layout);
        if (isLoading) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            layoutLoading.setVisibility(View.VISIBLE);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            layoutLoading.setVisibility(View.INVISIBLE);
        }
    }
}