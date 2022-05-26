package com.example.venteDeVoitures.advertiser.bottomNav.myadverts;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

import com.example.venteDeVoitures.R;

public class MyAdvertsFragment extends Fragment {
    SellsFragment sellsFragment;
    AddFragment addFragment;
    RentalsFragment rentalsFragment;
    FragmentManager fragmentManager;
    TabLayout tableLayout;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sellsFragment = new SellsFragment();
        addFragment = new AddFragment();
        rentalsFragment = new RentalsFragment();
        fragmentManager = getChildFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.advertiser_container_myadverts, addFragment).commit();

        tableLayout = (TabLayout) view.findViewById(R.id.advertiser_tab_layout);
        tableLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch(tab.getPosition()){
                    case(0):{
                        fragmentManager.beginTransaction().replace(R.id.advertiser_container_myadverts, addFragment).commit();
                        break;
                    }
                    case(1):{
                        fragmentManager.beginTransaction().replace(R.id.advertiser_container_myadverts, rentalsFragment).commit();
                        break;
                    }
                    case(2):{
                        fragmentManager.beginTransaction().replace(R.id.advertiser_container_myadverts, sellsFragment).commit();
                        break;
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.advertiser_fragment_my_adverts, container, false);
    }
}