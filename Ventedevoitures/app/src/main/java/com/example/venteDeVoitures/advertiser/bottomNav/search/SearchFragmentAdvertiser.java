package com.example.venteDeVoitures.advertiser.bottomNav.search;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.venteDeVoitures.R;
import com.example.venteDeVoitures.advertiser.AdvertiserMainActivity;
import com.example.venteDeVoitures.advertiser.SearchStateChange;

public class SearchFragmentAdvertiser extends Fragment implements View.OnClickListener {
    private SearchStateChange searchState;
    private ResultFragmentAdvertiser resultFragment;
    private Button researchBt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.advertiser_fragment_search, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle bundle){
        searchState = (AdvertiserMainActivity)getActivity();
        researchBt = view.findViewById(R.id.advertiser_fragment_search_button);
        researchBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchState.setSearchState(AdvertiserMainActivity.SEARCHING);
            }
        });
        view.findViewById(R.id.advertiser_fragment_search_toyota).setOnClickListener((View.OnClickListener) this);
        view.findViewById(R.id.advertiser_fragment_search_audi).setOnClickListener((View.OnClickListener) this);
        view.findViewById(R.id.advertiser_fragment_search_tesla).setOnClickListener((View.OnClickListener) this);
        view.findViewById(R.id.advertiser_fragment_search_bugatti).setOnClickListener((View.OnClickListener) this);
        view.findViewById(R.id.advertiser_fragment_search_peugeot).setOnClickListener((View.OnClickListener) this);
        view.findViewById(R.id.advertiser_fragment_search_renault).setOnClickListener((View.OnClickListener) this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case(R.id.advertiser_fragment_search_toyota):
                resultFragment.setArgs("Toyota", "");
                searchState.setSearchState(AdvertiserMainActivity.RESULT);
                break;
            case(R.id.advertiser_fragment_search_audi):
                resultFragment.setArgs("Audi", "");
                searchState.setSearchState(AdvertiserMainActivity.RESULT);
                break;
            case(R.id.advertiser_fragment_search_tesla):
                resultFragment.setArgs("Tesla", "");
                searchState.setSearchState(AdvertiserMainActivity.RESULT);
                break;
            case(R.id.advertiser_fragment_search_bugatti):
                resultFragment.setArgs("Bugatti", "");
                searchState.setSearchState(AdvertiserMainActivity.RESULT);
                break;
            case(R.id.advertiser_fragment_search_peugeot):
                resultFragment.setArgs("Peugeot", "");
                searchState.setSearchState(AdvertiserMainActivity.RESULT);
                break;
            case(R.id.advertiser_fragment_search_renault):
                resultFragment.setArgs("Renault", "");
                searchState.setSearchState(AdvertiserMainActivity.RESULT);
                break;
        }
    }

    public void setResultFragment(ResultFragmentAdvertiser r){
        this.resultFragment = r;
    }
}