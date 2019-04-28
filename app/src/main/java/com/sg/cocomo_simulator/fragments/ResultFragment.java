package com.sg.cocomo_simulator.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.sg.cocomo_simulator.R;
import com.sg.cocomo_simulator.view.MainActivity;

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
        double sloc,effort,kloc,tdev;
        TextView textViewFP=view.findViewById(R.id.tvfpr);
        Spinner spinner=view.findViewById(R.id.spinner3);
        spinner.setAdapter(new ArrayAdapter<String>(getContext(),R.layout.spinneritem,MainActivity.classofproduct));
        spinner.setSelection(MainActivity.selectedclass);
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
                tdev = (2.5) * (Math.pow(effort, 0.38));

            } else if (MainActivity.selectedclass == 1) {
                sloc = MainActivity.fpvalue * MainActivity.langfactor;
                kloc = sloc / 1000;
                effort = (3.0) * (Math.pow(kloc, 1.12));
                tdev = (2.5) * (Math.pow(effort, 0.35));
            } else {
                sloc = MainActivity.fpvalue * MainActivity.langfactor;
                kloc = sloc / 1000;
                effort = (3.6) * (Math.pow(kloc, 1.20));
                tdev = (2.5) * (Math.pow(effort, 0.32));
            }
            textViewFP.append("SLOC: " + sloc + "\n\nKLOC: " + kloc + "\n\nEffort: " + effort + "\n\nDevelopment Time: " + tdev);
        }
}
