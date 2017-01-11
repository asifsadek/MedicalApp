package base.com.medicalapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import base.com.medicalapp.R;
import base.com.medicalapp.model.ProductRecord;
import base.com.medicalapp.model.SchemeRecord;


public class SchemeAdapter extends RecyclerView.Adapter<SchemeAdapter.ViewHolder> {

    List<SchemeRecord> schemeRecordArray ;
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_cell_scheme, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.medicineName.setText("Cefimak");
        holder.medicineExpery.setText("Till Stock last");

    }

    public void updateData(List<SchemeRecord> recordArray) {

        // update the adapter's dataset
        schemeRecordArray = recordArray;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (schemeRecordArray != null) {
            return schemeRecordArray.size();
        }
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView medicineName;
        public TextView medicineExpery;

        public ViewHolder(View v) {
            super(v);
            medicineName = (TextView) v.findViewById(R.id.orderId);
            medicineExpery = (TextView) v.findViewById(R.id.medicineExpery);

        }
    }
}
