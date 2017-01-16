package base.com.medicalapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import base.com.medicalapp.R;
import base.com.medicalapp.model.ProductRecord;



public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    List<ProductRecord> productRecordArray ;
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_cell_cart, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        ProductRecord jsonObject = getItem(position);
        if (jsonObject.fields.productSchemes!=null) {


        }
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
    public int getItemCount() {
        if (productRecordArray != null) {
            return productRecordArray.size();
        }
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView schemeName;
        public TextView schemeExpiry;
        public TextView schemeType;

        public ViewHolder(View v) {
            super(v);
            schemeName = (TextView) v.findViewById(R.id.schemeId);
            schemeExpiry = (TextView) v.findViewById(R.id.schemeExpiry);

        }
    }
}

