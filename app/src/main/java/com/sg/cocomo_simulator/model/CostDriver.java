package com.sg.cocomo_simulator.model;

import java.util.ArrayList;

public class CostDriver {
    private String name;
    private double selectedspinnervalue;
    private String[] strings;
    private int selectedspinner;
    private double[] spinnervalues;

    public CostDriver(String name, double selectedspinnervalue, String[] strings, double[] spinnervalues) {
        this.name = name;
        this.selectedspinnervalue = selectedspinnervalue;
        this.strings = strings;
        this.spinnervalues = spinnervalues;
        for(int i=0;i<spinnervalues.length;i++)
        {
            if(spinnervalues[i]==selectedspinnervalue)
            {
                selectedspinner=i;
                break;
            }
        }
    }

    public double[] getSpinnervalues() {
        return spinnervalues;
    }

    public void setSpinnervalues(double[] spinnervalues) {
        this.spinnervalues = spinnervalues;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSelectedspinner() {
        return selectedspinner;
    }

    public void setSelectedspinner(int selectedspinner) {
        this.selectedspinner = selectedspinner;
    }

    public String[] getStrings() {
        return strings;
    }

    public void setStrings(String[] strings) {
        this.strings = strings;
    }
}
