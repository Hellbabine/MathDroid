package com.example.hellbabine.mathdroid;

import android.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
    private TextView txtEquation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("Lancement de l'app");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtEquation = (TextView) findViewById(R.id.txtEquation);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
