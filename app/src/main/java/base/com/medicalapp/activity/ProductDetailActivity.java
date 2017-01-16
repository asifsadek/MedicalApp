package base.com.medicalapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.util.List;

import base.com.medicalapp.R;
import base.com.medicalapp.manager.ApiResponseWrapper;
import base.com.medicalapp.manager.NetworkManager;
import base.com.medicalapp.model.Product;
import base.com.medicalapp.model.ProductFields;
import base.com.medicalapp.model.ProductRecord;

public class ProductDetailActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        String record = this.getIntent().getExtras().getString("record");
        String productURL = PRODUCT_URL_BASE+record+apiKey;
        setContentView(R.layout.activity_product_detail);
        Log.d("MEDIAPP",productURL);
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

        productNameTextView = (TextView) findViewById(R.id.textViewProductDetail);
        unitPackTextView = (TextView) findViewById(R.id.textViewUnitPack);
        formTextView = (TextView) findViewById(R.id.textViewForm);
        compositionTextView = (TextView) findViewById(R.id.textViewComposition);
        typeTextView = (TextView) findViewById(R.id.textViewType);
        mrpTextView  = (TextView) findViewById(R.id.textViewMRP);

        productNameTextView.setText(productFields.name);
        unitPackTextView.setText(productFields.pack);
        formTextView.setText(productFields.form);
        compositionTextView.setText(productFields.composition);
        typeTextView.setText(typeStringFor(productFields));
        mrpTextView.setText(String.valueOf(productFields.mRP));
    }

    public String typeStringFor(ProductFields productFields){

        String type = "";
       for(String typeString:productFields.type){
           type= type + typeString+",";
       }
        return type;
    }

    }

