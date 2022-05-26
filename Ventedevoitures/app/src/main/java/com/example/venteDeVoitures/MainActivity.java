package com.example.venteDeVoitures;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.venteDeVoitures.advertiser.AdvertiserMainActivity;
import com.example.venteDeVoitures.advertiser.loginRegister.AdvertiserLoginRegisterActivity;
import com.example.venteDeVoitures.client.ClientMainActivity;

import java.sql.Connection;
import java.sql.DriverManager;

public class MainActivity extends AppCompatActivity {
    Button clientButton;
    Button advertiserButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_choice);
        Button clientButton = (Button)findViewById(R.id.profile_choice_client);
        Button advertiserButton = (Button)findViewById(R.id.profile_choice_advertiser);

        clientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ClientMainActivity.class);
                startActivity(intent);
            }
        });

        advertiserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AdvertiserLoginRegisterActivity.class);
                startActivity(intent);
            }
        });

    }
}