package com.example.venteDeVoitures.client.bottomNav;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.venteDeVoitures.R;
import com.example.venteDeVoitures.advertiser.SearchStateChange;
import com.example.venteDeVoitures.client.ClientMainActivity;
import com.example.venteDeVoitures.client.ClientViewModel;
import com.example.venteDeVoitures.roomDB.entities.Rental;
import com.example.venteDeVoitures.roomDB.entities.Sell;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;

public class AdvertiserAdvertsActivity extends AppCompatActivity {
    private ActionBar actionBar;
    private MaterialToolbar toolbar;
    private ScrollView scrollView;
    private ClientViewModel viewModel;
    private SearchStateChange searchState;
    private List<LinearLayout> rentalResults;
    private List<LinearLayout> sellResults;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_activity_advertiseradverts);

        toolbar = findViewById(R.id.client_activity_advertiseradverts_toolbar);
        scrollView = findViewById(R.id.client_activity_advertiseradverts_scrollview);
        viewModel = ClientViewModel.getInstance();
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        AppCompatActivity context = this;
        Bundle extras = getIntent().getExtras();

        List<Sell> sells = viewModel.getAdvertiserSells(extras.getInt("id"));
        List<Rental> rentals = viewModel.getAdvertiserRentals(extras.getInt("id"));
        rentalResults = new ArrayList<LinearLayout>();
        sellResults = new ArrayList<LinearLayout>();
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        scrollView.removeAllViews();

        for (Rental r : rentals) {
            LinearLayout ll = new LinearLayout(this);
            ll.setOrientation(LinearLayout.VERTICAL);
            TextView brandTV = new TextView(this);
            brandTV.setText(r.brand);
            ll.addView(brandTV);

            TextView modelTV = new TextView(this);
            modelTV.setText(r.model);
            ll.addView(modelTV);

            TextView distanceTV = new TextView(this);
            distanceTV.setText(r.distance);
            ll.addView(distanceTV);

            TextView yearTV = new TextView(this);
            yearTV.setText(r.year);
            ll.addView(yearTV);

            TextView priceTV = new TextView(this);
            priceTV.setText(r.price);
            ll.addView(priceTV);

            try {
                ImageView image1 = new ImageView(this);
                image1.setImageBitmap(BitmapFactory.decodeByteArray(r.image1, 0, r.image1.length));
                ll.addView(image1);
            }
            catch (NullPointerException e){}

            try {
                ImageView image2 = new ImageView(this);
                image2.setImageBitmap(BitmapFactory.decodeByteArray(r.image2, 0, r.image2.length));
                ll.addView(image2);}
            catch (NullPointerException e){}

            try {
                ImageView image3 = new ImageView(this);
                image3.setImageBitmap(BitmapFactory.decodeByteArray(r.image3, 0, r.image3.length));
                ll.addView(image3);}
            catch (NullPointerException e){}

            try {
                ImageView image4 = new ImageView(this);
                image4.setImageBitmap(BitmapFactory.decodeByteArray(r.image4, 0, r.image4.length));
                ll.addView(image4);}
            catch (NullPointerException e){}

            ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, AdvertDetailsActivity.class);
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

            rentalResults.add(ll);
            linearLayout.addView(ll);
        }

        for (Sell s : sells) {
            LinearLayout ll = new LinearLayout(this);
            ll.setOrientation(LinearLayout.VERTICAL);
            TextView brandTV = new TextView(this);
            brandTV.setText(s.brand);
            ll.addView(brandTV);

            TextView modelTV = new TextView(this);
            modelTV.setText(s.model);
            ll.addView(modelTV);

            TextView distanceTV = new TextView(this);
            distanceTV.setText(s.distance);
            ll.addView(distanceTV);

            TextView yearTV = new TextView(this);
            yearTV.setText(s.year);
            ll.addView(yearTV);

            TextView priceTV = new TextView(this);
            priceTV.setText(s.price);
            ll.addView(priceTV);

            try {
                ImageView image1 = new ImageView(this);
                image1.setImageBitmap(BitmapFactory.decodeByteArray(s.image1, 0, s.image1.length));
                ll.addView(image1);
            }
            catch (NullPointerException e){}

            try {
                ImageView image2 = new ImageView(this);
                image2.setImageBitmap(BitmapFactory.decodeByteArray(s.image2, 0, s.image2.length));
                ll.addView(image2);}
            catch (NullPointerException e){}

            try {
                ImageView image3 = new ImageView(this);
                image3.setImageBitmap(BitmapFactory.decodeByteArray(s.image3, 0, s.image3.length));
                ll.addView(image3);}
            catch (NullPointerException e){}

            try {
                ImageView image4 = new ImageView(this);
                image4.setImageBitmap(BitmapFactory.decodeByteArray(s.image4, 0, s.image4.length));
                ll.addView(image4);}
            catch (NullPointerException e){}

            sellResults.add(ll);
            ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, AdvertDetailsActivity.class);
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

                    intent.putExtra("advertiser_id",s.owner);
                    startActivity(intent);
                }
            });
            linearLayout.addView(ll);
        }
        scrollView.addView(linearLayout);
    }





}
