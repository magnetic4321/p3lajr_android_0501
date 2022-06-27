package com.edwardsebastianekasaputra.ajr.role.customer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.edwardsebastianekasaputra.ajr.R;
import com.edwardsebastianekasaputra.ajr.auth.UserPreferences;
import com.edwardsebastianekasaputra.ajr.databinding.FragmentCustomerHomeBinding;
import com.edwardsebastianekasaputra.ajr.databinding.FragmentCustomerProfileBinding;
import com.edwardsebastianekasaputra.ajr.models.User;

public class CustomerHomeFragment extends Fragment {

    private UserPreferences userPreferences;
    private User user;
    FragmentCustomerHomeBinding binding;

    Button btnPromo, btnBrosur;

    public CustomerHomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_customer_home, container, false);
        userPreferences = new UserPreferences(getActivity().getApplicationContext());
        user = userPreferences.getUserLogin();

        btnPromo = view.findViewById(R.id.btnPromo);
        btnBrosur = view.findViewById(R.id.btnBrosur);

        btnPromo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://192.168.1.8:8000/api/promos");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        btnBrosur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://192.168.1.8:8000/api/mobils");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        return view;
    }
}