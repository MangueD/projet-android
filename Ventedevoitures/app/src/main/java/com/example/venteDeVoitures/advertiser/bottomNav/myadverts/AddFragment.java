package com.example.venteDeVoitures.advertiser.bottomNav.myadverts;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.venteDeVoitures.R;
import com.example.venteDeVoitures.RealPathUtil;
import com.example.venteDeVoitures.advertiser.AdvertiserViewModel;

import java.io.ByteArrayOutputStream;

public class AddFragment extends Fragment {
    private final static int IMAGE_1 = 100;
    private final static int IMAGE_2 = 110;
    private final static int IMAGE_3 = 120;
    private final static int IMAGE_4 = 140;
    private int imagePtr;
    private int[] imageID = {IMAGE_1, IMAGE_2, IMAGE_3, IMAGE_4};
    private ImageButton[] imageButtons;
    private int nImages;
    private Bitmap[] bitmaps;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.advertiser_fragment_add, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle bundle){
        super.onViewCreated(view, bundle);
        RadioButton sellRadioButton = view.findViewById(R.id.advertiser_fragment_add_radio_sell);
        EditText brand = ((EditText) view.findViewById(R.id.advertiser_fragment_add_brand_et));
        EditText model = ((EditText) view.findViewById(R.id.advertiser_fragment_add_model_et));
        EditText year = ((EditText) view.findViewById(R.id.advertiser_fragment_add_year_et));
        EditText distance = ((EditText) view.findViewById(R.id.advertiser_fragment_add_distance_et));
        EditText price = ((EditText) view.findViewById(R.id.advertiser_fragment_add_price_et));
        CheckBox viaEmail = view.findViewById(R.id.advertiser_fragment_add_emailcb);
        CheckBox viaPhone = view.findViewById(R.id.advertiser_fragment_add_phonecb);
        LinearLayout imagesLayout = view.findViewById(R.id.advertiser_fragment_add_imagell);
        AdvertiserViewModel viewModel = new ViewModelProvider(getActivity()).get(AdvertiserViewModel.class);

        nImages = 2;
        if(viewModel.isProfessional())
            nImages = 4;
        imageButtons = new ImageButton[nImages];
        bitmaps = new Bitmap[nImages];
        for(int i=0 ; i<nImages ; i++){
            int n = i;
            imageButtons[i] = new ImageButton(getActivity());
            imageButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(ContextCompat.checkSelfPermission(getActivity().getApplicationContext(),
                            Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                        imagePtr = n;
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(intent.ACTION_GET_CONTENT);
                        startActivityForResult(intent, 10);
                    }
                    else{
                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                    }
                }
            });
            imageButtons[i].setImageResource(R.drawable.ic_action_name);
            imagesLayout.addView(imageButtons[i]);
        }

        view.findViewById(R.id.advertiser_fragment_add_confirmbt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                byte[][] images = new byte[4][];

                for(int i=0 ; i<nImages ; i++){
                    try{
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        bitmaps[i].compress(Bitmap.CompressFormat.JPEG,80,stream);
                        images[i] = stream.toByteArray();
                    }
                    catch(NullPointerException e){
                        images[i] = null;
                    }
                }

                if(sellRadioButton.isChecked()){
                    if (viewModel.createSell(brand.getText().toString(),
                            model.getText().toString(),
                            year.getText().toString(),
                            distance.getText().toString(),
                            price.getText().toString(),
                            viaEmail.isChecked(),
                            viaPhone.isChecked(),
                            images[0],
                            images[1],
                            images[2],
                            images[3])) {
                        System.out.println("sell created");
                    } else {
                        System.out.println("sell creation failed");
                    }
                }
                else{
                    if (viewModel.createRental(brand.getText().toString(),
                            model.getText().toString(),
                            year.getText().toString(),
                            distance.getText().toString(),
                            price.getText().toString(),
                            viaEmail.isChecked(),
                            viaPhone.isChecked(),
                            images[0],
                            images[1],
                            images[2],
                            images[3])) {
                        System.out.println("rental created");
                    } else {
                        System.out.println("rental creation failed");
                    }
                }
                Toast toast = Toast.makeText(getActivity(), "advert created !", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){

        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 10 && resultCode == Activity.RESULT_OK){
            Uri uri = data.getData();
            String path = RealPathUtil.getRealPath(getActivity(), uri);
            System.out.println(imagePtr);
            bitmaps[imagePtr] = BitmapFactory.decodeFile(path);
            imageButtons[imagePtr].setImageBitmap(bitmaps[imagePtr]);
        }
    }
}