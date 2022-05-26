package com.example.venteDeVoitures.client.bottomNav.newAdverts;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.venteDeVoitures.R;
import com.example.venteDeVoitures.client.ClientViewModel;
import com.example.venteDeVoitures.client.bottomNav.AdvertDetailsActivity;
import com.example.venteDeVoitures.roomDB.entities.Rental;
import com.example.venteDeVoitures.roomDB.entities.Sell;

import java.util.Collections;
import java.util.List;

public class NewFragmentClient extends Fragment {
    ScrollView scrollView;
    ClientViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.client_fragment_new, container, false);
    }

    @Override
    public void onViewCreated(View v, Bundle b){
        scrollView = v.findViewById(R.id.client_fragment_new_scrollview);
    }

    @Override
    public void onResume(){
        super.onResume();
        scrollView.removeAllViews();
        List<Sell> sells = viewModel.getNewSells();
        List<Rental> rentals = viewModel.getNewRentals();
        Collections.reverse(sells);
        Collections.reverse(rentals);
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

            ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), AdvertDetailsActivity.class);
                    intent.putExtra("type", "rental");
                    intent.putExtra("id", r.id);
                    intent.putExtra("brand", r.brand);
                    intent.putExtra("model", r.model);
                    intent.putExtra("distance", r.distance);
                    intent.putExtra("year", r.year);
                    intent.putExtra("price", r.price);
                    intent.putExtra("image1", r.image1);
                    intent.putExtra("image2", r.image2);
                    intent.putExtra("image3", r.image3);
                    intent.putExtra("image4", r.image4);
                    intent.putExtra("via_email", r.email);
                    intent.putExtra("via_phone", r.phone);
                    intent.putExtra("advertiser_id",r.id_owner);
                    startActivity(intent);
                }
            });
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

            ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), AdvertDetailsActivity.class);
                    intent.putExtra("type", "sell");
                    intent.putExtra("id", s.id);
                    intent.putExtra("brand", s.brand);
                    intent.putExtra("model", s.model);
                    intent.putExtra("distance", s.distance);
                    intent.putExtra("year", s.year);
                    intent.putExtra("price", s.price);
                    intent.putExtra("image1", s.image1);
                    intent.putExtra("image2", s.image2);
                    intent.putExtra("image3", s.image3);
                    intent.putExtra("image4", s.image4);
                    intent.putExtra("via_email", s.email);
                    intent.putExtra("via_phone", s.phone);
                    intent.putExtra("advertiser_id",s.owner);
                    startActivity(intent);
                }
            });
            linearLayout.addView(ll);
        }
        scrollView.addView(linearLayout);
    }

    public void setArgs(ClientViewModel vm){
        this.viewModel = vm;
    }
}
