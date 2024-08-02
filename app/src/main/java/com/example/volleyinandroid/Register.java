package com.example.volleyinandroid;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
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

public class Register extends AppCompatActivity {

    EditText reg_name,reg_email,reg_pass,reg_gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        reg_name=findViewById(R.id.reg_name);
        reg_email=findViewById(R.id.reg_email);
        reg_pass=findViewById(R.id.reg_pass);
        reg_gender=findViewById(R.id.reg_gender);

    }

    public  void registerUser(View view){
        String name=reg_name.getText().toString();
        String email=reg_email.getText().toString();
        String pass=reg_pass.getText().toString();
        String gender=reg_gender.getText().toString();

        ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Loading...");
        progressDialog.setMessage("Loading...please wait..!");
        progressDialog.show();

        RequestQueue requestQueue= Volley.newRequestQueue(this);

        StringRequest stringRequest=new StringRequest(
                Request.Method.POST,
                //"http://192.168.231.44:8086/CrudAppRoyal/InsertRecord",  //aa mysql ma che
                "http://192.168.231.44:/RegisterApi.php"  //aa php mate
                ,
                ////                "http://192.168.231.44:/RetrofitPhpApi.php/register",  //aa ma ek j file ma login and register che
//                UrlPaths.registerUrl
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.hide();
                        Toast.makeText(Register.this, ""+response, Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.hide();
                        Toast.makeText(Register.this, ""+error, Toast.LENGTH_SHORT).show();
                    }
                }
        ){
//            method override karvi ->value moklva mate request object ma

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                HashMap<String,String> hm=new HashMap<>();

//                aa mysql mate
//                hm.put("name",name);
//                hm.put("email",email);
//                hm.put("password",pass);
//                hm.put("gender",gender);


                //php api mate
                hm.put("key_name",name);
                hm.put("key_email",email);
                hm.put("key_pass",pass);
                hm.put("key_gender",gender);
                return  hm;
            }
        };
        requestQueue.add(stringRequest);
    }
}