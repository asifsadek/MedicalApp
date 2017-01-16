package base.com.medicalapp.fragments;

import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import base.com.medicalapp.R;
import base.com.medicalapp.adapter.ProductAdapter;
import base.com.medicalapp.manager.ApiResponseWrapper;
import base.com.medicalapp.manager.NetworkManager;
import base.com.medicalapp.model.Product;


public class ProductsFragment extends BaseFragment {

    private View fragmentView;
    ProductAdapter productAdapter = new ProductAdapter();
    private RecyclerView productRecyclerView;
    private static final String IMAGE_URL_BASE
            = "Products?api_key=keyOKgBm0Ho3UFLs6";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        fragmentView = (View) inflater.inflate(R.layout.fragment_products, container, false);
        initView();
        return fragmentView;
    }

    private void initView() {

        NetworkManager.get(getContext(), IMAGE_URL_BASE, null, new NetworkManager.NetworkInterface() {
            @Override
            public void onResponse(ApiResponseWrapper baseResponse) {

                if (baseResponse != null && baseResponse.isSuccess()) {
                    Gson gson = new Gson();
                    Product products = gson.fromJson(baseResponse.getJsonObjectResponse().toString(), Product.class);
                    productAdapter.updateData(products.records);

                }

            }
        });
        productRecyclerView = (RecyclerView) fragmentView.findViewById(R.id.productRecyclerView);
        productRecyclerView.setLayoutManager(new LinearLayoutManager(activityContext));
        productRecyclerView.setAdapter(productAdapter);
    }
}
