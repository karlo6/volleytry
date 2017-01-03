package volleytest.volleytry.com.volleytest;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Button mBtnAdd;
    private ProgressDialog mPdiggy;

    private String TAG = "VOLLEY_ADD_PRODUCT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtnAdd = (Button) findViewById(R.id.btnAdd);

        mPdiggy = new ProgressDialog(this);
        mPdiggy.setMessage("Loading...");
        mPdiggy.show();

        String url = "http://taisondigital.com.ph/testforyou/add-product";

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());
                mPdiggy.hide();

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d(TAG, "Error: " + error.getMessage());
                        mPdiggy.hide();
                    }
                }) {
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

        AppController.getInstance().addToRequestQueue(jsonObjReq, TAG);

    }

}
