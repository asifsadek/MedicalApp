package base.com.medicalapp.fragments;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;


public class BaseFragment  extends Fragment implements  View.OnClickListener{
    protected FragmentActivity activityContext;



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activityContext = (FragmentActivity) context;
    }

    @Override
    public void onClick(View view) {

    }
}
