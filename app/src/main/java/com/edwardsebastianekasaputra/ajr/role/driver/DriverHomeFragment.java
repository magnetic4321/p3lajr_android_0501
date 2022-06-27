package com.edwardsebastianekasaputra.ajr.role.driver;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edwardsebastianekasaputra.ajr.R;
import com.edwardsebastianekasaputra.ajr.auth.UserPreferences;
import com.edwardsebastianekasaputra.ajr.models.User;

public class DriverHomeFragment extends Fragment {

    private UserPreferences userPreferences;
    private User user;

    public DriverHomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_driver_home, container, false);
        userPreferences = new UserPreferences(getActivity().getApplicationContext());
        user = userPreferences.getUserLogin();

        return view;
    }
}