package com.example.me.inghack.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.me.inghack.R;
import com.example.me.inghack.helper.HttpHandler;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.example.me.inghack.helper.Constants.CategoryAPI;
import static com.example.me.inghack.helper.Constants.LicenseInfo;
import static com.example.me.inghack.helper.Constants.PRODUCTBYCategoryAPI;

public class LicenseInfoActivity extends AppCompatActivity {

    TextView tx_dlno, tx_doi, tx_cdoi, tx_cov, tx_validFrom, tx_valitTill, tx_name, tx_sonOf, tx_dob, tx_address;
    String license = "";
    private ProgressDialog pDialog;
    ImageView pic;
    private String TAG = LicenseInfoActivity.class.getSimpleName();
    static String dlno = "", doi = "", cdoi = "", cov = "", validFrom = "", valitTill = "", uName = "", sonof = "", dob = "", address = "", image = "", email = "";
    ImageLoader imageLoader;
    DisplayImageOptions options;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_license_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        actionBar = getSupportActionBar();
        //actionBar.setTitle(getString(R.string.CatTitle));


        setLicId();

        Intent get_selected = getIntent();
        license = (String) get_selected.getExtras().get("LicenseInfo");


        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(LicenseInfoActivity.this)

                // Thread priority

                .threadPriority(Thread.NORM_PRIORITY)

                // Deny cache multiple image sizes on memory

                .denyCacheImageMultipleSizesInMemory()

                // Processing order like a stack (last in, first out)

                .tasksProcessingOrder(QueueProcessingType.LIFO)

                // Max image size to cache on memory

                .memoryCacheSize(1 * 1024 * 2014)

                // Max image size to cache on disc

                .diskCacheSize(2 * 1024 * 1024)

                // Write log messages

                .writeDebugLogs()

                .build();

        imageLoader = ImageLoader.getInstance();
        imageLoader.init(config);
        // Define image display options


        options = new DisplayImageOptions.Builder()

                // Cache loaded image in memory and disc

                .cacheOnDisk(true)

                .cacheInMemory(true)

                // Show Android icon while loading

                .showImageOnLoading(R.drawable.ic_menu_gallery)

                .build();


        new GetLicenseInfo().execute();

    }

    private void setLicId() {

        pic = (ImageView) findViewById(R.id.pic);
        tx_dlno = (TextView) findViewById(R.id.ldlno);
        tx_doi = (TextView) findViewById(R.id.doi);
        tx_cdoi = (TextView) findViewById(R.id.cdoi);
        tx_cov = (TextView) findViewById(R.id.cov);
        tx_validFrom = (TextView) findViewById(R.id.validFrom);
        tx_valitTill = (TextView) findViewById(R.id.valitTill);
        tx_name = (TextView) findViewById(R.id.name);
        tx_sonOf = (TextView) findViewById(R.id.sonOf);
        tx_dob = (TextView) findViewById(R.id.dob);
        tx_address = (TextView) findViewById(R.id.address);
    }


    /**
     * Async task class to get json by making HTTP call
     */
    private class GetLicenseInfo extends AsyncTask<Void, Void, Void> {

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


            String[] name = {"dl_no"};
            String[] value = {"" + license};

            // Making a request to url and getting response
            String jsonStr = sh.PostCall(LicenseInfo, name, value);
            Log.e(TAG, "Response from url: " + jsonStr);


            if (jsonStr != null) {
                try {

                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray myLicense = jsonObj.getJSONArray("license_info");
                    Log.d(TAG, "mylicense : " + myLicense);

                    JSONObject c = myLicense.getJSONObject(0);
                    //JSONObject c = jsonObj.getJSONObject("license_info");


                    //JSONObject c = myLicense.getJSONObject(0);

                    dlno = c.getString("dl_no");
                    doi = c.getString("doi");
                    cdoi = c.getString("cdoi");
                    cov = c.getString("cov");
                    validFrom = c.getString("valid_from");
                    valitTill = c.getString("valid_till");
                    uName = c.getString("name");
                    sonof = c.getString("son_of");
                    dob = c.getString("dob");
                    address = c.getString("address");
                    image = c.getString("image");
                    email = c.getString("email_id");



                } catch (final JSONException e) {
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


            Toast.makeText(LicenseInfoActivity.this, "dl_no: " + image, Toast.LENGTH_SHORT).show();
            tx_dlno.setText("DL no. :" + dlno);
            tx_doi.setText("DOI. :" + doi);
            tx_cdoi.setText("CDOI. :" + cdoi);
            tx_cov.setText("COV. :" + cov);
            tx_validFrom.setText("Valid From. :" + validFrom);
            tx_valitTill.setText("Valid Till. :" + valitTill);
            tx_name.setText("Name. :" + uName);
            tx_sonOf.setText("S/O. :" + sonof);
            tx_dob.setText("D.O.B. :" + dob);
            tx_address.setText("Address. :" + address);

            actionBar.setTitle(""+uName);

            imageLoader.loadImage(image, options, new SimpleImageLoadingListener() {
                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    // Do whatever you want with Bitmap
                    Drawable dimg = new BitmapDrawable(LicenseInfoActivity.this.getResources(), loadedImage);
                    pic.setBackground(dimg);
                }
            });


        }

    }
}
