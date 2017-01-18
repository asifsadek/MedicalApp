package base.com.medicalapp.model;

import android.content.Context;
import android.util.Log;
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
            = "Orders";
    private static final String ORDER_ITEMS_URL_BASE
            = "Order%20Items?api_key=keyOKgBm0Ho3UFLs6";
    private static final String APIKEY = "?api_key=keyOKgBm0Ho3UFLs6";
    private String retailerId;
    private List<OrderFields> cartProducts;
    private String orderId;
    private static Context context;
    private OrderItemRecords orderItemrecord;

    private GlobalPreferences() {
        retailerId = "0";
        orderId = "0";

    }

    public static GlobalPreferences getInstance() {
        if (mInstance == null) {
            mInstance = new GlobalPreferences();
        }
        return mInstance;
    }

    public void init(Context context) {

        this.context = context.getApplicationContext();
    }

    public void setOrderItemRecord(int orderId) {

        this.orderItemrecord.fields.iD = orderId;
    }

    public String getRetailerId() {
        return this.retailerId;
    }

    public String getOrderId() {
        return this.orderId;
    }

    public List<OrderFields> getCartProducts() {
        return this.cartProducts;
    }

    public void setOrderId(String value) {

        orderId = value;
    }

    public void addToCart(OrderFields product) {

        cartProducts.add(product);
    }


    public void setRetailerId(String value) {
        retailerId = value;
    }

    private Orders createNewOrder() {

        Orders order = new Orders();
        OrderRecord newOrderRecord = new OrderRecord();
        newOrderRecord.orderFields.status="Temperory";
        newOrderRecord.orderFields.retailer.add(getRetailerId());
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        newOrderRecord.orderFields.date = date.toString();
        order.orderRecords.add(newOrderRecord);
        return order;

    }

    private OrderItems updateOrderItems(String productRecordId, int quantity) {

        OrderItems orderItems = new OrderItems();
        OrderItemRecords orderItemRecords = new OrderItemRecords();
        orderItemRecords.fields.order.add(getOrderId());
        orderItemRecords.fields.product.add(productRecordId);
        orderItemRecords.fields.quantity = quantity;
        orderItems.records.add(orderItemRecords);
        return orderItems;

    }

    private void sendCreateOrderRequest(String ProductId,int quantity) {

        Gson gson = new Gson();
        String retailerJson = gson.toJson(createNewOrder());
        StringEntity createOrderEntity = new StringEntity(retailerJson, "UTF-8");
        createOrderEntity.setContentType("application/json");

        NetworkManager.post(context, ORDERS_URL_BASE+APIKEY, createOrderEntity, new NetworkManager.NetworkInterface() {
            @Override
            public void onResponse(ApiResponseWrapper baseResponse) {

                if (baseResponse != null && baseResponse.isSuccess()) {
                    Gson gson = new Gson();
                    OrderRecord ordersNew = gson.fromJson(baseResponse.getJsonObjectResponse().toString(), OrderRecord.class);
                    setOrderId(ordersNew.id);
                    //set new order ID for order items


                }
            }
        });


    }


    private void addProductToCart(String productRecordId, int quantity) {

        Gson gson = new Gson();
        String orderItemJson = gson.toJson(updateOrderItems(productRecordId,quantity));
        StringEntity orderItemEntity = new StringEntity(orderItemJson, "UTF-8");
        orderItemEntity.setContentType("application/json");


        NetworkManager.post(context, ORDER_ITEMS_URL_BASE, orderItemEntity, new NetworkManager.NetworkInterface() {
            @Override
            public void onResponse(ApiResponseWrapper baseResponse) {

                if (baseResponse != null && baseResponse.isSuccess()) {
                    //update Order success


                }
            }
        });

    }

    public void confirmOrder(){

        Gson gson = new Gson();
        OrderRecord newOrderRecord = new OrderRecord();
        newOrderRecord.orderFields.status = "Unconfirmed";
        Orders order = new Orders();
        order.orderRecords.add(newOrderRecord);
        String retailerJson = gson.toJson(order);
        StringEntity updateOrderEntity = new StringEntity(retailerJson, "UTF-8");
        updateOrderEntity.setContentType("application/json");

        String ORDER_RECORD_URL = ORDERS_URL_BASE+"/"+getOrderId()+APIKEY;

        NetworkManager.post(context, ORDER_ITEMS_URL_BASE, updateOrderEntity, new NetworkManager.NetworkInterface() {
            @Override
            public void onResponse(ApiResponseWrapper baseResponse) {

                if (baseResponse != null && baseResponse.isSuccess()) {
                    //update Order success
                    setOrderId("0");

                }
            }
        });




    }

    public void addProductToOrder(String productRecordId,int quantity){

        if(getOrderId()!="0"){
            //order created update order items
            addProductToCart(productRecordId,quantity);

        }else{
            //orderId zero, create order
            sendCreateOrderRequest(productRecordId,quantity);
        }

    }

}
