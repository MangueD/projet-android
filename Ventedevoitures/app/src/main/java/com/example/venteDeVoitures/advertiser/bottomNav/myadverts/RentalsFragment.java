package com.example.venteDeVoitures.advertiser.bottomNav.myadverts;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.venteDeVoitures.R;
import com.example.venteDeVoitures.advertiser.AdvertiserViewModel;
import com.example.venteDeVoitures.roomDB.entities.Rental;

import java.util.List;


public class RentalsFragment extends Fragment {
    RelativeLayout relativeLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.advertiser_fragment_rentals, container, false);
    }

    @Override
    public void onViewCreated(View v, Bundle bundle){
        relativeLayout = v.findViewById(R.id.advertiser_fragment_rentals_layout);
    }



    @Override
    public void onResume(){
        super.onResume();
        AdvertiserViewModel viewModel = AdvertiserViewModel.getInstance();
        List<Rental> rentals = viewModel.getUserRentals();
        if(rentals != null){
            LinearLayout linearLayoutl = new LinearLayout(getActivity());
            linearLayoutl.setOrientation(LinearLayout.VERTICAL);
            int i = 0;
            for(Rental r : rentals){
                LinearLayout ll = new LinearLayout(getActivity());
                ll.setOrientation(LinearLayout.VERTICAL);
                TextView tv1 = new TextView(getActivity());
                tv1.setText("Marque :");
                ll.addView(tv1);
                TextView brandTV = new TextView(getActivity());
                brandTV.setText(r.brand);
                ll.addView(brandTV);


                TextView tv2 = new TextView(getActivity());
                tv2.setText("Modele :");
                ll.addView(tv2);
                TextView modelTV = new TextView(getActivity());
                modelTV.setText(r.model);
                ll.addView(modelTV);

                TextView tv3 = new TextView(getActivity());
                tv3.setText("Distance :");
                ll.addView(tv3);
                TextView distanceTV = new TextView(getActivity());
                distanceTV.setText(r.distance);
                ll.addView(distanceTV);

                TextView tv4 = new TextView(getActivity());
                tv4.setText("Date :");
                ll.addView(tv4);
                TextView yearTV = new TextView(getActivity());
                yearTV.setText(r.year);
                ll.addView(yearTV);

                TextView tv5= new TextView(getActivity());
                tv5.setText("Distance :");
                ll.addView(tv5);
                TextView priceTV = new TextView(getActivity());
                priceTV.setText(r.price);
                ll.addView(priceTV);

                try {
                    ImageView image1 = new ImageView(getActivity());
                    image1.setImageBitmap(BitmapFactory.decodeByteArray(r.image1, 0, r.image1.length));
                    ll.addView(image1);
                }
                catch (NullPointerException e){}

                try {
                    ImageView image2 = new ImageView(getActivity());
                    image2.setImageBitmap(BitmapFactory.decodeByteArray(r.image2, 0, r.image2.length));
                    ll.addView(image2);}
                catch (NullPointerException e){}

                try {
                    ImageView image3 = new ImageView(getActivity());
                    image3.setImageBitmap(BitmapFactory.decodeByteArray(r.image3, 0, r.image3.length));
                    ll.addView(image3);}
                catch (NullPointerException e){}

                try {
                    ImageView image4 = new ImageView(getActivity());
                    image4.setImageBitmap(BitmapFactory.decodeByteArray(r.image4, 0, r.image4.length));
                    ll.addView(image4);}
                catch (NullPointerException e){}

                Button editBT = new Button(getActivity());
                editBT.setText("Edit");
                editBT.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(), EditRentalActivity.class);
                        intent.putExtra("brand", r.brand);
                        intent.putExtra("model", r.model);
                        intent.putExtra("distance", r.distance);
                        intent.putExtra("year", r.year);
                        intent.putExtra("price", r.price);
                        intent.putExtra("image1", r.image1);
                        intent.putExtra("image2", r.image2);
                        intent.putExtra("image3", r.image3);
                        intent.putExtra("image4", r.image4);
                        intent.putExtra("id",r.id);
                        startActivity(intent);
                    }
                });
                Button deleteBT = new Button(getActivity());
                deleteBT.setText("Delete");
                deleteBT.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        viewModel.deleteRental(r);
                        linearLayoutl.removeView(ll);
                    }
                });
                LinearLayout ll2 = new LinearLayout(getActivity());
                ll2.addView(editBT);
                ll2.addView(deleteBT);

                ll.addView(ll2);
                linearLayoutl.addView(ll);
            }
            ScrollView scrollView = new ScrollView(getActivity());
            relativeLayout.removeAllViews();
            relativeLayout.addView(scrollView);
            scrollView.getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;
            scrollView.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
            scrollView.addView(linearLayoutl);
        }
    }


}