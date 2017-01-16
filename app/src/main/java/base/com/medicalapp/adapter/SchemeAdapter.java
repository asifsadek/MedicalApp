package base.com.medicalapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import base.com.medicalapp.R;
import base.com.medicalapp.model.Scheme;
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

        SchemeRecord jsonObject = getItem(position);
        if (jsonObject.fields.retailerProductSchemes!=null) {
                holder.schemeName.setText(jsonObject.fields.code);
                holder.schemeType.setText(jsonObject.fields.type);

        }
    }

    public void updateData(List<SchemeRecord> recordArray) {

        // update the adapter's dataset
        schemeRecordArray = recordArray;
        notifyDataSetChanged();
    }

    public SchemeRecord getItem(int position) {
        return schemeRecordArray.get(position);
    }

    @Override public long getItemId(int position) {

        // your particular dataset uses String IDs
        // but you have to put something in this method
        return position;
    }

    @Override
    public int getItemCount() {
        if (schemeRecordArray != null) {
            return schemeRecordArray.size();
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

