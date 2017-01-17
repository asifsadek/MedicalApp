package base.com.medicalapp.model;

/**
 * Created by ctsuser1 on 1/17/17.
 */

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderItemsFields {

    @SerializedName("ID")
    @Expose
    public Integer iD;
    @SerializedName("Quantity")
    @Expose
    public Integer quantity;
    @SerializedName("Product")
    @Expose
    public List<String> product = null;
    @SerializedName("Scheme")
    @Expose
    public List<Object> scheme = null;
    @SerializedName("Product Name")
    @Expose
    public List<String> productName = null;
    @SerializedName("Product SKU")
    @Expose
    public List<Integer> productSKU = null;
    @SerializedName("PTR")
    @Expose
    public List<Float> pTR = null;
    @SerializedName("Total")
    @Expose
    public Float total;
    @SerializedName("Net")
    @Expose
    public Float net;
    @SerializedName("MRP")
    @Expose
    public List<Float> mRP = null;
    @SerializedName("MRP Total")
    @Expose
    public Integer mRPTotal;

}