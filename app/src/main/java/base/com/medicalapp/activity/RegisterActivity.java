package base.com.medicalapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import base.com.medicalapp.R;
import base.com.medicalapp.manager.ApiResponseWrapper;
import base.com.medicalapp.manager.NetworkManager;
import base.com.medicalapp.model.Retailer;
import cz.msebera.android.httpclient.entity.StringEntity;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    private static final String REGISTER_URL
            = "Retailers?api_key=keyOKgBm0Ho3UFLs6";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView() {
        setOnclickListener();
    }

    private void setOnclickListener() {

        findViewById(R.id.registerButton).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.registerButton:
                validateAndRegister();
        }
    }

    private void validateAndRegister() {

        String test = getValueFromView(R.id.pharmacyNameEditText).toString();


        //if (newRetailer.retailerFields.contactName != null && newRetailer.retailerFields.name != null && newRetailer.retailerFields.email != null && newRetailer.retailerFields.address != null &&
        //        newRetailer.retailerFields.tINNo != null && newRetailer.retailerFields.dLNo != null) {

            NetworkManager.post(this.getBaseContext(), REGISTER_URL, getStringEntity(), new NetworkManager.NetworkInterface() {
                @Override
                public void onResponse(ApiResponseWrapper baseResponse) {

                    if (baseResponse != null && baseResponse.isSuccess()) {

                        goToHomePage();
                    }
                    else{

                    }


                 }
            });
        //}
    }

    private StringEntity getStringEntity(){

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("Name",getValueFromView(R.id.pharmacyNameEditText).toString());
            jsonObject.put("Email",getValueFromView(R.id.emailEditText).toString());
            jsonObject.put("Contact Name",getValueFromView(R.id.nameEditText).toString());
            jsonObject.put("TIN No",getValueFromView(R.id.tinNoEditText).toString());
            jsonObject.put("DL No",getValueFromView(R.id.dlNoEditText).toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        StringEntity entity = new StringEntity(jsonObject.toString(),"UTF-8");
        return entity;
    }

}
