package com.example.venteDeVoitures.client.bottomNav;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.venteDeVoitures.R;
import com.example.venteDeVoitures.client.ClientViewModel;
import com.example.venteDeVoitures.roomDB.entities.Advertiser;
import com.google.android.material.appbar.MaterialToolbar;

public class AdvertDetailsActivity extends AppCompatActivity {
    AdvertiserAdvertsActivity result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_activity_advertdetails);
        Bundle extras = getIntent().getExtras();
        ClientViewModel viewModel = ClientViewModel.getInstance();
        result = new AdvertiserAdvertsActivity();
        MaterialToolbar toolbar = findViewById(R.id.client_activity_advertdetails_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        int ownerID = extras.getInt("advertiser_id");
        Advertiser advertiser = viewModel.getAdvertiser(ownerID);
        TextView brand = ((TextView) findViewById(R.id.client_activity_advertdetails_brand_et));
        TextView model = ((TextView) findViewById(R.id.client_activity_advertdetails_model_et));
        TextView year = ((TextView) findViewById(R.id.client_activity_advertdetails_year_et));
        TextView distance = ((TextView) findViewById(R.id.client_activity_advertdetails_distance_et));
        TextView price = ((TextView) findViewById(R.id.client_activity_advertdetails_price_et));
        TextView owner = ((TextView) findViewById(R.id.client_activity_advertdetails_owner_et));
        LinearLayout imagesLayout = findViewById(R.id.client_activity_advertdetails_imagell);
        LinearLayout contactLayout = findViewById(R.id.client_activity_advertdetails_contactlayout);
        Button saving = findViewById(R.id.client_activity_advertdetails_savebt);
        AppCompatActivity context = this;
        saving.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!viewModel.isConnected()){
                   Toast.makeText(context,"You must be connected to use this option !", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(viewModel.savedExist(extras.getString("type"), extras.getInt("id"))){
                        Toast.makeText(context,"saved !", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(context,"Already saved !", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        brand.setText(extras.getString("brand"));
        model.setText(extras.getString("model"));
        year.setText(extras.getString("year"));
        distance.setText(extras.getString("distance"));
        price.setText(extras.getString("price"));
        owner.setText(advertiser.username);
        if(viewModel.isConnected()){
            owner.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, AdvertiserAdvertsActivity.class);
                    intent.putExtra("id", ownerID);
                    startActivity(intent);
                }
            });
        }

        for(int i=0 ; i<4 ; i++){
            byte[] imageByte = extras.getByteArray("image"+(Integer.toString(i+1)));
            ImageView image= new ImageView(this);

            if(imageByte != null) {
                imagesLayout.addView(image);
                image.setImageBitmap(BitmapFactory.decodeByteArray(imageByte, 0, imageByte.length));
            }
        }

        if(extras.getBoolean("via_phone")){
            Button button = new Button(this);
            button.setText("Call "+advertiser.phone);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(ContextCompat.checkSelfPermission(context.getApplicationContext(),
                            Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED){
                        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + advertiser.phone));
                        startActivity(intent);
                    }
                    else{
                            ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.CALL_PHONE}, 1);
                    }

                }
            });
            contactLayout.addView(button);
        }
        if(extras.getBoolean("via_email")){
            Button button = new Button(this);
            button.setText("Email");
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, SendEmailActivity.class);
                    intent.putExtra("email", advertiser.email);
                    startActivity(intent);
                }
            });
            contactLayout.addView(button);
        }


    }
}
