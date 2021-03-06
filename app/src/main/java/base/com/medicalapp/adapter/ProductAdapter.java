package base.com.medicalapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import base.com.medicalapp.R;
import base.com.medicalapp.activity.ProductDetailActivity;
import base.com.medicalapp.model.ProductRecord;



public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {


    private List<ProductRecord>  productRecordArray;
    Context context;


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_cell_product, parent, false);

        context= parent.getContext();

        return new ViewHolder(itemView);
    }

    public void updateData(List<ProductRecord> recordArray) {

        // update the adapter's dataset
        productRecordArray = recordArray;
        notifyDataSetChanged();
    }



    public ProductRecord getItem(int position) {
        return productRecordArray.get(position);
    }

    @Override public long getItemId(int position) {

        // your particular dataset uses String IDs
        // but you have to put something in this method
        return position;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        final ProductRecord jsonObject = getItem(position);
        if (jsonObject.fields.name!=null) {

            String imageID = jsonObject.fields.name;
            holder.medicineName.setText(imageID);
            holder.medicineMrp.setText(Float.toString(jsonObject.fields.mRP));
            holder.schemeFlag.setVisibility(View.INVISIBLE);
            //if(jsonObject.fields.productSchemes!=null) {
             //   holder.schemeFlag.setImageResource(R.drawable.scheme);
             //   holder.schemeFlag.setVisibility(View.VISIBLE);
           // }
            //holder.medicineSpec.setText(jsonObject.fields.);
        }

        holder.medicineSpec.setText(jsonObject.fields.pack);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               gotoProductDetailPage(jsonObject.id);

            }
        });

    }


    @Override
    public int getItemCount() {

        if (productRecordArray != null) {
            return productRecordArray.size();
        }
        return 0;
    }

    private void gotoProductDetailPage(String id){

        Intent detailIntent = new Intent(context, ProductDetailActivity.class);
        detailIntent.putExtra("record",id);
        context.startActivity(detailIntent);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView medicineName;
        public TextView medicineSpec;
        public TextView medicineMrp;
        public ImageView schemeFlag;

        public ViewHolder(View v) {
            super(v);
            medicineName = (TextView) v.findViewById(R.id.schemeId);
            medicineSpec = (TextView) v.findViewById(R.id.medicineSpec);
            medicineMrp  = (TextView) v.findViewById(R.id.medicineMrp);
            schemeFlag   = (ImageView) v.findViewById(R.id.schemeFlag);
        }
    }
}

