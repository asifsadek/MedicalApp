package base.com.medicalapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import base.com.medicalapp.R;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {

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

        String name = getValueFromView(R.id.nameEditText);
        String pharmacyName = getValueFromView(R.id.pharmacyNameEditText);
        String email = getValueFromView(R.id.emailEditText);
        String address = getValueFromView(R.id.addressEditText);
        String tinNo = getValueFromView(R.id.tinNoEditText);
        String dlNo = getValueFromView(R.id.dlNoEditText);
        String password = getValueFromView(R.id.passwordEditText);


        if (name != null && pharmacyName != null && email != null && address != null &&
                tinNo != null && dlNo != null && password != null) {



        }else{

        }


    }
}
