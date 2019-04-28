package com.sg.cocomo_simulator.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.sg.cocomo_simulator.R;
import com.sg.cocomo_simulator.adapter.CostDriverAdapter;
import com.sg.cocomo_simulator.model.CostDriver;
import com.sg.cocomo_simulator.view.MainActivity;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ResultFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ResultFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private FragmentManager fragmentManager;
    private String[] allfive=new String[5];
    private String[] lastfour=new String[4];
    private String[] firstFour=new String[4];
    private String[] lastThree=new String[3];
    int verylow,low,nominal,high,veryhigh;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public ResultFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ResultFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ResultFragment newInstance(String param1, String param2) {
        ResultFragment fragment = new ResultFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_result, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        double sloc,effort,kloc,tdev,totalcost;
        Button costdriverbutton=view.findViewById(R.id.costdriverbutton);
        costdriverbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                costdriver();
            }
        });
        setString();
        TextView textViewFP=view.findViewById(R.id.tvfpr);
        Spinner spinner=view.findViewById(R.id.spinner3);
        spinner.setAdapter(new ArrayAdapter<String>(getContext(),R.layout.spinneritem,MainActivity.classofproduct));
        spinner.setSelection(MainActivity.selectedclass);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position!=MainActivity.selectedclass) {
                    MainActivity.selectedclass = position;
                    fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                    fragmentTransaction.replace(R.id.MainFrame, new ResultFragment()).commit();
                }}

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        if(!MainActivity.isSLOC) {
            textViewFP.setText("Function Point: " + MainActivity.fpvalue + "\n\nLanguage Factor: " + MainActivity.langfactor+"\n\n");
            sloc = MainActivity.fpvalue * MainActivity.langfactor;
        }
        else {
            sloc=MainActivity.slocvalue;
        }
            if (MainActivity.selectedclass == 0) {
                kloc = sloc / 1000;
                effort = (2.4) * (Math.pow(kloc, 1.05));
                for(int i=0;i<15;i++)
                {
                    effort*=MainActivity.costdrivers[i];
                }
                tdev = (2.5) * (Math.pow(effort, 0.38));
                totalcost=MainActivity.costperperson*(effort/tdev);


            } else if (MainActivity.selectedclass == 1) {
                kloc = sloc / 1000;
                effort = (3.0) * (Math.pow(kloc, 1.12));
                for(int i=0;i<15;i++)
                {
                    effort*=MainActivity.costdrivers[i];
                }
                tdev = (2.5) * (Math.pow(effort, 0.35));
                totalcost=MainActivity.costperperson*(effort/tdev);

            } else {
                kloc = sloc / 1000;
                effort = (3.6) * (Math.pow(kloc, 1.20));
                for(int i=0;i<15;i++)
                {
                    effort*=MainActivity.costdrivers[i];
                }
                tdev = (2.5) * (Math.pow(effort, 0.32));
                totalcost=MainActivity.costperperson*(effort/tdev);
            }
            textViewFP.append("SLOC: " + sloc + "\n\nKLOC: " + kloc + "\n\nEffort: " + effort + " Person-Months\n\nDevelopment Time: " + tdev+" Months"+"\n\nCost per Person: "+MainActivity.costperperson+" Units\n\nTotal Cost: "+totalcost+" Units");
    }
    public void costdriver()
    {
        ArrayList<CostDriver> costDrivers=new ArrayList<>(15);
        int selected[]=new int[15];
        costDrivers.add(new CostDriver("Required Software Reliability",MainActivity.costdrivers[0],allfive,new double[]{0.75,0.88,1.00,1.15,1.40}));
        costDrivers.add(new CostDriver("Size of Application Database",MainActivity.costdrivers[1],lastfour,new double[]{0.94,1.00,1.08,1.16}));
        costDrivers.add(new CostDriver("Complexity of the Product",MainActivity.costdrivers[2],allfive,new double[]{0.70,0.85,1.00,1.15,1.30}));
        costDrivers.add(new CostDriver("Running Performance Constraints",MainActivity.costdrivers[3],lastThree,new double[]{1.00,1.11,1.30}));
        costDrivers.add(new CostDriver("Memory Constraints",MainActivity.costdrivers[4],lastThree,new double[]{1.00,1.06,1.21}));
        costDrivers.add(new CostDriver("Volatility of the Virtual Machine Environment",MainActivity.costdrivers[5],lastfour,new double[]{0.87,1.00,1.15,1.30}));
        costDrivers.add(new CostDriver("Required Turnabout Time",MainActivity.costdrivers[6],lastfour,new double[]{0.94,1.00,1.07,1.15}));
        costDrivers.add(new CostDriver("Analyst Capability",MainActivity.costdrivers[7],allfive,new double[]{1.46,1.19,1.00,0.86,0.71}));
        costDrivers.add(new CostDriver("Applications Experience",MainActivity.costdrivers[8],allfive,new double[]{1.29,1.13,1.00,0.91,0.82}));
        costDrivers.add(new CostDriver("Software Engineer Capability",MainActivity.costdrivers[9],allfive,new double[]{1.42,1.17,1.00,0.86,0.70}));
        costDrivers.add(new CostDriver("Virtual Machine Experience",MainActivity.costdrivers[10],firstFour,new double[]{1.21,1.10,1.00,0.90}));
        costDrivers.add(new CostDriver("Programming Language Experience",MainActivity.costdrivers[11],firstFour,new double[]{1.14,1.07,1.00,0.95}));
        costDrivers.add(new CostDriver("Application of Software Engineering Methods",MainActivity.costdrivers[12],allfive,new double[]{1.24,1.10,1.00,0.91,0.82}));
        costDrivers.add(new CostDriver("Use of Software Tools",MainActivity.costdrivers[13],allfive,new double[]{1.24,1.10,1.00,0.91,0.83}));
        costDrivers.add(new CostDriver("Required Development Scheduele",MainActivity.costdrivers[14],allfive,new double[]{1.23,1.08,1.00,1.04,1.10}));
        LayoutInflater li = LayoutInflater.from(getView().getContext());
        final View promptsView = li.inflate(R.layout.costdriver_dialogbox, null);
        RecyclerView recyclerView=promptsView.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        CostDriverAdapter costDriverAdapter=new CostDriverAdapter(costDrivers,getContext());
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(costDriverAdapter);
        new MaterialDialog.Builder(getContext()).customView(promptsView,true).positiveText("OK").negativeText("Cancel").canceledOnTouchOutside(true)
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                }).onPositive(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.MainFrame, new ResultFragment()).commit();
            }
        }).show();

    }
    public void setString()
    {
        allfive[0]="Very Low";
        allfive[1]="Low";
        allfive[2]="Nominal";
        allfive[3]="High";
        allfive[4]="Very High";
        firstFour[0]="Very Low";
        firstFour[1]="Low";
        firstFour[2]="Nominal";
        firstFour[3]="High";
        lastfour[0]="Low";
        lastfour[1]="Nominal";
        lastfour[2]="High";
        lastfour[3]="Very High";
        lastThree[0]="Nominal";
        lastThree[1]="High";
        lastThree[2]="Very High";
    }
}
