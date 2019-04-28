package com.sg.cocomo_simulator.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sg.cocomo_simulator.R;
import com.sg.cocomo_simulator.model.CostDriver;
import com.sg.cocomo_simulator.view.MainActivity;

import java.util.ArrayList;

public class CostDriverAdapter extends RecyclerView.Adapter<CostDriverAdapter.CostDriverViewHolder> {
    private ArrayList<CostDriver> costDrivers;
    private Context context;


    public CostDriverAdapter(ArrayList<CostDriver> costDrivers,Context context) {
        this.costDrivers = costDrivers;
        this.context=context;
    }

    @NonNull
    @Override
    public CostDriverViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.costdriverlistitem,parent,false);
        return new CostDriverViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CostDriverViewHolder holder, int position) {
        holder.name.setText(costDrivers.get(position).getName());
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(context,R.layout.spinneritem,costDrivers.get(position).getStrings());
        holder.spinner.setAdapter(arrayAdapter);
        holder.spinner.setSelection(costDrivers.get(position).getSelectedspinner());
        double[] values=costDrivers.get(position).getSpinnervalues();
        holder.selectedvalue.setText("Value: "+values[costDrivers.get(position).getSelectedspinner()]);

    }

    @Override
    public int getItemCount() {
        return costDrivers==null?0:costDrivers.size();
    }

    class CostDriverViewHolder extends RecyclerView.ViewHolder{
        Spinner spinner;
        TextView name;
        TextView selectedvalue;

        public CostDriverViewHolder(@NonNull View itemView) {
            super(itemView);
            spinner=itemView.findViewById(R.id.costdriverspinner);
            name=itemView.findViewById(R.id.costdrivername);
            selectedvalue=itemView.findViewById(R.id.costdrivervalue);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if(position!=costDrivers.get(getAdapterPosition()).getSelectedspinner())
                    {
                        double[] values=costDrivers.get(getAdapterPosition()).getSpinnervalues();
                        MainActivity.costdrivers[getAdapterPosition()]=values[position];
                        costDrivers.get(getAdapterPosition()).setSelectedspinner(position);
                        notifyDataSetChanged();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
    }
}
