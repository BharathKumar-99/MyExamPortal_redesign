package com.jrcreations.myexamportal.UI.Selection;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.jrcreations.myexamportal.LoginSignup.Login;
import com.jrcreations.myexamportal.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UiSelection extends AppCompatActivity {

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    TextView name;
    private List<RowModel> movieList;
    String value;
    RecyclerView recyclerView;
    private RecycleAdaptor adapter;
    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui_selection);

        if (user == null) {
            startActivity(new Intent(this, Login.class));
        }
        name=findViewById(R.id.selheading);
        recyclerView=findViewById(R.id.recview);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        adapter=new RecycleAdaptor(this,movieList);
        recyclerView.setAdapter(adapter);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            value = extras.getString("key");
            //The key argument here must match that used in the other activity
        }
     

        getname(value);






    }



    public void getname(String id) {


        //getting the progressbar
        String url = "https://myexamportals.com/Php/getuser.php";
        //creating a string request to send request to the url
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            JSONArray tutorialsArray = new JSONArray(response);


                            for (int i = 0; i < tutorialsArray.length(); i++) {
                                //getting the json object of the particular index inside the array
                                JSONObject tutorialsObject = tutorialsArray.getJSONObject(i);
                                RowModel users=new RowModel();
                                users.setName(tutorialsObject.getString("name"));
                                users.setPic(tutorialsObject.getString("pic"));
                                //creating a tutorial object and giving them the values from json object
                                Log.d("TAG", "onResponse: " + users.getName());
                                movieList.add(users);

                            }
                            adapter.notifyDataSetChanged();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                error -> {
                    //displaying the error in toast if occur
                    Log.d("TAG", "onErrorResponse: " + error);
                    error.printStackTrace();
                }) {
            @Override
            public Map getParams() {
                Map params = new HashMap();

                params.put("id", id);

                return params;
            }
        };

        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //adding the string request to request queue
        requestQueue.add(stringRequest);
    }
}