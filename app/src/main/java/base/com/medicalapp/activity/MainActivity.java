package base.com.medicalapp.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import base.com.medicalapp.R;
import base.com.medicalapp.adapter.PagerAdapter;
import base.com.medicalapp.manager.ApiResponseWrapper;
import base.com.medicalapp.manager.NetworkManager;
import cz.msebera.android.httpclient.entity.StringEntity;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private ViewPager categoryViewPager;

    private LinearLayout selectedLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();


    }



    private void initView() {

        setOnclickListener();
        selectedLayout = (LinearLayout) findViewById(R.id.productsContainer);

        categoryViewPager = (ViewPager) findViewById(R.id.vpPager);

        categoryViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                switch (position) {
                    case 0:
                        setSelection(R.id.productsContainer);
                        break;

                    case 1:
                        setSelection(R.id.schemesContainer);
                        break;
                    case 2:
                        setSelection(R.id.ordersContainer);
                        break;
                    case 3:
                        setSelection(R.id.settingContainer);
                        break;

                }

            }

            @Override
            public void onPageSelected(int position) {


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        categoryViewPager.setAdapter(new PagerAdapter(getSupportFragmentManager()));
    }

    private void setOnclickListener() {

        findViewById(R.id.productsContainer).setOnClickListener(this);
        findViewById(R.id.schemesContainer).setOnClickListener(this);
        findViewById(R.id.ordersContainer).setOnClickListener(this);
        findViewById(R.id.settingContainer).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        Log.v("MAIN ", String.valueOf(v.getId()));

        switch (v.getId()) {

            case R.id.productsContainer:
                categoryViewPager.setCurrentItem(0);
                setSelection(R.id.productsContainer);
                break;
            case R.id.schemesContainer:
                categoryViewPager.setCurrentItem(1);
                setSelection(R.id.schemesContainer);
                break;
            case R.id.ordersContainer:
                categoryViewPager.setCurrentItem(2);
                setSelection(R.id.ordersContainer);
                break;
            case R.id.settingContainer:
                categoryViewPager.setCurrentItem(3);
                setSelection(R.id.settingContainer);
                break;
        }

    }

    private void setSelection(int containerId) {

        selectedLayout.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.color_gray));
        findViewById(containerId).setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimary));
        selectedLayout = (LinearLayout) findViewById(containerId);

    }
}
