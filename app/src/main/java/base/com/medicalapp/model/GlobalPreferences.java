package base.com.medicalapp.model;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import base.com.medicalapp.manager.ApiResponseWrapper;
import base.com.medicalapp.manager.NetworkManager;
import cz.msebera.android.httpclient.entity.StringEntity;

/**
 * Created by ctsuser1 on 1/16/17.
 */

public class GlobalPreferences {
    private static GlobalPreferences mInstance = null;
    private static final String ORDERS_URL_BASE
            = "Orders?api_key=keyOKgBm0Ho3UFLs6";
    private String retailerId;
    private List<OrderFields> cartProducts;
    private String orderId;
    private static Context context;
    private OrderItemRecords orderItemrecord;

    private GlobalPreferences(){
        retailerId = "0";
        orderId = "0";

    }

    public static GlobalPreferences getInstance(){
        if(mInstance == null)
        {
            mInstance = new GlobalPreferences();
        }
        return mInstance;
    }

    public void init(Context context){

        this.context = context.getApplicationContext();
    }

    public void setOrderItemRecord(int orderId){

        this.orderItemrecord.fields.iD = orderId;
    }

    public String getRetailerId(){
        return this.retailerId;
    }

    public String getOrderId(){
        return this.orderId;
    }

    public List<OrderFields> getCartProducts(){
        return this.cartProducts;
    }

    public void setOrderId(String value){

        orderId = value;
    }

    public void addToCart(OrderFields product){

        cartProducts.add(product);
    }


    public void setRetailerId(String value){
        retailerId = value;
    }

    private Orders createNewOrder(){

        Orders order = new Orders();
        OrderRecord newOrderRecord = new OrderRecord();
        newOrderRecord.orderFields.status="UNCONFIRMED";
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        newOrderRecord.orderFields.date = date.toString();
        order.orderRecords.add(newOrderRecord);
        return order;

    }

    public void sendCreateOrderRequest(){

        Gson gson = new Gson();
        String retailerJson = gson.toJson(createNewOrder());
        StringEntity createOrderEntity = new StringEntity(retailerJson, "UTF-8");
        createOrderEntity.setContentType("application/json");

        NetworkManager.post(context, ORDERS_URL_BASE, createOrderEntity, new NetworkManager.NetworkInterface() {
            @Override
            public void onResponse(ApiResponseWrapper baseResponse) {

                if (baseResponse != null && baseResponse.isSuccess()) {
                    Gson gson = new Gson();
                    OrderRecord ordersNew = gson.fromJson(baseResponse.getJsonObjectResponse().toString(), OrderRecord.class);
                    setOrderId(ordersNew.id);
                    //set new order ID for order items
                    setOrderItemRecord(ordersNew.orderFields.iD);


                }
            }
        });


}
    }
