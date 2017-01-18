package base.com.medicalapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.gson.Gson;

import base.com.medicalapp.R;
import base.com.medicalapp.adapter.CartAdapter;
import base.com.medicalapp.adapter.SchemeAdapter;
import base.com.medicalapp.manager.ApiResponseWrapper;
import base.com.medicalapp.manager.NetworkManager;
import base.com.medicalapp.model.GlobalPreferences;
import base.com.medicalapp.model.OrderItems;


public class CartFragment extends BaseFragment {

    private View fragmentView;
    private RecyclerView cartRecyclerView;
    CartAdapter cartAdapter = new CartAdapter();
    private static final String ORDER_ITEMS_URL_BASE
            = "Order%20Items/";
    private static final String APIKEY
            = "'&api_key=keyOKgBm0Ho3UFLs6";
    private Button placeOrderButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        fragmentView = (View) inflater.inflate(R.layout.fragment_setting, container, false);
        placeOrderButton = (Button) fragmentView.findViewById(R.id.placeOrderButton);
        placeOrderButton.setOnClickListener(this);
        initView();
        return fragmentView;
    }

    private void initView() {

        String URL_COMPLETE = ORDER_ITEMS_URL_BASE+ "filterByFormula={ID}='"+ GlobalPreferences.getInstance().getOrderId()+APIKEY;

        NetworkManager.get(getContext(), URL_COMPLETE, null, new NetworkManager.NetworkInterface() {
            @Override
            public void onResponse(ApiResponseWrapper baseResponse) {

                if (baseResponse != null && baseResponse.isSuccess()) {
                    Gson gson = new Gson();
                    OrderItems orderItems = gson.fromJson(baseResponse.getJsonObjectResponse().toString(), OrderItems.class);
                    cartAdapter.updateData(orderItems.records);

                }

            }
        });


        cartRecyclerView = (RecyclerView) fragmentView.findViewById(R.id.cartRecyclerView);
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(activityContext));
        cartRecyclerView.setAdapter(cartAdapter);


    }

    @Override
    public void onClick(View v) {

        GlobalPreferences.getInstance().confirmOrder();
        initView();
    }


}
