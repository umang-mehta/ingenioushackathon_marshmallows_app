package com.example.me.inghack.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.me.inghack.R;
import com.example.me.inghack.helper.HttpHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.example.me.inghack.helper.Constants.CategoryAPI;
import static com.example.me.inghack.helper.Constants.PRODUCTBYCategoryAPI;

public class LicenseInfoActivity extends AppCompatActivity {

    TextView licenseNo;
    String license = "";
    private ProgressDialog pDialog;
    private String TAG = LicenseInfoActivity.class.getSimpleName();
    String dlno="",doi="",cdoi="",cov="",validFrom="",valitTill="",uName="",sonof="",dob="",address="",email="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_license_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Intent get_selected = getIntent();
        license = (String) get_selected.getExtras().get("LicenseInfo");
        Toast.makeText(this, "Dl no :" + license, Toast.LENGTH_SHORT).show();
        licenseNo = (TextView) findViewById(R.id.licenseNO);
        licenseNo.setText("" + license);

    }



    /**
     * Async task class to get json by making HTTP call
     */
    private class GetCategories extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(LicenseInfoActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();


            String[] name = {"dl no"};
            String[] value = {"" + license};

            // Making a request to url and getting response
            String jsonStr = sh.PostCall(PRODUCTBYCategoryAPI, name, value);
            Log.e(TAG, "Response from url: " + jsonStr);


            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray category = jsonObj.getJSONArray("categories");


                    JSONObject c = category.getJSONObject(0);

                    dlno = c.getString("dlno");
                    doi = c.getString("dlno");
                    cdoi = c.getString("dlno");
                    cov = c.getString("dlno");
                    validFrom = c.getString("dlno");
                    valitTill = c.getString("dlno");
                    uName = c.getString("dlno");
                    sonof = c.getString("dlno");
                    dob = c.getString("dlno");
                    address = c.getString("dlno");
                    email = c.getString("dlno");


                    // looping through All Contacts
//                    for (int i = 0; i < category.length(); i++) {
//                        JSONObject c = category.getJSONObject(i);
//
//                        String catId = c.getString("catId");
//                        String catName = c.getString("catName");
//                        String catImg = c.getString("catImg");
//
//                        // Phone node is JSON Object
//                        JSONObject phone = c.getJSONObject("phone");
//                        String mobile = phone.getString("mobile");
//                        String home = phone.getString("home");
//                        String office = phone.getString("office");

                        // tmp hash map for single contact
                        //HashMap<String, String> contact = new HashMap<>();

                        // adding each child node to HashMap key => value
                        //contact.put("id", id);
                        //contact.put("name", name);
                        //contact.put("email", email);
                        //contact.put("mobile", mobile);

                        //****************************************************************

                        //FeddProperties feed = new FeddProperties();
  //                      CategoriesDTO Cdto = new CategoriesDTO(catId, catName, catImg);
                        //feed.setTitle(catName);

                        //feed.setThumbnail(icons[i]);
                        //feed.setThumbnail(catImg);

                        //os_versions.add(feed);
    //                    cat_list.add(Cdto);

//                        Log.e("u", "os versions: " + cat_list);

                        //****************************************************************
                        // adding contact to contact list
                        //contactList.add(contact);
//                    }
                }
                catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();



            /**
             * Updating parsed JSON data into ListView
             * */
//            ListAdapter adapter = new SimpleAdapter(
//                    MainActivity.this, contactList,
//                    R.layout.list_item, new String[]{"name", "email",
//                    "mobile"}, new int[]{R.id.name,
//                    R.id.email, R.id.mobile});
//
//            lv.setAdapter(adapter);
            //displayList();


            //ActionBar actionBar = getSupportActionBar();
            //actionBar.setTitle(getString(R.string.CatTitle));

//            LinearLayoutManager layoutManager = new LinearLayoutManager(LicenseInfoActivity.this);
//            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);


            //recyclerView.setHasFixedSize(true);

            //LinearLayoutManager layoutManager = new GridLayoutManager(MainActivity.this, 2, 1, false);
            //layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            //recyclerView.setLayoutManager(layoutManager);


//            final LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
//            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            //recyclerView.setLayoutManager(layoutManager);

            // ListView
            //recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

            //Grid View


//            recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 2, 1, false));
//
//            //StaggeredGridView
//            //recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,1));
//
//            Log.d("u", "os_versions====>" + cat_list);
//            // create an Object for Adapter
//            mAdapter = new CardViewDataAdapter(cat_list, LicenseInfoActivity.this);
//            Log.d("u", "adapter====>" + mAdapter);
//            // set the adapter object to the Recyclerview
//            recyclerView.setAdapter(mAdapter);

        }

    }
}
