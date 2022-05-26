package com.example.venteDeVoitures.advertiser.loginRegister;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.venteDeVoitures.R;

public class AdvertiserLoginRegisterActivity extends AppCompatActivity implements SwitchLoginRegister{
    LoginFragmentAdvertiser loginFragment;
    RegisterFragmentAdvertiser registerFragment;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.advertiser_login_register_activity);
        loginFragment = new LoginFragmentAdvertiser();
        loginFragment.setInterface(this);
        registerFragment = new RegisterFragmentAdvertiser();
        registerFragment.setInterface(this);
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.advertiser_login_register_container, loginFragment).commit();
    }

    @Override
    public void callBack(int c){
        if(c == 0){
            fragmentManager.beginTransaction().replace(R.id.advertiser_login_register_container, loginFragment).commit();
        }
        else{
            fragmentManager.beginTransaction().replace(R.id.advertiser_login_register_container, registerFragment).commit();
        }
    }
}
