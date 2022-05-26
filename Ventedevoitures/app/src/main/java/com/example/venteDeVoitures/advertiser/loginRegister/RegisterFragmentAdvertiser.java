package com.example.venteDeVoitures.advertiser.loginRegister;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.venteDeVoitures.R;
import com.example.venteDeVoitures.advertiser.AdvertiserMainActivity;
import com.example.venteDeVoitures.roomDB.VenteDeVoitureDatabase;
import com.example.venteDeVoitures.roomDB.entities.Advertiser;

public class RegisterFragmentAdvertiser extends Fragment {
    SwitchLoginRegister i;
    VenteDeVoitureDatabase db;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        db = VenteDeVoitureDatabase.getInstance(getActivity());
        return inflater.inflate(R.layout.advertiser_fragment_register, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        view.findViewById(R.id.advertiser_fragment_register_button_reg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = ((EditText)getActivity().findViewById(R.id.advertiser_fragment_register_username_et)).getText().toString();
                String password = ((EditText)getActivity().findViewById(R.id.advertiser_fragment_register_password_et)).getText().toString();
                String email = ((EditText)getActivity().findViewById(R.id.advertiser_fragment_register_email_et)).getText().toString();
                String phone = ((EditText)getActivity().findViewById(R.id.advertiser_fragment_register_phone_et)).getText().toString();
                boolean professional = ((CheckBox)getActivity().findViewById(R.id.advertiser_fragment_register_professional_cb)).isChecked();

                if(db.advertiserDAO().getAvertiserByUsername(username) == null){
                    db.advertiserDAO().insertNewUser(new Advertiser(username, password, professional, email, phone));
                    Toast.makeText(getActivity(), "Registered !", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast toast = Toast.makeText(getActivity(), "Account already exists !", Toast.LENGTH_SHORT);

                    toast.show();
                }
            }
        });

        view.findViewById(R.id.advertiser_fragment_register_button_log).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i.callBack(0);
            }
        });
    }

    public void setInterface(SwitchLoginRegister i){
        this.i = i;
    }
}