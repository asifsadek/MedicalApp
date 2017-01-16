package base.com.medicalapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import base.com.medicalapp.R;
import base.com.medicalapp.adapter.ProductAdapter;
import base.com.medicalapp.adapter.SchemeAdapter;
import base.com.medicalapp.manager.ApiResponseWrapper;
import base.com.medicalapp.manager.NetworkManager;
import base.com.medicalapp.model.Product;
import base.com.medicalapp.model.Scheme;



public class SchemesFragment extends BaseFragment {

    SchemeAdapter schemeAdapter = new SchemeAdapter();
    private View fragmentView;
    private RecyclerView schemeRecyclerView;
    private static final String ORDER_SCHEME_URL
            = "Schemes?api_key=keyOKgBm0Ho3UFLs6";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        fragmentView = (View) inflater.inflate(R.layout.fragment_schemes, container, false);
        initView();
        return fragmentView;
    }

    private void initView() {

        NetworkManager.get(getContext(), ORDER_SCHEME_URL, null, new NetworkManager.NetworkInterface() {
            @Override
            public void onResponse(ApiResponseWrapper baseResponse) {

                if (baseResponse != null && baseResponse.isSuccess()) {
                    Gson gson = new Gson();
                    Scheme schemes = gson.fromJson(baseResponse.getJsonObjectResponse().toString(), Scheme.class);
                    schemeAdapter.updateData(schemes.records);

                }

            }
        });
        schemeRecyclerView = (RecyclerView) fragmentView.findViewById(R.id.schemeRecyclerView);
        schemeRecyclerView.setLayoutManager(new LinearLayoutManager(activityContext));
        schemeRecyclerView.setAdapter(new SchemeAdapter());
    }
}
