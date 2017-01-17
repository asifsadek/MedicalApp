package base.com.medicalapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import base.com.medicalapp.R;
import base.com.medicalapp.manager.ApiResponseWrapper;
import base.com.medicalapp.manager.NetworkManager;
import base.com.medicalapp.model.GlobalPreferences;
import base.com.medicalapp.model.Orders;
import base.com.medicalapp.model.Retailer;
import base.com.medicalapp.model.RetailerFields;
import cz.msebera.android.httpclient.entity.StringEntity;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    private static final String REGISTER_URL
            = "Retailers?api_key=keyOKgBm0Ho3UFLs6";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Log.v("JSON", "on create");
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
        Log.v("JSON", "on click");
        switch (v.getId()) {

            case R.id.registerButton:
                validateAndRegister();
        }
    }

    private void validateAndRegister() {

        Retailer newRetailer = getNewRetailerValues();
        Gson gson = new Gson();
        String retailerJson = gson.toJson(newRetailer);
        StringEntity retailerRegistrationEntity = new StringEntity(retailerJson, "UTF-8");
        retailerRegistrationEntity.setContentType("application/json");

        if (newRetailer.retailerFields.contactName != null && newRetailer.retailerFields.name != null && newRetailer.retailerFields.email != null && newRetailer.retailerFields.address != null &&
                newRetailer.retailerFields.tINNo != null && newRetailer.retailerFields.dLNo != null) {


            NetworkManager.post(this.getBaseContext(), REGISTER_URL, retailerRegistrationEntity, new NetworkManager.NetworkInterface() {
                @Override
                public void onResponse(ApiResponseWrapper baseResponse) {

                    if (baseResponse != null && baseResponse.isSuccess()) {
                        Gson gson = new Gson();
                        Retailer retailerNew = gson.fromJson(baseResponse.getJsonObjectResponse().toString(), Retailer.class);
                        GlobalPreferences.getInstance().setRetailerId(retailerNew.id);
                        goToHomePage();
                    } else {
                        Toast.makeText(getApplicationContext(), "Please enter all required information to proceed", Toast.LENGTH_SHORT).show();
                    }


                }
            });
            }
        }

    private Retailer getNewRetailerValues() {

        TextView pharmacyNameView = (TextView) findViewById(R.id.pharmacyNameEditText);
        TextView emailTextView = (TextView) findViewById(R.id.emailEditText);
        TextView nameTextView = (TextView) findViewById(R.id.nameEditText);
        TextView TINTextView = (TextView) findViewById(R.id.tinNoEditText);
        TextView DLTextView = (TextView) findViewById(R.id.dlNoEditText);
        TextView addressTextView = (TextView)findViewById(R.id.addressEditText);
        TextView mobileTextView = (TextView)findViewById(R.id.passwordEditText);

        RetailerFields retailerFields = new RetailerFields();
        retailerFields.name = pharmacyNameView.getText().toString();
        retailerFields.email = emailTextView.getText().toString();
        retailerFields.contactName = nameTextView.getText().toString();
        retailerFields.tINNo = TINTextView.getText().toString();
        retailerFields.dLNo = DLTextView.getText().toString();
        retailerFields.address = addressTextView.getText().toString();
        retailerFields.mobile = mobileTextView.getText().toString();

        Retailer retailer = new Retailer();
        retailer.retailerFields = retailerFields;
        return retailer;

    }

}
