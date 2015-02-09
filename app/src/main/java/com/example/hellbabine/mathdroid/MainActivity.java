package com.example.hellbabine.mathdroid;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends FragmentActivity {
    private Frag currentFrag;
    private String SAVE_EQUATION;
    private String EQUATION;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(currentFrag == null) {
            currentFrag = new Frag();
        }
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            setContentView(R.layout.activity_main);
            String equation = savedInstanceState.getString(SAVE_EQUATION);
            Bundle bundle = new Bundle();
            bundle.putString("EQUATION", equation);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, currentFrag).commit();
            currentFrag.setArguments(bundle);
        } else {
            System.out.println("Lancement de l'app");
            setContentView(R.layout.activity_main);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, currentFrag).commit();
        }

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return currentFrag.onCreateView(inflater, container, savedInstanceState);
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        System.out.println("Save Context");
        savedInstanceState.putCharSequence(SAVE_EQUATION, currentFrag.GetEquation());
        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }



    public void btnClick(View v){
       currentFrag.btnClick(v);
    }

    public static class Frag extends android.support.v4.app.Fragment{
        private TextView txtEquation;
        private String equation;
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
           // this.setRetainInstance(true);

            View v = inflater.inflate(R.layout.activity_main, container, false);
            txtEquation = (TextView) v.findViewById(R.id.txtEquation);
            return v;
        }

        @Override
        public void onStart() {
            super.onStart();
            Bundle bundle = this.getArguments();
            if (bundle != null) {
                equation = bundle.getString("EQUATION","");
            }
            else
            {
                equation = "FUCK OFF";
            }
            this.setEquation(equation);
        }

        public void setEquation(String equa)
        {
            txtEquation.setText(equa);
        }

        public void btnClick(View v)
        {
            //TODO faire le switch pour savoir quel btn
            Button btn = (Button) v;
            System.out.println(btn.getText());
            switch(btn.getId())
            {
                case R.id.btn0 :
                    AddCarToEquation("0");
                    break;
                case R.id.btn1 :
                    AddCarToEquation("1");
                    break;
                case R.id.btn2 :
                    AddCarToEquation("2");
                    break;
                case R.id.btn3 :
                    AddCarToEquation("3");
                    break;
                case R.id.btn4 :
                    AddCarToEquation("4");
                    break;
                case R.id.btn5 :
                    AddCarToEquation("5");
                    break;
                case R.id.btn6 :
                    AddCarToEquation("6");
                    break;
                case R.id.btn7 :
                    AddCarToEquation("7");
                    break;
               // case R.id.btn8 :
                 //   AddCarToEquation("8");
                   // break;
                case R.id.btn9 :
                    AddCarToEquation("9");
                    break;
                case R.id.btnPlus :
                    AddCarToEquation("+");
                    break;
                case R.id.btnMinus :
                    AddCarToEquation("-");
                    break;
                case R.id.btnDivide:
                    AddCarToEquation("/");
                    break;
                case R.id.btnMultiply :
                    AddCarToEquation("*");
                    break;
                case R.id.btnEqual :
                    AddCarToEquation("=");
                    break;
                case R.id.btnComa :
                    AddCarToEquation(",");
                    break;
                case R.id.btnPower :
                    AddCarToEquation("^");
                    break;
                case R.id.btnVar :
                    AddCarToEquation("x");
                    break;
                case R.id.btnOpen :
                    AddCarToEquation("(");
                    break;
                case R.id.btnClose :
                    AddCarToEquation(")");
                    break;
                case R.id.btnErase :
                    btnEraseClick(v);
                    break;
            }
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

        public CharSequence GetEquation()
        {
            return txtEquation.getText();
        }
    }
}
