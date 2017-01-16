package base.com.medicalapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import base.com.medicalapp.R;
import base.com.medicalapp.manager.ApiResponseWrapper;
import base.com.medicalapp.manager.NetworkManager;
import base.com.medicalapp.model.Retailer;

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
        goToHomePage();
        Retailer newRetailer = new Retailer();
        newRetailer.retailerFields.contactName = getValueFromView(R.id.nameEditText);
        newRetailer.retailerFields.name = getValueFromView(R.id.pharmacyNameEditText);
        newRetailer.retailerFields.email = getValueFromView(R.id.emailEditText);
        newRetailer.retailerFields.address = getValueFromView(R.id.addressEditText);
        newRetailer.retailerFields.tINNo = getValueFromView(R.id.tinNoEditText);
        newRetailer.retailerFields.dLNo = getValueFromView(R.id.dlNoEditText);
        String password = getValueFromView(R.id.passwordEditText);


        if (newRetailer.retailerFields.contactName != null && newRetailer.retailerFields.name != null && newRetailer.retailerFields.email != null && newRetailer.retailerFields.address != null &&
                newRetailer.retailerFields.tINNo != null && newRetailer.retailerFields.dLNo != null && password != null) {

            //NetworkManager.post(this.getBaseContext(), REGISTER_URL, null, new NetworkManager.NetworkInterface() {
             //   @Override
              //  public void onResponse(ApiResponseWrapper baseResponse) {

                //    if (baseResponse != null && baseResponse.isSuccess()) {

        }else{

        }


    }
}
