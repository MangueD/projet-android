package com.example.venteDeVoitures.client;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.venteDeVoitures.R;
import com.example.venteDeVoitures.advertiser.SearchStateChange;
import com.example.venteDeVoitures.advertiser.loginRegister.SwitchLoginRegister;
import com.example.venteDeVoitures.client.bottomNav.newAdverts.NewFragmentClient;
import com.example.venteDeVoitures.client.bottomNav.profile.LoginFragmentClient;
import com.example.venteDeVoitures.client.bottomNav.profile.ProfileFragment;
import com.example.venteDeVoitures.client.bottomNav.profile.RegisterFragmentClient;
import com.example.venteDeVoitures.client.bottomNav.saved.SavedAdvertsFragment;
import com.example.venteDeVoitures.client.bottomNav.search.ResultFragmentClient;
import com.example.venteDeVoitures.client.bottomNav.search.SearchFragmentClient;
import com.example.venteDeVoitures.client.bottomNav.search.SearchingFragmentClient;
import com.example.venteDeVoitures.roomDB.VenteDeVoitureDatabase;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class ClientMainActivity extends AppCompatActivity implements SearchStateChange, SwitchLoginRegister {
    private BottomNavigationView bottomNavigationView;
    private NewFragmentClient newFragment;
    private SavedAdvertsFragment savedAdvertsFragment;
    private ClientViewModel clientViewModel;
    public static final int SEARCH = 0;
    public static final int SEARCHING = 1;
    public static final int RESULT = 2;
    public static final int SEARCH_SIZE = 3;
    public static final int LOGIN = 0;
    public static final int REGISTER = 1;
    public static final int CONNECTED = 2;
    private int currentLoginState;
    private int currentSearchState;
    private Fragment[] searchState;
    private Fragment[] loginState;

    ProfileFragment notificationFragment;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_activity_main);
        newFragment = new NewFragmentClient();

        savedAdvertsFragment = new SavedAdvertsFragment();
        searchState = new Fragment[SEARCH_SIZE];
        searchState[SEARCH] = new SearchFragmentClient();
        searchState[SEARCHING] = new SearchingFragmentClient();
        searchState[RESULT] = new ResultFragmentClient();
        currentSearchState = SEARCH;
        ((SearchFragmentClient)searchState[SEARCH]).setResultFragment((ResultFragmentClient) searchState[RESULT]);
        ((SearchingFragmentClient)searchState[SEARCHING]).setResultFragment((ResultFragmentClient) searchState[RESULT]);

        loginState = new Fragment[2];
        loginState[LOGIN] = new LoginFragmentClient();
        loginState[REGISTER] = new RegisterFragmentClient();
        currentLoginState = LOGIN;
        ((LoginFragmentClient)loginState[LOGIN]).setInterface((SwitchLoginRegister) this);
        ((RegisterFragmentClient)loginState[REGISTER]).setInterface((SwitchLoginRegister) this);
        notificationFragment = new ProfileFragment();


        fragmentManager = getSupportFragmentManager();
        bottomNavigationView = findViewById(R.id.client_bottom_navigation);

        clientViewModel = new ViewModelProvider(this).get(ClientViewModel.class);
        clientViewModel.setArgs(VenteDeVoitureDatabase.getInstance(this));
        newFragment.setArgs(clientViewModel);

        fragmentManager.beginTransaction().replace(R.id.client_container, newFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch(item.getItemId()){
                    case R.id.client_item_home:{
                        fragmentManager.beginTransaction().replace(R.id.client_container, newFragment).commit();
                        return true;
                    }
                    case R.id.client_item_search:{
                        fragmentManager.beginTransaction().replace(R.id.client_container, searchState[currentSearchState]).commit();
                        return true;
                    }
                    case R.id.client_item_savedadverts:{
                        fragmentManager.beginTransaction().replace(R.id.client_container, savedAdvertsFragment).commit();
                        return true;
                    }
                    case R.id.client_item_notification:{
                        if(clientViewModel.isConnected())
                            fragmentManager.beginTransaction().replace(R.id.client_container, notificationFragment).commit();
                        else{
                            fragmentManager.beginTransaction().replace(R.id.client_container, loginState[currentLoginState]).commit();
                        }
                        return true;
                    }
                }
                return false;
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Fragment f = fragmentManager.findFragmentById(R.id.client_container);
        if (menuItem.getItemId() == android.R.id.home && f.getClass() == ResultFragmentClient.class) {
            setSearchState(SEARCH);
            return true;
        }
        if (menuItem.getItemId() == android.R.id.home && f.getClass() == SearchingFragmentClient.class) {
            setSearchState(SEARCH);
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
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
        fragmentManager.beginTransaction().replace(R.id.client_container, searchState[currentSearchState]).commit();
    }

    @Override
    public void callBack(int c) {
        switch (c){
            case(LOGIN):
                currentLoginState = LOGIN;
                break;
            case(REGISTER):
                currentLoginState = REGISTER;
                break;
            case(CONNECTED):
                fragmentManager.beginTransaction().replace(R.id.client_container, notificationFragment).commit();
            default:
                return;
        }
        fragmentManager.beginTransaction().replace(R.id.client_container, loginState[currentLoginState]).commit();
    }
}