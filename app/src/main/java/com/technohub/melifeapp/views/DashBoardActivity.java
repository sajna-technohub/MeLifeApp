package com.technohub.melifeapp.views;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.technohub.melifeapp.R;
import com.technohub.melifeapp.classes.ConnectionReceiver;
import com.technohub.melifeapp.models.LoginResponse;
import com.technohub.melifeapp.views.ui.home.HomeFragment;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class DashBoardActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, ConnectionReceiver.ReceiverListener  {
    private AppBarConfiguration mAppBarConfiguration;
    int flag=0;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().hide();
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        BottomNavigationView navView = findViewById(R.id.bottom_nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send).setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        //botton navigatiomn
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder
                (R.id.navigation_home,
                R.id.navigation_user,
                R.id.navigation_logout)
                .build();
        NavController navController1 = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController1, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController1);
        loadFragment(new HomeFragment());
        navView.setOnNavigationItemSelectedListener(this);
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()) {

            case R.id.navigation_home:
                fragment = new HomeFragment();
                break;
//            case R.id.navigation_loc:
//                    fragment = new ReportFragment();
//                    fragment = new ExamFragment();
//                break;
//            case R.id.navigation_like:
//                fragment = new StreamFragment();
//                break;
            case R.id.navigation_user:
                fragment = new ProfileFragment();
                break;
            case R.id.navigation_logout:
                if(new LoginResponse().removeSharedPreferences(getApplicationContext()))
                {
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    finish();
                }
                break;
        }

        return loadFragment(fragment);
    }
    private void checkConnection() {

        // initialize intent filter
        IntentFilter intentFilter = new IntentFilter();

        // add action
        intentFilter.addAction("android.new.conn.CONNECTIVITY_CHANGE");

        // register receiver
      registerReceiver(new ConnectionReceiver(), intentFilter);

        // Initialize listener
        ConnectionReceiver.Listener = this;

        // Initialize connectivity manager
        ConnectivityManager manager = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        // Initialize network info
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        // get connection status
        boolean isConnected = networkInfo != null && networkInfo.isConnectedOrConnecting();

        // display snack bar
        showSnackBar(isConnected);
    }

    private void showSnackBar(boolean isConnected) {

        // initialize color and message
        String message;
        int color;

        // check condition
        if (isConnected) {

            // when internet is connected
            // set message
//            message = "Connected to Internet";
//            Log.e("networkconn",message);

            // set text color
            color = Color.WHITE;

        } else {

            // when internet
            // is disconnected
            // set message
            message = "Not Connected to Internet";
            Log.e("networkconn",message);
            // set text color
            color = Color.RED;
            Snackbar snackbar = Snackbar.make(findViewById(R.id.cordinatorlayout), message, Snackbar.LENGTH_LONG);

            // initialize view
            View view = snackbar.getView();

            // Assign variable
            TextView textView = view.findViewById(R.id.snackbar_text);

            // set text color
            textView.setTextColor(color);

            // show snack bar
            snackbar.show();
        }
        // initialize snack bar

    }
    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null)
        {
                     getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.homelayout, fragment)
                    .commit();
                     return true;
        }
        return false;
    }
    @Override
    public void onNetworkChange(boolean isConnected) {
        // display snack bar
        showSnackBar(isConnected);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dash_board, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);

    }

    @Override
    protected void onPause() {
        super.onPause();
        checkConnection();
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkConnection();
    }
}
