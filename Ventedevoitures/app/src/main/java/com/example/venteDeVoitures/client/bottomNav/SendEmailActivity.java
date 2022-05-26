package com.example.venteDeVoitures.client.bottomNav;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.venteDeVoitures.R;
import com.example.venteDeVoitures.client.ClientViewModel;
import com.google.android.material.appbar.MaterialToolbar;

public class SendEmailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_activity_send_email);
        Bundle extras = getIntent().getExtras();
        ClientViewModel viewModel = ClientViewModel.getInstance();

        MaterialToolbar toolbar = findViewById(R.id.client_activity_send_email_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        EditText dest = findViewById(R.id.client_activity_send_email_to_et);
        dest.setText(extras.getString("email"));
        EditText subject = findViewById(R.id.client_activity_send_email_subject_et);
        EditText message = findViewById(R.id.client_activity_send_email_message_et);

        Button send = findViewById(R.id.client_activity_send_email_button);
        Context context = this;
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String adresseString = dest.getText().toString();
                String messString = message.getText().toString();
                String subString = subject.getText().toString();
                String[] addresses = adresseString.split(",");
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_EMAIL, addresses);
                intent.putExtra(Intent.EXTRA_SUBJECT, subString);
                intent.putExtra(Intent.EXTRA_TEXT, messString);

                if(intent.resolveActivity(getPackageManager()) != null){
                    startActivity(intent);
                    Toast.makeText(context, "Send !", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(context, "Send failed", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });

    }
}