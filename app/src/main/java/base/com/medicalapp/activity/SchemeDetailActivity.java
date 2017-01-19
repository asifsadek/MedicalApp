package base.com.medicalapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

import base.com.medicalapp.R;
import base.com.medicalapp.manager.ApiResponseWrapper;
import base.com.medicalapp.manager.NetworkManager;
import base.com.medicalapp.model.GlobalPreferences;
import base.com.medicalapp.model.SchemeFields;
import base.com.medicalapp.model.SchemeRecord;

public class SchemeDetailActivity extends AppCompatActivity  implements View.OnClickListener{

    private static final String Schemes_URL_BASE
            = "Schemes/";
    private static final String apiKey = "?api_key=keyOKgBm0Ho3UFLs6";
    private SchemeRecord schemeRecord;
    private TextView schemeNameTextView;
    private TextView schemeExpiryDateTextView;
    private TextView schemeTypeTextView;
    private TextView schemeDescriptionTextview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        String record = this.getIntent().getExtras().getString("record");
        String schemeURL = Schemes_URL_BASE+record+apiKey;
        setContentView(R.layout.activity_scheme_detail);

        getSchemeDetails(schemeURL);

    }

    private void getSchemeDetails(String schemeURL) {


        NetworkManager.get(getApplicationContext(), schemeURL, null, new NetworkManager.NetworkInterface() {
            @Override
            public void onResponse(ApiResponseWrapper baseResponse) {

                if (baseResponse != null && baseResponse.isSuccess()) {
                    Gson gson = new Gson();

                    schemeRecord = gson.fromJson(baseResponse.getJsonObjectResponse().toString(), SchemeRecord.class);
                    initViews(schemeRecord.fields);

                }

            }
        });
    }


    public void initViews(SchemeFields schemeFields) {

        schemeNameTextView = (TextView) findViewById(R.id.textOrder);
        schemeExpiryDateTextView = (TextView) findViewById(R.id.textViewExpiryDate);
        schemeTypeTextView = (TextView) findViewById(R.id.textViewSchemeType);
        schemeDescriptionTextview = (TextView) findViewById(R.id.textViewDescription);


        schemeNameTextView.setText(schemeFields.name);
        schemeExpiryDateTextView.setText(dateToDisplay(schemeFields.expiryDate));
        schemeTypeTextView.setText(schemeFields.type);
        schemeDescriptionTextview.setText(schemeFields.description);
        setOnclickListener();

    }

    public String dateToDisplay(String dateFromJSON){

        String date = dateFromJSON.substring(8,10)+"/"+dateFromJSON.substring(5,7)+"/"+dateFromJSON.substring(0,4);
        return date;
    }

    private void setOnclickListener() {

        findViewById(R.id.buttonAddScheme).setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.buttonAddCart:
                addSchemeToCart();
                break;

        }
    }

    public void addSchemeToCart(){

        GlobalPreferences.getInstance().addSchemeToCart(schemeRecord.id);


    }

}

