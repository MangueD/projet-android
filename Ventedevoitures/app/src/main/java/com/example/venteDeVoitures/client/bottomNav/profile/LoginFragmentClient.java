package com.example.venteDeVoitures.client.bottomNav.profile;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.venteDeVoitures.R;
import com.example.venteDeVoitures.advertiser.AdvertiserMainActivity;
import com.example.venteDeVoitures.advertiser.loginRegister.SwitchLoginRegister;
import com.example.venteDeVoitures.client.ClientMainActivity;
import com.example.venteDeVoitures.client.ClientViewModel;
import com.example.venteDeVoitures.roomDB.VenteDeVoitureDatabase;
import com.example.venteDeVoitures.roomDB.entities.Advertiser;
import com.example.venteDeVoitures.roomDB.entities.Client;

public class LoginFragmentClient extends Fragment {

    private ClientViewModel viewModel;
    public SwitchLoginRegister i;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewModel = ClientViewModel.getInstance();
        return inflater.inflate(R.layout.client_fragment_login, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        view.findViewById(R.id.client_fragment_login_button_sign).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = ((EditText)getActivity().findViewById(R.id.client_fragment_login_username_et)).getText().toString();
                String password = ((EditText)getActivity().findViewById(R.id.client_fragment_login_password_et)).getText().toString();
                if(viewModel.connect(username, password)){
                    i.callBack(ClientMainActivity.CONNECTED);
                }
                else{
                    System.out.println("username or password incorrect");
                }
            }
        });

        view.findViewById(R.id.client_fragment_login_button_reg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i.callBack(ClientMainActivity.REGISTER);
            }
        });
    }

    public void setInterface(SwitchLoginRegister i){
        this.i = i;
    }
}