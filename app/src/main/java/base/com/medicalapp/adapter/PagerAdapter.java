package base.com.medicalapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import base.com.medicalapp.fragments.OrdersFragment;
import base.com.medicalapp.fragments.ProductsFragment;
import base.com.medicalapp.fragments.SchemesFragment;
import base.com.medicalapp.fragments.CartFragment;


public class PagerAdapter extends FragmentPagerAdapter {
    private static int NUM_ITEMS = 4;
    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new ProductsFragment();
            case 1:
                return  new SchemesFragment();
            case 2:
                return new OrdersFragment();
            case 3:
                return new CartFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }
}
