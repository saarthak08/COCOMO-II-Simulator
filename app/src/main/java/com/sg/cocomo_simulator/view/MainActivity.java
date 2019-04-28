package com.sg.cocomo_simulator.view;

import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.MenuItem;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.sg.cocomo_simulator.R;
import com.sg.cocomo_simulator.databinding.ActivityMainBinding;
import com.sg.cocomo_simulator.databinding.AppBarMainBinding;
import com.sg.cocomo_simulator.databinding.ContentMainBinding;
import com.sg.cocomo_simulator.databinding.NavHeaderMainBinding;
import com.sg.cocomo_simulator.di.App;

import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    boolean doubleBackToExitPressedOnce = false;
    FragmentManager fragmentManager;
    Button sloc;
    Button fp;
    long slocvalue;
    double fpvalue;
    int selectedclass;
    @Inject
    Application application;
    ArrayList<String> classofproduct=new ArrayList<>();
    ArrayList<String> lang=new ArrayList<>();
    TextView langfactorTV;
    int langfactor;
    EditText editTextfp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        App.getApp().getComponent().inject(MainActivity.this);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.calculate);
        sloc=findViewById(R.id.sloc);
        fp=findViewById(R.id.fp);
        classofproduct.add("Organic");
        classofproduct.add("Semidetached");
        classofproduct.add("Embedded");
        sloc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(MainActivity.this).title("Enter the value of SLOC:").autoDismiss(false).inputRange(1,18).inputType(InputType.TYPE_CLASS_NUMBER).input("Example: 1,10,100...", null, false, new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                        slocvalue=Long.parseLong((input.toString()));
                    }
                }).cancelable(false).canceledOnTouchOutside(false).positiveText("OK").onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                       chooseclassofproduct();

                    }
                }).negativeText("Cancel").onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.cancel();
                    }
                }).show();
            }
        });
        LayoutInflater li = LayoutInflater.from(MainActivity.this);
        final View promptsView = li.inflate(R.layout.fp_material_dialog, null);
        final Spinner spinner=promptsView.findViewById(R.id.spinner);
        langfactorTV=promptsView.findViewById(R.id.langfactv);
        editTextfp=promptsView.findViewById(R.id.editTextFP);
        addlang();
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,R.layout.spinneritem
                ,lang);
        spinner.setAdapter(arrayAdapter);
        fp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                new MaterialDialog.Builder(MainActivity.this).autoDismiss(false).title("Enter the value of FP:").customView(promptsView,true)
                        .positiveText("OK").negativeText("Cancel").cancelable(false)
                        .canceledOnTouchOutside(false).onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.cancel();
                    }
                }).onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        if(editTextfp.getText().toString().trim().length()!=0&&editTextfp.getText().toString().trim().charAt(0)!='.'&&editTextfp.getText().toString().trim().charAt(editTextfp.getText().toString().trim().length()-1)!='.') {
                            fpvalue=Double.parseDouble(editTextfp.getText().toString().trim());
                            chooseclassofproduct();
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this,"Error! Invalid Input",Toast.LENGTH_SHORT).show();
                        }
                    }
                }).show();
            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0)
                {
                    langfactorTV.setText("Language Factor: 53");
                    langfactor=53;

                }
                else if(position==1)
                {
                    langfactorTV.setText("Language Factor: 97");
                    langfactor=97;
                }
                else if(position==2)
                {
                    langfactorTV.setText("Language Factor: 50");
                    langfactor=50;
                }
                else if(position==3)
                {
                    langfactorTV.setText("Language Factor: 54");
                    langfactor=54;
                }
                else if(position==4)
                {
                    langfactorTV.setText("Language Factor: 47");
                    langfactor=47;
                }
                else if(position==5)
                {
                    langfactorTV.setText("Language Factor: 34");
                    langfactor=34;
                }
                else if(position==6)
                {
                    langfactorTV.setText("Language Factor: 21");
                    langfactor=21;
                }
                else if(position==7)
                {
                    langfactorTV.setText("Language Factor: 57");
                    langfactor=57;
                }
                else if(position==8)
                {
                    langfactorTV.setText("Language Factor: 24");
                    langfactor=24;
                }
                else if(position==9)
                {
                    langfactorTV.setText("Language Factor: 37");
                    langfactor=37;
                }
                else if(position==10)
                {
                    langfactorTV.setText("Language Factor: 51");
                    langfactor=51;
                }   else if(position==11)
                {
                    langfactorTV.setText("Language Factor: 42");
                    langfactor=42;
                }   else if(position==12)
                {
                    langfactorTV.setText("Language Factor: 119");
                    langfactor=119;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer =findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }
            doubleBackToExitPressedOnce = true;
            Toast.makeText(MainActivity.this,"Press \'BACK\' again to exit",Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            },2000);
        }
    }

    public void chooseclassofproduct()
    {
        new MaterialDialog.Builder(MainActivity.this).title("Choose the class of product:").items(classofproduct).itemsCallbackSingleChoice(0, new MaterialDialog.ListCallbackSingleChoice() {
            @Override
            public boolean onSelection(MaterialDialog dialoge, View itemView, int which, CharSequence text) {
                selectedclass=which;
                return true;
            }
        }).positiveText("OK").negativeText("Cancel").canceledOnTouchOutside(false).cancelable(false)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialoge, @NonNull DialogAction which) {

                    }
                }).onNegative(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialoge, @NonNull DialogAction which) {
                dialoge.dismiss();
            }
        }).show();
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.calculate) {
            // Handle the camera action
        } else if (id == R.id.history) {

        } else if (id == R.id.important) {

        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void addlang()
    {
        lang.add("Java");
        lang.add("C");
        lang.add("C++");
        lang.add("C#");
        lang.add("JavaScript");
        lang.add("HTML");
        lang.add("SQL");
        lang.add(".NET");
        lang.add("Perl");
        lang.add("Oracle");
        lang.add("ASP");
        lang.add("Visual Basic");
        lang.add("Assembler");
    }

}
