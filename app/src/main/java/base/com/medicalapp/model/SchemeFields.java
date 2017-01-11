package base.com.medicalapp.model;

/**
 * Created by ctsuser1 on 1/11/17.
 */

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SchemeFields {

    @SerializedName("Scheme")
    @Expose
    public List<String> scheme = null;
    @SerializedName("Retailer Scheme Status")
    @Expose
    public Boolean retailerSchemeStatus;
    @SerializedName("Orders")
    @Expose
    public List<String> orders = null;
    @SerializedName("Retailer")
    @Expose
    public List<String> retailer = null;
    @SerializedName("Code")
    @Expose
    public List<String> code = null;
    @SerializedName("ID")
    @Expose
    public List<String> iD = null;
    @SerializedName("Discount \u20b9")
    @Expose
    public List<Integer> discount = null;
    @SerializedName("Type")
    @Expose
    public List<String> type = null;
    @SerializedName("Name")
    @Expose
    public List<String> name = null;
    @SerializedName("Scheme Status")
    @Expose
    public List<Boolean> schemeStatus = null;
    @SerializedName("Expiry Date")
    @Expose
    public List<String> expiryDate = null;
    @SerializedName("Status")
    @Expose
    public Integer status;

}
