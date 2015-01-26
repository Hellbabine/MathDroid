package com.example.hellbabine.mathdroid;

import android.app.Activity;
import android.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity implements View.OnClickListener {
    //private TextView txtEquation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("Lancement de l'app");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new UI_Fragment())
                    .commit();
        }
        Button btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(this);

        Button btn2 = (Button) findViewById(R.id.btn2);
        btn2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Button btn = (Button) v;
        System.out.println(btn.getId());
        System.out.println(btn.getText());
    }

    /* Created by Hellbabine on 2015-01-25.
    */
    public static class UI_Fragment extends Fragment{
        private TextView txtEquation;
        public UI_Fragment()
        {
            this.setRetainInstance(true);
        }

        public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.activity_main, container, false);
            txtEquation = (TextView) view.findViewById(R.id.txtEquation);
            return view;
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
            AddCarToEquation("X");
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



        private void AddCarToEquation(String c)
        {
            txtEquation.setText(txtEquation.getText() + c);
        }
    }

}
