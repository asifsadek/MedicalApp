package base.com.medicalapp.model;

/**
 * Created by ctsuser1 on 1/12/17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StockistFields {

    @SerializedName("Mobile")
    @Expose
    public String mobile;
    @SerializedName("Email")
    @Expose
    public String email;
    @SerializedName("Name")
    @Expose
    public String name;
    @SerializedName("Address")
    @Expose
    public String address;
    @SerializedName("Pincode")
    @Expose
    public Integer pincode;
    @SerializedName("TIN No")
    @Expose
    public String tINNo;
    @SerializedName("DL No")
    @Expose
    public String dLNo;
    @SerializedName("Retailers")
    @Expose
    public String retailers;
    @SerializedName("StockistProducts")
    @Expose
    public String stockistProducts;
    @SerializedName("RetailerProducts")
    @Expose
    public String retailerProducts;
    @SerializedName("Contact Name")
    @Expose
    public String contactName;

}
