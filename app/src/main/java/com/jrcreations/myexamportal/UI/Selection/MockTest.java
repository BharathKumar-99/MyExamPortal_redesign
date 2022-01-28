package com.jrcreations.myexamportal.UI.Selection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.jrcreations.myexamportal.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockTest extends AppCompatActivity {
TextView name;
    private List<MockTestModel> mockTestModels;
RecyclerView recyclerView;
    String value;
    RecycleMock adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mock_test);

        name=findViewById(R.id.selheading2);
        recyclerView=findViewById(R.id.mrec);

        name.setText("Mock Test");

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        adapter=new RecycleMock(this,mockTestModels);
        recyclerView.setAdapter(adapter);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            value = extras.getString("key");
            //The key argument here must match that used in the other activity
        }

        getname(value);
        Log.d("TAG", "onCreate: ");
    }
    public void getname(String id) {


        //getting the progressbar
        String url = "https://myexamportals.com/Php/getSelection.php";
        //creating a string request to send request to the url
        Log.d("TAG", "getname: ");
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                response -> {
                    Log.d("TAG", "getname:res ");
                    try {

                        JSONArray tutorialsArray = new JSONArray(response);


                        for (int i = 0; i < tutorialsArray.length(); i++) {
                            //getting the json object of the particular index inside the array
                            JSONObject tutorialsObject = tutorialsArray.getJSONObject(i);
                            MockTestModel mt=new MockTestModel();
                            mt.setName(tutorialsObject.getString("name"));
                            mt.setMarks(tutorialsObject.getInt("marks"));
                            mt.setQuestions(tutorialsObject.getInt("questions"));
                            mt.setTime(tutorialsObject.getInt("time"));

                            //creating a tutorial object and giving them the values from json object
                            Log.d("TAG", "onResponse: " + mt.getName());
                            mockTestModels.add(mt);

                        }
                        adapter.notifyDataSetChanged();


                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.d("TAG", "getnamew: ");
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