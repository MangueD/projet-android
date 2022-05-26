package com.example.venteDeVoitures.client.bottomNav.profile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.venteDeVoitures.R;
import com.example.venteDeVoitures.advertiser.loginRegister.SwitchLoginRegister;
import com.example.venteDeVoitures.client.ClientMainActivity;
import com.example.venteDeVoitures.client.ClientViewModel;
import com.example.venteDeVoitures.roomDB.VenteDeVoitureDatabase;
import com.example.venteDeVoitures.roomDB.entities.Advertiser;

public class RegisterFragmentClient extends Fragment {
    SwitchLoginRegister i;
    ClientViewModel viewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewModel = ClientViewModel.getInstance();
        return inflater.inflate(R.layout.client_fragment_register, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        view.findViewById(R.id.client_fragment_register_button_reg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = ((EditText)getActivity().findViewById(R.id.client_fragment_register_username_et)).getText().toString();
                String password = ((EditText)getActivity().findViewById(R.id.client_fragment_register_password_et)).getText().toString();

                if(viewModel.createUser(username, password)){
                    Toast.makeText(getActivity(), "Registered !", Toast.LENGTH_SHORT).show();
                    i.callBack(ClientMainActivity.LOGIN);
                }
                else{
                    Toast.makeText(getActivity(), "Account already exists !", Toast.LENGTH_SHORT).show();
                }
            }
        });

        view.findViewById(R.id.client_fragment_register_button_log).setOnClickListener(new View.OnClickListener() {
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