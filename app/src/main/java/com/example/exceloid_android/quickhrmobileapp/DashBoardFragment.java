package com.example.exceloid_android.quickhrmobileapp;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DashBoardFragment extends Fragment {

    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    public static int int_items = 6 ;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /**
         *Inflate tab_layout and setup Views.
         */
        View root =  inflater.inflate(R.layout.fragment_dash_board,null);

        tabLayout = (TabLayout)root.findViewById(R.id.tabs);
        viewPager = (ViewPager)root.findViewById(R.id.viewpager);

        viewPager.setAdapter(new MyAdapter(getChildFragmentManager()));

        viewPager.setCurrentItem(0);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });

        return root;

    }

    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        /**
         * Return fragment with respect to Position .
         */

        @Override
        public Fragment getItem(int position)
        {
            final Fragment result;
            switch (position){
                case 0:
                    result=new TasksFragment();
                    break;
                case 1 :
                    result= new TasksFragment();
                    break;
                case 2 :
                    result= new TasksFragment();
                    break;
                case 3 :
                    result= new TasksFragment();
                    break;
                case 4 :
                    result= new TasksFragment();
                    break;
                case 5 :
                    result= new TasksFragment();
                    break;
                default:
                    result=null;
                    break;
            }
            return result;
        }

        @Override
        public int getCount() {

            return int_items;

        }

        /**
         * This method returns the title of the tab according to the position.
         */

        @Override
        public CharSequence getPageTitle(int position) {

            switch (position){
                case 0 :
                    return "Tasks";
                case 1 :
                    return "Leave Statistics";
                case 2 :
                    return "Claim Statistics";
                case 3 :
                    return "Calendar";
                case 4 :
                    return "Expiring Documents";
                case 5 :
                    return "Employees Birthday";
                default:
                    break;
            }
            return null;
        }
    }
}
