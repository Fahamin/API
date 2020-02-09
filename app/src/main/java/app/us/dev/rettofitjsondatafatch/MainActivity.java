package app.us.dev.rettofitjsondatafatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import static com.android.volley.Request.*;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    String apiurl = "https://api.myjson.com/bins/6zjls";
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.txtId);
        mRequestQueue = Volley.newRequestQueue(this);

        GetJsonData();

    }

    private void GetJsonData() {

        final JsonObjectRequest objectRequest = new JsonObjectRequest(Method.GET, apiurl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    for (int i = 1; i <=response.length() ; i++) {
                        String valu = Integer.toString(i);
                        JSONObject jsonObject = response.getJSONObject(valu);

                            String name = jsonObject.getString("name");
                            String sal = jsonObject.getString("salary");

                            textView.append(name + " " + sal + "/n");

                    }

                    //    Log.d("d",name);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        mRequestQueue.add(objectRequest);
    }
}

/*{
        "1": {
        "name": "Sachin",
        "salary": 56000
        },
        "2": {
        "name": "Sahin",
        "salary": 57000
        },
        "3": {
        "name": "chin",
        "salary": 58000
        },
        "4": {
        "name": "hin",
        "salary": 1000
        }
        }*/
