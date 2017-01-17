package base.com.medicalapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import base.com.medicalapp.R;
import base.com.medicalapp.manager.ApiResponseWrapper;
import base.com.medicalapp.manager.NetworkConstants;
import base.com.medicalapp.manager.NetworkManager;
import base.com.medicalapp.model.GlobalPreferences;
import base.com.medicalapp.model.Retailer;

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


        if (retailerMobileTextView.getText().toString()!=null) {

            String RETAILER_URL = RETAILER_URL_BASE+"filterByFormula={Mobile}='"+retailerMobileTextView.getText().toString()+"'&api_key=keyOKgBm0Ho3UFLs6";

            Log.v("URL",RETAILER_URL);
            NetworkManager.get(this.getBaseContext(), RETAILER_URL, null, new NetworkManager.NetworkInterface() {

                @Override
                public void onResponse(ApiResponseWrapper baseResponse) {

                    if (baseResponse != null && baseResponse.isSuccess()) {
                        Gson gson = new Gson();
                        Retailer retailerNew = gson.fromJson(baseResponse.getJsonObjectResponse().toString(), Retailer.class);
                        GlobalPreferences.getInstance().setRetailerId(retailerNew.id);
                        goToHomePage();
                    } else {

                        Toast.makeText(getApplicationContext(), "Unable to find retailer.Please check the number entered", Toast.LENGTH_SHORT).show();

                    }


                }
            });
        }
    }
    }

