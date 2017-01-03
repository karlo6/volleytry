package volleytest.volleytry.com.volleytest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Taison_Gary on 1/3/2017.
 */

public class AppController extends AppCompatActivity {

    public static final String TAG = AppController.class
            .getSimpleName();




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appcontroller);
    }


    RequestQueue queue = Volley.newRequestQueue(this);


    String url = "http://taisondigital.com.ph/testforyou/add-product";


    JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, url, new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {
            Log.d("Response", response.toString());

        }
    },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("Error.Response", response.toString());

                }
            }
    ) {
        @Override
        protected Map<String, String> getParams() {
            Map<String, String> params = new HashMap<String, String>();
            params.put("name", "asd");
            params.put("quantity", "123");
            params.put("price", "12345");
            Log.e("err", params.toString());

            return params;
        }
    };
    queue.add(jsonObjReq);
}

