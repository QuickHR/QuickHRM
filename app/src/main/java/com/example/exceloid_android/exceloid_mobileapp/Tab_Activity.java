package com.example.exceloid_android.exceloid_mobileapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Exceloid-Android on 20-09-2016.
 */
public class Tab_Activity extends Fragment {

    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    public static int int_items = 9 ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /**
         *Inflate tab_layout and setup Views.
         */
        View root =  inflater.inflate(R.layout.tab_activity,null);

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
                        result=new Profile_Information();
                        break;
                    case 1 :
                        result= new Personal_information();
                        break;
                    case 2 :
                        result= new Address_Information();
                        break;
                    case 3 :
                        result= new Job_Information();
                        break;
                    case 4 :
                        result= new Experiance_Information();
                        break;
                    case 5 :
                        result= new Qualification_Information();
                        break;
                    case 6 :
                        result= new Bank_Information();
                        break;
                    case 7 :
                        result= new Leave_Information();
                        break;
                    case 8 :
                        result= new Shift_Information();
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
                        return "Profile";
                    case 1 :
                        return "Personal";
                    case 2 :
                        return "Address";
                    case 3 :
                        return "Job";
                    case 4 :
                        return "Experience";
                    case 5 :
                        return "Qualification";
                    case 6 :
                        return "Bank";
                    case 7 :
                        return "Leave";
                    case 8 :
                        return "Shift";
                    default:
                        break;
                }
                return null;
            }
        }
}
