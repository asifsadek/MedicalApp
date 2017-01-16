package base.com.medicalapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;



public class BaseActivity extends AppCompatActivity {

    protected void goToRegisterPage() {

        Intent mainIntent = new Intent(this, RegisterActivity.class);
        startActivity(mainIntent);
        finish();
    }

    protected void goToHomePage() {

        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
        finish();
    }



    protected   String getValueFromView(int id){
        return "";
    }

    protected  void setValueFromView(View view , String value){

    }
}
