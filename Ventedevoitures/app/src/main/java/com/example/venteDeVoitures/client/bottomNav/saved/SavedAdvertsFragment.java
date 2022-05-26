package com.example.venteDeVoitures.client.bottomNav.saved;

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
import com.example.venteDeVoitures.advertiser.bottomNav.myadverts.EditRentalActivity;
import com.example.venteDeVoitures.client.ClientViewModel;
import com.example.venteDeVoitures.client.bottomNav.AdvertDetailsActivity;
import com.example.venteDeVoitures.roomDB.entities.Rental;
import com.example.venteDeVoitures.roomDB.entities.Saved;
import com.example.venteDeVoitures.roomDB.entities.Sell;

import java.util.List;


public class SavedAdvertsFragment extends Fragment {
    RelativeLayout relativeLayout;
    ClientViewModel viewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewModel = ClientViewModel.getInstance();
        return inflater.inflate(R.layout.client_fragment_savedadverts, container, false);
    }

    @Override
    public void onViewCreated(View v, Bundle b){
        relativeLayout = v.findViewById(R.id.client_fragment_savedadverts_relativelayout);
    }

    @Override
    public void onResume(){
        super.onResume();
        relativeLayout.removeAllViews();
        if(!viewModel.isConnected()){
            TextView textView = new TextView(getActivity());
            textView.setText("you must be connected to use this service");
            relativeLayout.addView(textView);
            return;
        }

        List<Saved> saveds = viewModel.getUserSaved();
        if(saveds != null){
            LinearLayout linearLayoutl = new LinearLayout(getActivity());
            linearLayoutl.setOrientation(LinearLayout.VERTICAL);
            ScrollView scrollView = new ScrollView(getActivity());
            scrollView.addView(linearLayoutl);
            int i = 0;
            for(Saved s : saveds) {
                if (s.advertType.equals("rental")) {
                    Rental rental = viewModel.getRentalById(s.advertID);
                    LinearLayout ll = new LinearLayout(getActivity());
                    ll.setOrientation(LinearLayout.VERTICAL);
                    TextView tv1 = new TextView(getActivity());
                    tv1.setText("Marque :");
                    ll.addView(tv1);
                    TextView brandTV = new TextView(getActivity());
                    brandTV.setText(rental.brand);
                    ll.addView(brandTV);


                    TextView tv2 = new TextView(getActivity());
                    tv2.setText("Modele :");
                    ll.addView(tv2);
                    TextView modelTV = new TextView(getActivity());
                    modelTV.setText(rental.model);
                    ll.addView(modelTV);

                    TextView tv3 = new TextView(getActivity());
                    tv3.setText("Distance :");
                    ll.addView(tv3);
                    TextView distanceTV = new TextView(getActivity());
                    distanceTV.setText(rental.distance);
                    ll.addView(distanceTV);


                    TextView tv4 = new TextView(getActivity());
                    tv4.setText("Date :");
                    ll.addView(tv4);
                    TextView yearTV = new TextView(getActivity());
                    yearTV.setText(rental.year);
                    ll.addView(yearTV);

                    TextView tv5= new TextView(getActivity());
                    tv5.setText("Distance :");
                    ll.addView(tv5);
                    TextView priceTV = new TextView(getActivity());
                    priceTV.setText(rental.price);
                    ll.addView(priceTV);

                    try {
                        ImageView image1 = new ImageView(getActivity());
                        image1.setImageBitmap(BitmapFactory.decodeByteArray(rental.image1, 0, rental.image1.length));
                        ll.addView(image1);
                    } catch (NullPointerException e) {
                    }

                    try {
                        ImageView image2 = new ImageView(getActivity());
                        image2.setImageBitmap(BitmapFactory.decodeByteArray(rental.image2, 0, rental.image2.length));
                        ll.addView(image2);
                    } catch (NullPointerException e) {
                    }

                    try {
                        ImageView image3 = new ImageView(getActivity());
                        image3.setImageBitmap(BitmapFactory.decodeByteArray(rental.image3, 0, rental.image3.length));
                        ll.addView(image3);
                    } catch (NullPointerException e) {
                    }

                    try {
                        ImageView image4 = new ImageView(getActivity());
                        image4.setImageBitmap(BitmapFactory.decodeByteArray(rental.image4, 0, rental.image4.length));
                        ll.addView(image4);
                    } catch (NullPointerException e) {
                    }

                    ll.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), AdvertDetailsActivity.class);
                            intent.putExtra("type", "rental");
                            intent.putExtra("id", rental.id);
                            intent.putExtra("brand", rental.brand);
                            intent.putExtra("model", rental.model);
                            intent.putExtra("distance", rental.distance);
                            intent.putExtra("year", rental.year);
                            intent.putExtra("price", rental.price);
                            intent.putExtra("image1", rental.image1);
                            intent.putExtra("image2", rental.image2);
                            intent.putExtra("image3", rental.image3);
                            intent.putExtra("image4", rental.image4);
                            intent.putExtra("via_email", rental.email);
                            intent.putExtra("via_phone", rental.phone);
                            intent.putExtra("advertiser_id", rental.id_owner);
                            startActivity(intent);
                        }
                    });

                    Button deleteBT = new Button(getActivity());
                    deleteBT.setText("Delete");
                    deleteBT.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            viewModel.deleteSaved(s);
                            linearLayoutl.removeView(ll);
                            linearLayoutl.removeView(deleteBT);
                        }
                    });
                    linearLayoutl.addView(ll);

                    linearLayoutl.addView(deleteBT);
                }

                if (s.advertType.equals("sell")) {
                    Sell sell = viewModel.getSellById(s.advertID);
                    LinearLayout ll = new LinearLayout(getActivity());
                    ll.setOrientation(LinearLayout.VERTICAL);
                    TextView tv1 = new TextView(getActivity());
                    tv1.setText("Marque :");
                    ll.addView(tv1);
                    TextView brandTV = new TextView(getActivity());
                    brandTV.setText(sell.brand);
                    ll.addView(brandTV);


                    TextView tv2 = new TextView(getActivity());
                    tv2.setText("Modele :");
                    ll.addView(tv2);
                    TextView modelTV = new TextView(getActivity());
                    modelTV.setText(sell.model);
                    ll.addView(modelTV);

                    TextView tv3 = new TextView(getActivity());
                    tv3.setText("Distance :");
                    ll.addView(tv3);
                    TextView distanceTV = new TextView(getActivity());
                    distanceTV.setText(sell.distance);
                    ll.addView(distanceTV);


                    TextView tv4 = new TextView(getActivity());
                    tv4.setText("Date :");
                    ll.addView(tv4);
                    TextView yearTV = new TextView(getActivity());
                    yearTV.setText(sell.year);
                    ll.addView(yearTV);

                    TextView tv5= new TextView(getActivity());
                    tv5.setText("Distance :");
                    ll.addView(tv5);
                    TextView priceTV = new TextView(getActivity());
                    priceTV.setText(sell.price);
                    ll.addView(priceTV);

                    try {
                        ImageView image1 = new ImageView(getActivity());
                        image1.setImageBitmap(BitmapFactory.decodeByteArray(sell.image1, 0, sell.image1.length));
                        ll.addView(image1);
                    } catch (NullPointerException e) {
                    }

                    try {
                        ImageView image2 = new ImageView(getActivity());
                        image2.setImageBitmap(BitmapFactory.decodeByteArray(sell.image2, 0, sell.image2.length));
                        ll.addView(image2);
                    } catch (NullPointerException e) {
                    }

                    try {
                        ImageView image3 = new ImageView(getActivity());
                        image3.setImageBitmap(BitmapFactory.decodeByteArray(sell.image3, 0, sell.image3.length));
                        ll.addView(image3);
                    } catch (NullPointerException e) {
                    }

                    try {
                        ImageView image4 = new ImageView(getActivity());
                        image4.setImageBitmap(BitmapFactory.decodeByteArray(sell.image4, 0, sell.image4.length));
                        ll.addView(image4);
                    } catch (NullPointerException e) {
                    }

                    ll.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), AdvertDetailsActivity.class);
                            intent.putExtra("type", "rental");
                            intent.putExtra("id", sell.id);
                            intent.putExtra("brand", sell.brand);
                            intent.putExtra("model", sell.model);
                            intent.putExtra("distance", sell.distance);
                            intent.putExtra("year", sell.year);
                            intent.putExtra("price", sell.price);
                            intent.putExtra("image1", sell.image1);
                            intent.putExtra("image2", sell.image2);
                            intent.putExtra("image3", sell.image3);
                            intent.putExtra("image4", sell.image4);
                            intent.putExtra("via_email", sell.email);
                            intent.putExtra("via_phone", sell.phone);
                            intent.putExtra("advertiser_id", sell.owner);
                            startActivity(intent);
                        }
                    });

                    Button deleteBT = new Button(getActivity());

                    deleteBT.setText("Delete");
                    deleteBT.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            viewModel.deleteSaved(s);
                            linearLayoutl.removeView(ll);
                            linearLayoutl.removeView(deleteBT);
                        }
                    });
                    linearLayoutl.addView(ll);
                    linearLayoutl.addView(deleteBT);


                }
            }

            relativeLayout.addView(scrollView);
            scrollView.getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;
            scrollView.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;

        }
    }
}