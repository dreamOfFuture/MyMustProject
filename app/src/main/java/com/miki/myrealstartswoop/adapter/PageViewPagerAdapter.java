package com.miki.myrealstartswoop.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class PageViewPagerAdapter extends FragmentPagerAdapter {
    List<Fragment> list;
    public PageViewPagerAdapter(FragmentManager fm,List<Fragment> fragments) {
        super(fm);
        list =fragments;
    }

    @Override
    public Fragment getItem(int i) {
        return list.get(i);
    }

    @Override
    public int getCount() {
        return list.size();
    }

}
