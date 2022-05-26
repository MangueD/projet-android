package com.example.venteDeVoitures.client.bottomNav.search;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.venteDeVoitures.R;
import com.example.venteDeVoitures.advertiser.AdvertiserMainActivity;
import com.example.venteDeVoitures.advertiser.SearchStateChange;
import com.example.venteDeVoitures.client.ClientMainActivity;
import com.google.android.material.appbar.MaterialToolbar;

public class SearchingFragmentClient extends Fragment {
    private SearchStateChange searchState;
    private ActionBar actionBar;
    private Button button;
    private EditText brandEt;
    private EditText modelEt;
    private ResultFragmentClient resultFragment;
    private MaterialToolbar toolbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.client_fragment_search_searching, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        searchState = (ClientMainActivity)getActivity();

        toolbar = view.findViewById(R.id.client_fragment_search_searching_toolbar);

        button = view.findViewById(R.id.client_fragment_search_searching_confirmbt);
        brandEt = view.findViewById(R.id.client_fragment_search_searching_brandet);
        modelEt = view.findViewById(R.id.client_fragment_search_searching_modelet);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultFragment.setArgs(brandEt.getText().toString(), modelEt.getText().toString());
                searchState.setSearchState(AdvertiserMainActivity.RESULT);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        ((ClientMainActivity) getActivity()).setSupportActionBar(toolbar);
        actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }


    public void setResultFragment(ResultFragmentClient r){
        this.resultFragment = r;
    }
}