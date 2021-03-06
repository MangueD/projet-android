package com.example.venteDeVoitures.advertiser.bottomNav.search;


import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.venteDeVoitures.R;
import com.example.venteDeVoitures.advertiser.AdvertiserMainActivity;
import com.example.venteDeVoitures.advertiser.AdvertiserViewModel;
import com.example.venteDeVoitures.advertiser.SearchStateChange;
import com.example.venteDeVoitures.roomDB.entities.Rental;
import com.example.venteDeVoitures.roomDB.entities.Sell;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;


public class ResultFragmentAdvertiser extends Fragment {
    private ActionBar actionBar;
    private MaterialToolbar toolbar;
    private ScrollView scrollView;
    private AdvertiserViewModel viewModel;
    private SearchStateChange searchState;
    private List<LinearLayout> rentalResults;
    private List<LinearLayout> sellResults;
    private String brand;
    private String model;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.advertiser_fragment_search_result, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        searchState = (AdvertiserMainActivity)getActivity();
        toolbar = view.findViewById(R.id.advertiser_fragment_search_result_toolbar);
        scrollView = view.findViewById(R.id.advertiser_fragment_search_result_scrollview);
        viewModel = new ViewModelProvider(getActivity()).get(AdvertiserViewModel.class);
    }



    @Override
    public void onResume() {
        super.onResume();
        ((AdvertiserMainActivity) getActivity()).setSupportActionBar(toolbar);
        actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        List<Sell> sells = viewModel.getSearchSells(brand, model);
        List<Rental> rentals = viewModel.getSearchRentals(brand, model);
        rentalResults = new ArrayList<LinearLayout>();
        sellResults = new ArrayList<LinearLayout>();
        LinearLayout linearLayout = new LinearLayout(getActivity());
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        scrollView.removeAllViews();

        for (Rental r : rentals) {
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

            rentalResults.add(ll);
            linearLayout.addView(ll);
        }

        for (Sell s : sells) {
            LinearLayout ll = new LinearLayout(getActivity());
            ll.setOrientation(LinearLayout.VERTICAL);

            TextView tv1 = new TextView(getActivity());
            tv1.setText("Marque :");
            ll.addView(tv1);
            TextView brandTV = new TextView(getActivity());
            brandTV.setText(s.brand);
            ll.addView(brandTV);


            TextView tv2 = new TextView(getActivity());
            tv2.setText("Modele :");
            ll.addView(tv2);
            TextView modelTV = new TextView(getActivity());
            modelTV.setText(s.model);
            ll.addView(modelTV);

            TextView tv3 = new TextView(getActivity());
            tv3.setText("Distance :");
            ll.addView(tv3);
            TextView distanceTV = new TextView(getActivity());
            distanceTV.setText(s.distance);
            ll.addView(distanceTV);


            TextView tv4 = new TextView(getActivity());
            tv4.setText("Date :");
            ll.addView(tv4);
            TextView yearTV = new TextView(getActivity());
            yearTV.setText(s.year);
            ll.addView(yearTV);

            TextView tv5= new TextView(getActivity());
            tv5.setText("Distance :");
            ll.addView(tv5);
            TextView priceTV = new TextView(getActivity());
            priceTV.setText(s.price);
            ll.addView(priceTV);

            try {
                ImageView image1 = new ImageView(getActivity());
                image1.setImageBitmap(BitmapFactory.decodeByteArray(s.image1, 0, s.image1.length));
                ll.addView(image1);
            }
            catch (NullPointerException e){}

            try {
                ImageView image2 = new ImageView(getActivity());
                image2.setImageBitmap(BitmapFactory.decodeByteArray(s.image2, 0, s.image2.length));
                ll.addView(image2);}
            catch (NullPointerException e){}

            try {
                ImageView image3 = new ImageView(getActivity());
                image3.setImageBitmap(BitmapFactory.decodeByteArray(s.image3, 0, s.image3.length));
                ll.addView(image3);}
            catch (NullPointerException e){}

            try {
                ImageView image4 = new ImageView(getActivity());
                image4.setImageBitmap(BitmapFactory.decodeByteArray(s.image4, 0, s.image4.length));
                ll.addView(image4);}
            catch (NullPointerException e){}

            sellResults.add(ll);
            linearLayout.addView(ll);
        }
        scrollView.addView(linearLayout);
    }


    public void setArgs(String brand, String model){
        this.brand = brand;
        this.model = model;
    }
}