package com.example.hellbabine.mathdroid;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends FragmentActivity {
    private Fragment currentFrag;
    private String SAVE_EQUATION;
    private String FORMAT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            Boolean format = savedInstanceState.getBoolean(FORMAT);
            if(!format)
            {
                PortraitFragment portraitFragment = new PortraitFragment();
                currentFrag = portraitFragment;
                setContentView(R.layout.activity_main);
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragment_container, portraitFragment).commit();
            }
            else
            {
                LandscapeFragment fragment = new LandscapeFragment();
                currentFrag = fragment;
                setContentView(R.layout.activity_main);

                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragment_container, fragment).commit();
            }
        } else {
            System.out.println("Lancement de l'app");
            PortraitFragment portraitFragment = new PortraitFragment();
            currentFrag = portraitFragment;
            setContentView(R.layout.activity_main);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, portraitFragment).commit();
        }

    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        if(currentFrag instanceof PortraitFragment)
        {
            PortraitFragment frag = (PortraitFragment) currentFrag;
            savedInstanceState.putCharSequence(SAVE_EQUATION, frag.GetEquation());
            savedInstanceState.putBoolean(FORMAT, true);
        }
        else if(currentFrag instanceof LandscapeFragment)
        {
            LandscapeFragment frag = (LandscapeFragment) currentFrag;
            savedInstanceState.putCharSequence(SAVE_EQUATION, frag.GetEquation());
            savedInstanceState.putBoolean(FORMAT, false);
        }
        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }



    public void btnClick(View v){
        if(currentFrag instanceof PortraitFragment)
        {
            PortraitFragment frag = (PortraitFragment) currentFrag;
            frag.btnClick(v);
        }
        else if(currentFrag instanceof LandscapeFragment)
        {
            LandscapeFragment frag = (LandscapeFragment) currentFrag;
            frag.btnClick(v);
        }
    }
}
