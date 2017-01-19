package base.com.medicalapp.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import base.com.medicalapp.R;
import base.com.medicalapp.manager.ApiResponseWrapper;
import base.com.medicalapp.manager.NetworkManager;
import base.com.medicalapp.model.GlobalPreferences;
import base.com.medicalapp.model.ProductFields;
import base.com.medicalapp.model.ProductRecord;

public class ProductDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String PRODUCT_URL_BASE
            = "Products/";
    private static final String apiKey = "?api_key=keyOKgBm0Ho3UFLs6";
    private ProductRecord productRecord;
    private TextView productNameTextView;
    private TextView unitPackTextView;
    private TextView formTextView;
    private TextView compositionTextView;
    private TextView typeTextView;
    private TextView mrpTextView;
    private EditText quantityTextField;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        String record = this.getIntent().getExtras().getString("record");
        String productURL = PRODUCT_URL_BASE+record+apiKey;
        setContentView(R.layout.activity_product_detail);

        getProductDetails(productURL);

    }

    private void getProductDetails(String productURL) {

        Log.d("MEDIAPP", productURL);
        NetworkManager.get(getApplicationContext(), productURL, null, new NetworkManager.NetworkInterface() {
            @Override
            public void onResponse(ApiResponseWrapper baseResponse) {

                if (baseResponse != null && baseResponse.isSuccess()) {
                    Gson gson = new Gson();

                    productRecord = gson.fromJson(baseResponse.getJsonObjectResponse().toString(), ProductRecord.class);
                    initViews(productRecord.fields);

                }

            }
        });
    }


    public void initViews(ProductFields productFields) {

        productNameTextView = (TextView) findViewById(R.id.textOrder);
        unitPackTextView = (TextView) findViewById(R.id.textExpiry);
        formTextView = (TextView) findViewById(R.id.textViewSchemeType);
        compositionTextView = (TextView) findViewById(R.id.textViewComposition);
        typeTextView = (TextView) findViewById(R.id.textViewType);
        mrpTextView  = (TextView) findViewById(R.id.textViewMRP);
        quantityTextField = (EditText)findViewById(R.id.editTextQuantity);

        productNameTextView.setText(productFields.name);
        unitPackTextView.setText(productFields.pack);
        formTextView.setText(productFields.form);
        compositionTextView.setText(productFields.composition);
        typeTextView.setText(typeStringFor(productFields));
        mrpTextView.setText(String.valueOf(productFields.mRP));

        setOnclickListener();
    }

    private void setOnclickListener() {

        findViewById(R.id.buttonAddCart).setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.buttonAddCart:
                addProductToCart();
                break;

        }
    }
    public String typeStringFor(ProductFields productFields){

        String type = "";
       for(String typeString:productFields.type){
           type= type + typeString+",";
       }
        return type;
    }

    public void addProductToCart(){

        if(!quantityTextField.getText().toString().isEmpty()) {
            int quantity = Integer.parseInt(quantityTextField.getText().toString());
            if (quantity > 0) {

                GlobalPreferences.getInstance().addProductToOrder(productRecord.id, quantity);
                Toast.makeText(getApplicationContext(), "Added Product to cart", Toast.LENGTH_SHORT).show();


            } else
                showErrorToast();
        }

    }

    private void showErrorToast(){

        Toast.makeText(getApplicationContext(), "Please enter a valid quantity", Toast.LENGTH_SHORT).show();

    }

    }

