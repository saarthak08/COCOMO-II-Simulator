package com.sg.cocomo_simulator.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.google.android.material.snackbar.Snackbar;
import com.sg.cocomo_simulator.R;
import com.sg.cocomo_simulator.view.MainActivity;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CalculateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CalculateFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    FragmentManager fragmentManager;
    Button sloc;
    Button fp;
    ArrayList<String> lang=new ArrayList<>();
    ArrayList<String> classofproduct=new ArrayList<>();
    TextView langfactorTV;
    EditText editTextfp;
    MaterialDialog b;


    public CalculateFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CalculateFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CalculateFragment newInstance(String param1, String param2) {
        CalculateFragment fragment = new CalculateFragment();
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
        return inflater.inflate(R.layout.fragment_calculate, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sloc = view.findViewById(R.id.sloc);
        fp = view.findViewById(R.id.fp);
        setcostdrivers();
        MainActivity.costperperson=-1;
        classofproduct.add("Organic");
        classofproduct.add("Semidetached");
        classofproduct.add("Embedded");
        MainActivity.classofproduct=classofproduct;
        sloc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b = new MaterialDialog.Builder(view.getContext()).title("Enter the value of SLOC:").inputRange(1, 18).inputType(InputType.TYPE_CLASS_NUMBER).input("Example: 1,10,100...", null, false, new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                        MainActivity.slocvalue = Long.parseLong((input.toString().trim()));
                    }
                }).cancelable(false).canceledOnTouchOutside(false).positiveText("OK").onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        MainActivity.isSLOC = true;
                        chooseclassofproduct();

                    }
                }).negativeText("Cancel").onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        editTextfp.setText("");
                        dialog.cancel();
                    }
                }).show();
            }
        });
        LayoutInflater li = LayoutInflater.from(view.getContext());
        final View promptsView = li.inflate(R.layout.fp_material_dialog, null);
        final Spinner spinner = promptsView.findViewById(R.id.spinner);
        langfactorTV = promptsView.findViewById(R.id.langfactv);
        editTextfp = promptsView.findViewById(R.id.editTextFP);
        addlang();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(view.getContext(), R.layout.spinneritem
                , lang);
        spinner.setAdapter(arrayAdapter);
        fp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                new MaterialDialog.Builder(view.getContext()).title("Enter the value of FP:").customView(promptsView, true)
                        .positiveText("OK").negativeText("Cancel").cancelable(false)
                        .canceledOnTouchOutside(false).onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        editTextfp.setText("");
                        dialog.cancel();
                    }
                }).onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        if (editTextfp.getText().toString().trim().length() != 0 && editTextfp.getText().toString().trim().charAt(0) != '.' && editTextfp.getText().toString().trim().charAt(editTextfp.getText().toString().trim().length() - 1) != '.') {
                            MainActivity.fpvalue = Double.parseDouble(editTextfp.getText().toString().trim());
                            MainActivity.isSLOC = false;
                            chooseclassofproduct();
                        } else {
                            editTextfp.setText("");
                            Snackbar.make(v, "Error! Invalid Input", Snackbar.LENGTH_SHORT).show();
                        }
                    }
                }).show();
            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    langfactorTV.setText("Language Factor: 53");
                    MainActivity.langfactor = 53;

                } else if (position == 1) {
                    langfactorTV.setText("Language Factor: 97");
                    MainActivity.langfactor = 97;
                } else if (position == 2) {
                    langfactorTV.setText("Language Factor: 50");
                    MainActivity.langfactor = 50;
                } else if (position == 3) {
                    langfactorTV.setText("Language Factor: 54");
                    MainActivity.langfactor = 54;
                } else if (position == 4) {
                    langfactorTV.setText("Language Factor: 47");
                    MainActivity.langfactor = 47;
                } else if (position == 5) {
                    langfactorTV.setText("Language Factor: 34");
                    MainActivity.langfactor = 34;
                } else if (position == 6) {
                    langfactorTV.setText("Language Factor: 21");
                    MainActivity.langfactor = 21;
                } else if (position == 7) {
                    langfactorTV.setText("Language Factor: 57");
                    MainActivity.langfactor = 57;
                } else if (position == 8) {
                    langfactorTV.setText("Language Factor: 24");
                    MainActivity.langfactor = 24;
                } else if (position == 9) {
                    langfactorTV.setText("Language Factor: 37");
                    MainActivity.langfactor = 37;
                } else if (position == 10) {
                    langfactorTV.setText("Language Factor: 51");
                    MainActivity.langfactor = 51;
                } else if (position == 11) {
                    langfactorTV.setText("Language Factor: 42");
                    MainActivity.langfactor = 42;
                } else if (position == 12) {
                    langfactorTV.setText("Language Factor: 119");
                    MainActivity.langfactor = 119;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public void chooseclassofproduct()
    {
        new MaterialDialog.Builder(getView().getContext()).title("Choose the class of product:").items(classofproduct).itemsCallbackSingleChoice(0, new MaterialDialog.ListCallbackSingleChoice() {
            @Override
            public boolean onSelection(MaterialDialog dialoge, View itemView, int which, CharSequence text) {
                MainActivity.selectedclass=which;
                return true;
            }
        }).positiveText("OK").negativeText("Cancel").canceledOnTouchOutside(false).cancelable(false)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialoge, @NonNull DialogAction which) {
                        costperperson();
                    }
                }).onNegative(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialoge, @NonNull DialogAction which) {
                dialoge.dismiss();
            }
        }).show();
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

    public void setcostdrivers()
    {
        for(int i=0;i<15;i++)
        {
            MainActivity.costdrivers[i]=1;
        }
    }

    public void costperperson()
    {
        new MaterialDialog.Builder(getContext()).title("Enter Cost per Person: ").cancelable(false)
                .canceledOnTouchOutside(false).positiveText("OK").negativeText("Cancel")
                .inputRange(1,18)
                .inputType(InputType.TYPE_NUMBER_FLAG_DECIMAL)
                .input("Example: 1,10,100....", null, false, new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                            try {
                                if(Double.parseDouble(input.toString())>0) {
                                    MainActivity.costperperson = Double.parseDouble(input.toString().trim());
                                }
                                else
                                {
                                    Snackbar.make(getView(),"Error! Cost per person can't be negative.",Snackbar.LENGTH_SHORT).show();
                                }
                            }
                            catch (Exception e)
                            {
                                Snackbar.make(getView(), "Error! Invalid Input", Snackbar.LENGTH_SHORT).show();
                            }
                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.cancel();
                    }
                }).onPositive(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                    fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                    fragmentTransaction.replace(R.id.MainFrame, new ResultFragment()).commit();
            }
        }).show();
    }

}


