package com.example.volleyinandroid;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    EditText login_email,login_pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_email=findViewById(R.id.login_email);
        login_pass=findViewById(R.id.login_pass);
    }

    public  void loginUser(View view){
        String l_email=login_email.getText().toString();
        String l_pass=login_pass.getText().toString();


        ProgressDialog progressDialog=new ProgressDialog(Login.this);
        progressDialog.setTitle("Loading...");
        progressDialog.setMessage("Loading...Please Wait...!");
        progressDialog.show();
        RequestQueue requestQueue= Volley.newRequestQueue(this);

        StringRequest stringRequest=new StringRequest(
                Request.Method.POST,
//                "http://192.168.231.44:8086/CrudAppRoyal/LoginApi",  mysql api path servlet
                "http://192.168.231.44:/LoginApi.php",
//                "http://192.168.231.44:/RetrofitPhpApi.php/login",  //aa ma ek j file ma login and register che
//                UrlPaths.loginUrl
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.hide();
//                        Toast.makeText(Login.this, ""+response, Toast.LENGTH_SHORT).show();
//                        if (response.trim().equals("Failed to Login...")){
                        if (response.trim().equals("fail")){
                            Toast.makeText(Login.this, "Email Id and password not match...!", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            //                            profile page
                            Toast.makeText(Login.this, ""+response, Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.hide();
                        Toast.makeText(Login.this, ""+error, Toast.LENGTH_SHORT).show();

                    }
                }
        ){
            //value api ma mokalvani che etle getParams ne override karvani
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String>hm=new HashMap<>();
                hm.put("l_email",l_email);
                hm.put("l_pass",l_pass);
                return  hm;
            }
        };
        requestQueue.add(stringRequest);
    }
}