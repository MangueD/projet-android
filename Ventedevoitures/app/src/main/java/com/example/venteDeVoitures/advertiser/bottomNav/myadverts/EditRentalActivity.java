package com.example.venteDeVoitures.advertiser.bottomNav.myadverts;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.venteDeVoitures.R;
import com.example.venteDeVoitures.RealPathUtil;
import com.example.venteDeVoitures.advertiser.AdvertiserViewModel;
import com.google.android.material.appbar.MaterialToolbar;

import java.io.ByteArrayOutputStream;

public class EditRentalActivity extends AppCompatActivity {
    private final static int IMAGE_1 = 100;
    private final static int IMAGE_2 = 110;
    private final static int IMAGE_3 = 120;
    private final static int IMAGE_4 = 140;
    private int imagePtr;
    private int[] imageID = {IMAGE_1, IMAGE_2, IMAGE_3, IMAGE_4};
    private ImageButton[] imageButtons;
    private int nImages;
    private Bitmap[] bitmaps;
    byte[][] images = new byte[4][];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.advertiser_activity_edit_rental);
        Bundle extras = getIntent().getExtras();
        AdvertiserViewModel viewModel = AdvertiserViewModel.getInstance();

        MaterialToolbar toolbar = findViewById(R.id.advertiser_activity_edit_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        EditText brand = ((EditText) findViewById(R.id.advertiser_activity_edit_rental_brand_et));
        EditText model = ((EditText) findViewById(R.id.advertiser_activity_edit_rental_model_et));
        EditText year = ((EditText) findViewById(R.id.advertiser_activity_edit_rental_year_et));
        EditText distance = ((EditText) findViewById(R.id.advertiser_activity_edit_rental_distance_et));
        EditText price = ((EditText) findViewById(R.id.advertiser_activity_edit_rental_price_et));
        LinearLayout imagesLayout = findViewById(R.id.advertiser_activity_edit_rental_imagell);
        brand.setText(extras.getString("brand"));
        model.setText(extras.getString("model"));
        year.setText(extras.getString("year"));
        distance.setText(extras.getString("distance"));
        price.setText(extras.getString("price"));

        nImages = 2;
        if(viewModel.isProfessional())
            nImages = 4;
        imageButtons = new ImageButton[nImages];
        bitmaps = new Bitmap[nImages];



        for(int i=0 ; i<nImages ; i++){
            int n = i;
            Activity context = this;
            byte[] imageByte = extras.getByteArray("image"+(Integer.toString(i+1)));
            imageButtons[i] = new ImageButton(this);
            imageButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(ContextCompat.checkSelfPermission(getApplicationContext(),
                            Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                        imagePtr = n;
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(intent.ACTION_GET_CONTENT);
                        startActivityForResult(intent, 10);
                    }
                    else{
                        ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                    }
                }
            });
            if(imageByte == null){
                imageButtons[i].setImageResource(R.drawable.ic_action_name);
            }
            else{
                images[i] = imageByte;
                imageButtons[i].setImageBitmap(BitmapFactory.decodeByteArray(imageByte, 0, imageByte.length));
            }
            imagesLayout.addView(imageButtons[i]);
        }

        Button editBT = findViewById(R.id.advertiser_activity_edit_rental_editbt);
        editBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for(int i=0 ; i<nImages ; i++){
                    try{
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        bitmaps[i].compress(Bitmap.CompressFormat.JPEG,80,stream);
                        images[i] = stream.toByteArray();
                    }
                    catch(NullPointerException e){
                    }
                }

                viewModel.editRental(
                        extras.getInt("id"),
                        brand.getText().toString(),
                        model.getText().toString(),
                        year.getText().toString(),
                        distance.getText().toString(),
                        price.getText().toString(),
                        images[0],
                        images[1],
                        images[2],
                        images[3]
                );
                finish();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){

        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 10 && resultCode == Activity.RESULT_OK){
            Uri uri = data.getData();
            String path = RealPathUtil.getRealPath(this, uri);
            System.out.println(imagePtr);
            bitmaps[imagePtr] = BitmapFactory.decodeFile(path);
            imageButtons[imagePtr].setImageBitmap(bitmaps[imagePtr]);
        }
    }


}