package base.com.medicalapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import base.com.medicalapp.R;
import base.com.medicalapp.adapter.OrderAdapter;
import base.com.medicalapp.adapter.SchemeAdapter;
import base.com.medicalapp.manager.ApiResponseWrapper;
import base.com.medicalapp.manager.NetworkManager;
import base.com.medicalapp.model.GlobalPreferences;
import base.com.medicalapp.model.Orders;
import base.com.medicalapp.model.Product;



public class OrdersFragment extends BaseFragment {

    private View fragmentView;
    private RecyclerView orderRecyclerView;
    OrderAdapter orderAdapter = new OrderAdapter();
    private RecyclerView productRecyclerView;
    private static final String ORDER_URL_BASE
            = "Orders/";
    private static final String APIKEY
            = "&api_key=keyOKgBm0Ho3UFLs6";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        fragmentView = (View) inflater.inflate(R.layout.fragment_orders, container, false);
        initView();

        return fragmentView;
    }

    private void initView() {

        String URL_COMPLETE = ORDER_URL_BASE+"filterByFormula=(Retailer=\""+ GlobalPreferences.getInstance().getRetailerID()+APIKEY;
        NetworkManager.get(getContext(), URL_COMPLETE, null, new NetworkManager.NetworkInterface() {
            @Override
            public void onResponse(ApiResponseWrapper baseResponse) {

                if (baseResponse != null && baseResponse.isSuccess()) {
                    Gson gson = new Gson();
                    Orders orders = gson.fromJson(baseResponse.getJsonObjectResponse().toString(), Orders.class);
                    orderAdapter.updateData(orders.orderRecords);
                    Log.d("OrderAdapter records:", String.valueOf(orders.orderRecords));
                }

            }
        });
        orderRecyclerView = (RecyclerView) fragmentView.findViewById(R.id.schemeRecyclerView);
        orderRecyclerView.setLayoutManager(new LinearLayoutManager(activityContext));
        orderRecyclerView.setAdapter(orderAdapter);
    }
}
