package base.com.medicalapp.fragments;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

/**
 * Created by Abhishek on 1/7/2017.
 */

public class BaseFragment  extends Fragment{
    protected FragmentActivity activityContext;



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activityContext = (FragmentActivity) context;
    }

}
