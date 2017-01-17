package base.com.medicalapp.model;

/**
 * Created by ctsuser1 on 1/11/17.
 */

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderFields {

    @SerializedName("ID")
    @Expose
    public Integer iD;
    @SerializedName("Scheme")
    @Expose
    public List<String> scheme = null;
    @SerializedName("Status")
    @Expose
    public String status;
    @SerializedName("Date")
    @Expose
    public String date;
    @SerializedName("Scheme Status")
    @Expose
    public List<Boolean> schemeStatus = null;
    @SerializedName("Total")
    @Expose
    public Integer total;
    @SerializedName("Discount \u20b9")
    @Expose
    public List<Integer> discount = null;
    @SerializedName("Retailer")
    @Expose
    public List<String> retailer = null;
    @SerializedName("Stockist")
    @Expose
    public List<String> stockist = null;
    @SerializedName("Net")
    @Expose
    public Integer net;
    @SerializedName("All Schemes")
    @Expose
    public String allSchemes;
    @SerializedName("Notes")
    @Expose
    public String notes;
    @SerializedName("MRP Total")
    @Expose
    public Integer mRPTotal;

}
