package com.edwardsebastianekasaputra.ajr.role.driver;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.edwardsebastianekasaputra.ajr.LoginActivity;
import com.edwardsebastianekasaputra.ajr.R;
import com.edwardsebastianekasaputra.ajr.auth.UserPreferences;
import com.edwardsebastianekasaputra.ajr.models.User;

public class DriverProfileFragment extends Fragment {

    private UserPreferences userPreferences;

    TextView tvName, tvEmail;
    Button btnLogout;

    public DriverProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_driver_profile, container, false);

        userPreferences = new UserPreferences(requireActivity().getApplicationContext());
        User user = userPreferences.getUserLogin();

        tvName = view.findViewById(R.id.tvName);
        tvName.setText(user.getNama());
        tvEmail = view.findViewById(R.id.tvEmail);
        tvEmail.setText(user.getEmail());
        btnLogout = view.findViewById(R.id.btnLogout);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userPreferences.logout();
                Toast.makeText(getContext(), "Good Bye", Toast.LENGTH_SHORT).show();
                checkLogin();
            }
        });
        // Inflate the layout for this fragment
        return view;
    }

    private void checkLogin(){
        /* check if user login */
        if(!userPreferences.checkLogin()){
            startActivity(new Intent(getContext(), LoginActivity.class));
            requireActivity().finish();
        }
    }
}