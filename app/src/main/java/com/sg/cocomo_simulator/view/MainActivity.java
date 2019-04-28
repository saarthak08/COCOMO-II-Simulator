package com.sg.cocomo_simulator.view;

import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import com.sg.cocomo_simulator.R;
import com.sg.cocomo_simulator.di.App;
import com.sg.cocomo_simulator.fragments.CalculateFragment;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.view.Menu;
import android.widget.Toast;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    boolean doubleBackToExitPressedOnce = false;
    public static int langfactor;
    public static long slocvalue;
    public static double fpvalue;
    public static int selectedclass;
    public static boolean isSLOC;
    public static double costperperson;
    public static double[] costdrivers=new double[15];
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    public static ArrayList<String> classofproduct=new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle(R.string.app_name);
        App.getApp().getComponent().inject(MainActivity.this);
        fragmentManager= getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        fragmentTransaction.add(R.id.MainFrame,new CalculateFragment()).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            fragmentManager=getSupportFragmentManager();
            fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
            fragmentTransaction.replace(R.id.MainFrame,new CalculateFragment()).commit();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }
            doubleBackToExitPressedOnce = true;
            Toast.makeText(MainActivity.this, "Press \'BACK\' again to exit", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        }
    }

