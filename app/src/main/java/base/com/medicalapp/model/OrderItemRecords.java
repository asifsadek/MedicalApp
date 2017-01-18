package base.com.medicalapp.model;

/**
 * Created by ctsuser1 on 1/17/17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class OrderItemRecords {

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("fields")
    @Expose
    public OrderItemsFields fields;
    @SerializedName("createdTime")
    @Expose
    public String createdTime;

    public OrderItemRecords(){

        fields = new OrderItemsFields();
        fields.order = new ArrayList<String>();
        fields.product = new ArrayList<String>();
    }

}