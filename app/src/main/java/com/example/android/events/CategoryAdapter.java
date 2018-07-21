package com.example.android.events;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.android.events.R;

public class CategoryAdapter extends FragmentPagerAdapter {
    private Context cont;
    private final int COUNT = 3;

    public CategoryAdapter(Context context, FragmentManager fm) {
        super(fm);
        cont = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new AperitiviFragment();
        } else if (position == 1) {
            return new FesteFragment();
        } else {
            return new SagreFragment();
        }
    }

    @Override
    public int getCount() {
        return COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return cont.getString(R.string.categoria_aperitivi);
        } else if (position == 1) {
            return cont.getString(R.string.categoria_feste);
        } else {
            return cont.getString(R.string.categoria_sagre);
        }
    }
}
