package base.com.medicalapp.model;

import android.content.Context;
import android.util.Log;

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
    private String retailerRecordId;
    private String retailerID;
    private List<OrderFields> cartProducts;
    private String orderRecordId;
    private String orderID;
    private static Context context;
    private OrderItemRecords orderItemrecord;

    private GlobalPreferences() {
        retailerRecordId = "0";
        orderRecordId = "0";
        orderID="0";

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

    public void setRetailerID(String ID){
        this.retailerID = ID;
    }

    public String getRetailerID(){ return this.retailerID;}

    public String getOrderID(){ return  this.orderID; }

    public String getRetailerRecordId() {
        return this.retailerRecordId;
    }

    public String getOrderRecordId() {
        return this.orderRecordId;
    }

    public List<OrderFields> getCartProducts() {
        return this.cartProducts;
    }

    public void setOrderRecordId(String value) {

        orderRecordId = value;
    }

    public void setOrderID(String value){

        orderID = value;
    }

    public void addToCart(OrderFields product) {

        cartProducts.add(product);
    }


    public void setRetailerRecordId(String value) {
        retailerRecordId = value;
    }

    private OrderRecord createNewOrder() {

        OrderRecord newOrderRecord = new OrderRecord();
        newOrderRecord.orderFields.status="Temporary";
        newOrderRecord.orderFields.retailer.add(getRetailerRecordId());
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        newOrderRecord.orderFields.date = dateFormat.format(date);
        return newOrderRecord;

    }

    private OrderItemRecords updateOrderItems(String productRecordId, int quantity) {

        OrderItemRecords orderItemRecords = new OrderItemRecords();
        orderItemRecords.fields.order.add(getOrderRecordId());
        orderItemRecords.fields.product.add(productRecordId);
        orderItemRecords.fields.quantity = quantity;
        return orderItemRecords;

    }

    private OrderItemRecords updateSchemeToOrder(String schemeRecordId) {

        OrderItemRecords orderItemRecords = new OrderItemRecords();
        orderItemRecords.fields.order.add(getOrderRecordId());
        orderItemRecords.fields.scheme.add(schemeRecordId);
        return orderItemRecords;

    }

    private void sendCreateOrderRequest(final String ProductId, final int quantity) {

        Gson gson = new Gson();
        String retailerJson = gson.toJson(createNewOrder());
        StringEntity createOrderEntity = new StringEntity(retailerJson, "UTF-8");
        createOrderEntity.setContentType("application/json");
        Log.v("URL",ORDERS_URL_BASE+APIKEY);
        Log.v("JSON",retailerJson);
        NetworkManager.post(context, ORDERS_URL_BASE+APIKEY, createOrderEntity, new NetworkManager.NetworkInterface() {
            @Override
            public void onResponse(ApiResponseWrapper baseResponse) {

                if (baseResponse != null && baseResponse.isSuccess()) {
                    Gson gson = new Gson();
                    OrderRecord ordersNew = gson.fromJson(baseResponse.getJsonObjectResponse().toString(), OrderRecord.class);
                    Log.v("RESPONSE ORDER",baseResponse.getJsonObjectResponse().toString());
                    setOrderRecordId(ordersNew.id);
                    setOrderID(ordersNew.orderFields.iD.toString());
                    addProductToCart(ProductId,quantity);
                    //set new order ID for order items
                }else{
                    Log.v("JSON","CREATE ORDER FAILED");
                }
            }
        });


    }


    private void addProductToCart(String productRecordId, int quantity) {

        Gson gson = new Gson();
        String orderItemJson = gson.toJson(updateOrderItems(productRecordId,quantity));
        StringEntity orderItemEntity = new StringEntity(orderItemJson, "UTF-8");
        orderItemEntity.setContentType("application/json");

        Log.v("URL", ORDER_ITEMS_URL_BASE);
        Log.v("JSON",orderItemJson);
        NetworkManager.post(context, ORDER_ITEMS_URL_BASE, orderItemEntity, new NetworkManager.NetworkInterface() {
            @Override
            public void onResponse(ApiResponseWrapper baseResponse) {

                if (baseResponse != null && baseResponse.isSuccess()) {
                    //update Order success


                }else{
                    Log.v("JSON","ADD PRODUCT FAILED");
                }
            }
        });

    }

    public void addSchemeToCart(String schemeRecordId){

        Gson gson = new Gson();
        String orderItemJson = gson.toJson(updateSchemeToOrder(schemeRecordId));
        StringEntity orderItemEntity = new StringEntity(orderItemJson, "UTF-8");
        orderItemEntity.setContentType("application/json");

        Log.v("URL", ORDER_ITEMS_URL_BASE);
        Log.v("JSON",orderItemJson);
        NetworkManager.post(context, ORDER_ITEMS_URL_BASE, orderItemEntity, new NetworkManager.NetworkInterface() {
            @Override
            public void onResponse(ApiResponseWrapper baseResponse) {

                if (baseResponse != null && baseResponse.isSuccess()) {
                    //update Order success


                }else{
                    Log.v("JSON","ADD PRODUCT FAILED");
                }
            }
        });

    }

    public void confirmOrder(){

        Gson gson = new Gson();
        OrderRecord newOrderRecords= new OrderRecord();
        newOrderRecords.orderFields.status = "Unconfirmed";
        String retailerJson = gson.toJson(newOrderRecords);
        StringEntity updateOrderEntity = new StringEntity(retailerJson, "UTF-8");
        updateOrderEntity.setContentType("application/json");
        Log.v("CONFIRM",retailerJson);

        String ORDER_RECORD_URL = ORDERS_URL_BASE+"/"+getOrderRecordId()+APIKEY;
        Log.v("CONFIRM",ORDER_RECORD_URL);

        NetworkManager.patch(context, ORDER_ITEMS_URL_BASE, updateOrderEntity, new NetworkManager.NetworkInterface() {
            @Override
            public void onResponse(ApiResponseWrapper baseResponse) {

                if (baseResponse != null && baseResponse.isSuccess()) {
                    //update Order success
                    setOrderRecordId("0");
                    setOrderID("0");

                }
            }
        });




    }

    public void addProductToOrder(String productRecordId,int quantity){

        if(getOrderRecordId()!="0"){
            //order created update order items
            addProductToCart(productRecordId,quantity);

        }else{
            //orderId zero, create order
            sendCreateOrderRequest(productRecordId,quantity);
        }

    }

    public void createRetailerSchemes(){



    }

}
