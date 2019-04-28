package com.sg.cocomo_simulator.view;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.sg.cocomo_simulator.R;
import com.sg.cocomo_simulator.databinding.ActivityLauncherBinding;
import com.sg.cocomo_simulator.di.App;
import com.sg.cocomo_simulator.fragments.CalculateFragment;

import java.util.ArrayList;

public class LauncherActivity extends AppCompatActivity {
    private ActivityLauncherBinding activityLauncherBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        activityLauncherBinding = DataBindingUtil.setContentView(LauncherActivity.this, R.layout.activity_launcher);
        Toast.makeText(LauncherActivity.this, "Created By: Saarthak Gupta", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent i = new Intent(LauncherActivity.this, MainActivity.class);
                startActivity(i);
            }
        }, 2000);
    }


}