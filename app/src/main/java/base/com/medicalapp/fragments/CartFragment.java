package base.com.medicalapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import base.com.medicalapp.R;
import base.com.medicalapp.adapter.SchemeAdapter;


public class CartFragment extends BaseFragment {

    private View fragmentView;
    private RecyclerView cartRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        fragmentView = (View) inflater.inflate(R.layout.fragment_setting, container, false);
        initView();
        return fragmentView;
    }

    private void initView() {


        cartRecyclerView = (RecyclerView) fragmentView.findViewById(R.id.cartRecyclerView);
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(activityContext));

    }
}
