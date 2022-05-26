package com.example.venteDeVoitures.advertiser.loginRegister;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.venteDeVoitures.R;
import com.example.venteDeVoitures.advertiser.AdvertiserMainActivity;
import com.example.venteDeVoitures.roomDB.VenteDeVoitureDatabase;
import com.example.venteDeVoitures.roomDB.entities.Advertiser;


public class LoginFragmentAdvertiser extends Fragment {
    private VenteDeVoitureDatabase db;
    public SwitchLoginRegister i;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        db = VenteDeVoitureDatabase.getInstance(getActivity());
        return inflater.inflate(R.layout.advertiser_fragment_login, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        view.findViewById(R.id.advertiser_fragment_login_button_sign).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = ((EditText)getActivity().findViewById(R.id.advertiser_fragment_login_username_et)).getText().toString();
                String password = ((EditText)getActivity().findViewById(R.id.advertiser_fragment_login_password_et)).getText().toString();
                Advertiser user = db.advertiserDAO().getAvertiserByUsernamePassword(username,password);
                if(user != null){
                    Intent intent = new Intent(view.getContext(), AdvertiserMainActivity.class);
                    intent.putExtra("userID", user.id);
                    startActivity(intent);
                }
                else{
                    System.out.println("username or password incorrect");
                }
            }
        });

        view.findViewById(R.id.advertiser_fragment_login_button_reg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i.callBack(1);
            }
        });
    }

    public void setInterface(SwitchLoginRegister i){
        this.i = i;
    }
}