package com.example.exceloid_android.exceloid_mobileapp;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by Exceloid-Android on 22-09-2016.
 */
public class Navigation_Activity extends AppCompatActivity {

    ActionBar actionBar;
    private DrawerLayout mDrawer;
    private NavigationView nvDrawer;
    private ActionBarDrawerToggle drawerToggle;
    Fragment fragment = null;
    String name;
    UserFunctions userFunctions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_activity);
        setupActionBar();
        userFunctions = new UserFunctions();

        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        nvDrawer = (NavigationView) findViewById(R.id.nvView);
        setupDrawerContent(nvDrawer);

        drawerToggle = setupDrawerToggle();
        mDrawer.setDrawerListener(drawerToggle);

        Bundle b = getIntent().getExtras();
        name = b.getString("name");
        selectNavigation_item();
    }

    private void setupActionBar() {
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        return  new ActionBarDrawerToggle(this, mDrawer, R.string.navigation_drawer_open,  R.string.navigation_drawer_close);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        switch (item.getItemId()) {
            case android.R.id.home:
                if(mDrawer.isDrawerOpen(nvDrawer)){

                    mDrawer.closeDrawer(nvDrawer);

                }else {
                    mDrawer.openDrawer(GravityCompat.START);
                }

                return true;

            case R.id.ic_logout:

                AlertDialog.Builder adb1 = new AlertDialog.Builder(Navigation_Activity.this);
                adb1.setTitle("SignOut?");
                adb1.setMessage("Are you sure you want to SignOut?");
                adb1.setNegativeButton("Cancel", null);
                adb1.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        new MyAsync_Signout().execute();
                    }
                });

                adb1.show();

             //   Toast.makeText(Navigation_Activity.this, "Signout", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the fragment to show based on nav item clicked\
        Bundle bundle = new Bundle();

        switch(menuItem.getItemId()) {

            case R.id.myprofilee:
                fragment=new Tab_Activity();
                getSupportFragmentManager().beginTransaction().replace(R.id.flContent, fragment).commit();
                menuItem.setChecked(true);
                setTitle(menuItem.getTitle());
                break;
            case R.id.mydictionary:
                fragment=new Dictionary_Information();
                getSupportFragmentManager().beginTransaction().replace(R.id.flContent, fragment).commit();
                menuItem.setChecked(true);
                setTitle(menuItem.getTitle());
                break;
            case R.id.statuss:
                fragment=new LeavesRequests_Activity();
                getSupportFragmentManager().beginTransaction().replace(R.id.flContent, fragment).commit();
                menuItem.setChecked(true);
                setTitle(menuItem.getTitle());
                break;
            case R.id.requestss:
                fragment=new TimeSheet_Activity();
                getSupportFragmentManager().beginTransaction().replace(R.id.flContent, fragment).commit();
                menuItem.setChecked(true);
                setTitle(menuItem.getTitle());
                break;
            case R.id.helpp:
                fragment=new Expense_Reports();
                getSupportFragmentManager().beginTransaction().replace(R.id.flContent, fragment).commit();
                menuItem.setChecked(true);
                setTitle(menuItem.getTitle());
                break;
            default:
                break;
        }

        mDrawer.closeDrawers();
    }

    @Override
    public void onBackPressed() {

        if(mDrawer.isDrawerOpen(nvDrawer)){

            mDrawer.closeDrawer(nvDrawer);

        }else {
            super.onBackPressed();
        }
    }

    public void selectNavigation_item(){

        if(name.equals("zero")){
            fragment=new Tab_Activity();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
            setTitle("My Profile");
            nvDrawer.getMenu().getItem(0).setChecked(true);
        }else if(name.equals("one")){
            fragment=new Dictionary_Information();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
            setTitle("Directory");
            nvDrawer.getMenu().getItem(1).setChecked(true);
        }else if(name.equals("two")){
            fragment=new LeavesRequests_Activity();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
            setTitle("Leaves and Requests");
            nvDrawer.getMenu().getItem(2).setChecked(true);
        }else if(name.equals("three")){
            fragment=new TimeSheet_Activity();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
            setTitle("Tasks");
            nvDrawer.getMenu().getItem(3).setChecked(true);
        }else if(name.equals("four")){
            fragment=new Expense_Reports();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
            setTitle("Expenses & Reports");
            nvDrawer.getMenu().getItem(4).setChecked(true);
        }    }

    class MyAsync_Signout extends AsyncTask<Void, Integer, Void> {

        boolean running;
        ProgressDialog progressDialog;

        @Override
        protected Void doInBackground(Void... params) {
            try {
                //Do something...
                Thread.sleep(2000);
                userFunctions.logoutUser(getApplicationContext());
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            running = true;
            progressDialog = ProgressDialog.show(Navigation_Activity.this, null, "Please Wait!");
            progressDialog.getWindow().setGravity(Gravity.CENTER_HORIZONTAL);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Intent login=new Intent(Navigation_Activity.this,Login_Activity.class);
            login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(login);
            finish();
            progressDialog.dismiss();
        }
    }

}
