package com.example.hellbabine.mathdroid;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Hellbabine on 2015-01-27.
 */
public class PortraitFragment extends Fragment {
    private TextView txtEquation;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.setRetainInstance(true);
        View v = inflater.inflate(R.layout.portrait, container, false);
        txtEquation = (TextView) v.findViewById(R.id.txtEquation);
        return v;
    }

    public void btnClick(View v)
    {
        //TODO faire le switch pour savoir quel btn
        Button btn = (Button) v;
        System.out.println(btn.getText());
    }
    public void btn1Click(View v)
    {
        AddCarToEquation("1");
    }

    public void btn2Click(View v)
    {
        AddCarToEquation("2");
    }

    public void btn3Click(View v)
    {
        AddCarToEquation("3");
    }

    public void btn4Click(View v)
    {
        AddCarToEquation("4");
    }

    public void btn5Click(View v)
    {
        AddCarToEquation("5");
    }

    public void btn6Click(View v)
    {
        AddCarToEquation("6");
    }

    public void btn7Click(View v)
    {
        AddCarToEquation("7");
    }

    public void btn8Click(View v)
    {
        AddCarToEquation("8");
    }

    public void btn9Click(View v)
    {
        AddCarToEquation("9");
    }

    public void btn0Click(View v)
    {
        AddCarToEquation("0");
    }

    public void btnPlusClick(View v)
    {
        AddCarToEquation("+");
    }

    public void btnMinusClick(View v)
    {
        AddCarToEquation("-");
    }

    public void btnDivideClick(View v)
    {
        AddCarToEquation("/");
    }

    public void btnMultiplyClick(View v)
    {
        AddCarToEquation("*");
    }

    public void btnEqualClick(View v)
    {
        AddCarToEquation("=");
    }

    public void btnComaClick(View v)
    {
        AddCarToEquation(".");
    }

    public void btnPowerClick(View v)
    {
        AddCarToEquation("^");
    }

    public void btnVarClick(View v)
    {
        AddCarToEquation("x");
    }

    public void btnOpenClick(View v)
    {
        AddCarToEquation("(");
    }

    public void btnCloseClick(View v)
    {
        AddCarToEquation(")");
    }

    public void btnEraseClick(View v)
    {
        if(txtEquation.getText().length() != 0)
        {
            txtEquation.setText(txtEquation.getText().subSequence(0, txtEquation.getText().length() -1));
        }
    }

    public void AddCarToEquation(String c)
    {
        if(txtEquation != null) {
            txtEquation.setText(txtEquation.getText() + c);
        }
        else
        {
            System.out.println("TxtEquation == Null");
        }
    }
}
