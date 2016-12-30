package com.example.exceloid_android.quickhrmobileapp;

import android.content.Intent;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{
       // implements NavigationView.OnNavigationItemSelectedListener {

    Fragment fragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        setupDrawerContent(navigationView);
        //navigationView.setNavigationItemSelectedListener(this);

        fragment = new DashBoardFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment).commit();
        setTitle("DashBoard");
        navigationView.getMenu().getItem(0).setChecked(true);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.signOut) {
            Toast.makeText(MainActivity.this, "Sign Out", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);

                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem item) {
        // Handle navigation view item clicks here.

        switch (item.getItemId()) {

            case R.id.dashboard:
                fragment = new DashBoardFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment).commit();
                item.setChecked(true);
                setTitle(item.getTitle());
                break;
            case R.id.leaves:
                fragment = new LeaveRequest();
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment).commit();
                item.setChecked(true);
                setTitle(item.getTitle());
                break;
            case R.id.claims:
                fragment = new ClaimsFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment).commit();
                item.setChecked(true);
                setTitle(item.getTitle());
                break;
            case R.id.reports:
                fragment = new ReportsFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment).commit();
                item.setChecked(true);
                setTitle(item.getTitle());
                break;
            case R.id.timesheets:
                fragment = new TimeSheet_Activity();
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment).commit();
                item.setChecked(true);
                setTitle(item.getTitle());
                break;
            case R.id.settings:
                Intent in = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(in);
                //  Toast.makeText(getApplicationContext(), menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawers();
    }
        /*int id = item.getItemId();

        if (id == R.id.dashboard) {
            // Handle the camera action
            fragment=new DashBoardFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment).commit();
            item.setChecked(true);
            setTitle(item.getTitle());
        } else if (id == R.id.leaves) {
            fragment=new LeaveRequest();
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment).commit();
            item.setChecked(true);
            setTitle(item.getTitle());
        } else if (id == R.id.claims) {
            fragment=new ClaimsFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment).commit();
            item.setChecked(true);
            setTitle(item.getTitle());
        } else if (id == R.id.timesheets) {
            fragment=new TimeSheetFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment).commit();
            item.setChecked(true);
            setTitle(item.getTitle());
        } else if (id == R.id.reports) {
            fragment=new ReportsFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment).commit();
            item.setChecked(true);
            setTitle(item.getTitle());
        } else if (id == R.id.settings) {
            Intent in=new Intent(MainActivity.this,SettingsActivity.class);
            startActivity(in);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }*/
}
