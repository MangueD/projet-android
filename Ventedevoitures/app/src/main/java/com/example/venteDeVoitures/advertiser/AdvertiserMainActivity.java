package com.example.venteDeVoitures.advertiser;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.example.venteDeVoitures.R;
import com.example.venteDeVoitures.advertiser.bottomNav.home.HomeFragmentAdvertiser;
import com.example.venteDeVoitures.advertiser.bottomNav.myadverts.MyAdvertsFragment;
import com.example.venteDeVoitures.advertiser.bottomNav.search.ResultFragmentAdvertiser;
import com.example.venteDeVoitures.advertiser.bottomNav.search.SearchFragmentAdvertiser;
import com.example.venteDeVoitures.advertiser.bottomNav.search.SearchingFragmentAdvertiser;
import com.example.venteDeVoitures.roomDB.VenteDeVoitureDatabase;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class AdvertiserMainActivity extends AppCompatActivity implements SearchStateChange {
    private AdvertiserViewModel advertiserViewModel;
    private BottomNavigationView bottomNavigationView;

    private HomeFragmentAdvertiser homeFragment;
    private MyAdvertsFragment myAdvertsFragment;

    //related to search
    public static final int SEARCH = 0;
    public static final int SEARCHING = 1;
    public static final int RESULT = 2;
    private static final int SEARCH_SIZE = 3;
    private int currentSearchState;
    private Fragment[] searchState;

    private FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        System.out.println("Main created");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.advertiser_activity_main);
        homeFragment = new HomeFragmentAdvertiser();
        myAdvertsFragment = new MyAdvertsFragment();

        searchState = new Fragment[SEARCH_SIZE];
        searchState[SEARCH] = new SearchFragmentAdvertiser();
        searchState[SEARCHING] = new SearchingFragmentAdvertiser();
        searchState[RESULT] = new ResultFragmentAdvertiser();
        currentSearchState = SEARCH;
        ((SearchFragmentAdvertiser)searchState[SEARCH]).setResultFragment((ResultFragmentAdvertiser) searchState[RESULT]);
        ((SearchingFragmentAdvertiser)searchState[SEARCHING]).setResultFragment((ResultFragmentAdvertiser) searchState[RESULT]);

        fragmentManager = getSupportFragmentManager();

        bottomNavigationView = findViewById(R.id.advertiser_bottom_navigation);
        advertiserViewModel = new ViewModelProvider(this).get(AdvertiserViewModel.class);
        advertiserViewModel.setArgs(VenteDeVoitureDatabase.getInstance(this), getIntent().getIntExtra("userID", -1));

        fragmentManager.beginTransaction().replace(R.id.advertiser_container, homeFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch(item.getItemId()){
                    case R.id.advertiser_item_home:{
                        fragmentManager.beginTransaction().replace(R.id.advertiser_container, homeFragment).commit();
                        return true;
                    }
                    case R.id.advertiser_item_search:{
                        fragmentManager.beginTransaction().replace(R.id.advertiser_container, searchState[currentSearchState], "search").commit();
                        return true;
                    }
                    case R.id.advertiser_item_myadverts:{
                        fragmentManager.beginTransaction().replace(R.id.advertiser_container, myAdvertsFragment).commit();
                        return true;
                    }
                }
                return false;
            }
        });
    }

    @Override
    public void setSearchState(int state){
        switch (state){
            case(SEARCH):
                currentSearchState = SEARCH;
                break;
            case(SEARCHING):
                currentSearchState = SEARCHING;
                break;
            case(RESULT):
                currentSearchState = RESULT;
                break;
            default:
                return;
        }
        fragmentManager.beginTransaction().replace(R.id.advertiser_container, searchState[currentSearchState]).commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Fragment f = fragmentManager.findFragmentById(R.id.advertiser_container);
        if (menuItem.getItemId() == android.R.id.home && f.getClass() == ResultFragmentAdvertiser.class) {
            setSearchState(SEARCH);
            return true;
        }
        if (menuItem.getItemId() == android.R.id.home && f.getClass() == SearchingFragmentAdvertiser.class) {
            setSearchState(SEARCH);
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

}
