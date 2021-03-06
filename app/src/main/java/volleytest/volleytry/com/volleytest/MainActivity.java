package volleytest.volleytry.com.volleytest;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private String TAG = "VOLLEY_ADD_PRODUCT";
    private ProgressDialog pDiggy;
    String url = "http://taisondigital.com.ph/testforyou/add-product";

    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        pDiggy = new ProgressDialog(this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pDiggy.setMessage("Loading...");
                pDiggy.show();

                try {
                    postRequest();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });


    }


    private void postRequest() throws JSONException {
        JSONObject jsonobject = new JSONObject();
        jsonobject.put("name", " asf");
        jsonobject.put("quantity", "sf");
        jsonobject.put("price", "asfgh");

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, url, jsonobject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());
                pDiggy.hide();

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d(TAG, "Error: " + error.getMessage());
                        pDiggy.hide();
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError{
                HashMap<String, String> header = new HashMap<String, String>();
                header.put("Content-Type", "application/json; charset=utf-8");

                return header;
            }

        };
        AppController.getInstance().addToRequestQueue(jsonObjReq, TAG);
    }

}
