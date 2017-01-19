package base.com.medicalapp.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import base.com.medicalapp.R;
import base.com.medicalapp.manager.ApiResponseWrapper;
import base.com.medicalapp.manager.NetworkManager;
import base.com.medicalapp.model.GlobalPreferences;
import base.com.medicalapp.model.Retailer;
import base.com.medicalapp.model.RetailerRecord;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private static final String RETAILER_URL_BASE
            = "Retailers?";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        GlobalPreferences.getInstance().init(this);
        initViews();
    }

    private void initViews() {

        setOnclickListener();
    }

    private void setOnclickListener() {

        findViewById(R.id.loginButton).setOnClickListener(this);
        findViewById(R.id.registerButton).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.loginButton:
                validateAndCallLogin();
                break;

            case R.id.registerButton:
                goToRegisterPage();
                break;
        }
    }

    private void validateAndCallLogin() {

        TextView retailerMobileTextView = (TextView)findViewById(R.id.phoneNumber);

        String retailerMobile = retailerMobileTextView.getText().toString().trim();
        if (!retailerMobile.isEmpty()) {

            String RETAILER_URL = RETAILER_URL_BASE+"filterByFormula={Mobile}='"+retailerMobileTextView.getText().toString()+"'&api_key=keyOKgBm0Ho3UFLs6";

            Log.v("URL",RETAILER_URL);
            NetworkManager.get(this.getBaseContext(), RETAILER_URL, null, new NetworkManager.NetworkInterface() {

                @Override
                public void onResponse(ApiResponseWrapper baseResponse) {

                    if (baseResponse != null && baseResponse.isSuccess()) {
                        Gson gson = new Gson();
                        Retailer retailerNew = gson.fromJson(baseResponse.getJsonObjectResponse().toString(), Retailer.class);
                        RetailerRecord retailerRecord = retailerNew.records.get(0);
                        if(retailerRecord.id!=null) {
                            GlobalPreferences.getInstance().setRetailerRecordId(retailerRecord.id);
                            GlobalPreferences.getInstance().setRetailerID(retailerRecord.retailerFields.mobile.toString());
                            goToHomePage();
                        }else{
                            displayLoginErrorToastMessage();
                        }
                    } else {

                        displayLoginErrorToastMessage();

                    }


                }
            });
        }
    }

    private void displayLoginErrorToastMessage(){

        Toast.makeText(getApplicationContext(), "Unable to find retailer.Please check the number entered", Toast.LENGTH_SHORT).show();

    }
    }

