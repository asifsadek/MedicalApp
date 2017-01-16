package base.com.medicalapp.model;

import java.util.List;

/**
 * Created by ctsuser1 on 1/16/17.
 */

public class GlobalPreferences {
    private static GlobalPreferences mInstance = null;

    private String retailerId;
    private List<OrderFields> cartProducts;
    private String orderId;

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
}