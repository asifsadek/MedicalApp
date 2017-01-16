package base.com.medicalapp.model;

/**
 * Created by ctsuser1 on 1/16/17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RetailerFields {

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
    @SerializedName("RetailerProducts")
    @Expose
    public String retailerProducts;
    @SerializedName("Retailer Order Schemes")
    @Expose
    public String retailerOrderSchemes;
    @SerializedName("Contact Name")
    @Expose
    public String contactName;

}
