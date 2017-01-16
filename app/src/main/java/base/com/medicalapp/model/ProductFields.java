package base.com.medicalapp.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductFields {

    @SerializedName("Image")
    @Expose
    public List<Image> image = null;
    @SerializedName("SKU")
    @Expose
    public Integer sKU;
    @SerializedName("Composition")
    @Expose
    public String composition;
    @SerializedName("Name")
    @Expose
    public String name;
    @SerializedName("PTR (per Unit Pack)")
    @Expose
    public Float pTRPerUnitPack;
    @SerializedName("Type")
    @Expose
    public List<String> type = null;
    @SerializedName("MRP")
    @Expose
    public Float mRP;
    @SerializedName("Pack Description")
    @Expose
    public String packDescription;
    @SerializedName("Form")
    @Expose
    public String form;
    @SerializedName("Pack")
    @Expose
    public String pack;



}
