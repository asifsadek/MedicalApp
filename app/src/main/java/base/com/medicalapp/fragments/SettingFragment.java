package base.com.medicalapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import base.com.medicalapp.R;

/**
 * Created by Abhishek on 1/7/2017.
 */

public class SettingFragment extends BaseFragment {

    private View fragmentView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        fragmentView = (View) inflater.inflate(R.layout.fragment_setting, container, false);

        return fragmentView;
    }
}
