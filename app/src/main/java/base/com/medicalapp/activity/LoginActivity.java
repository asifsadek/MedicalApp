package base.com.medicalapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import base.com.medicalapp.R;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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
        goToHomePage();
    }
}
