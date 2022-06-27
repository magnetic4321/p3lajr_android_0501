package com.edwardsebastianekasaputra.ajr.role.driver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.edwardsebastianekasaputra.ajr.R;
import com.edwardsebastianekasaputra.ajr.auth.UserPreferences;
import com.edwardsebastianekasaputra.ajr.models.User;
import com.edwardsebastianekasaputra.ajr.role.customer.CustomerHomeFragment;
import com.edwardsebastianekasaputra.ajr.role.customer.CustomerProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DriverActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigation;

    TextView tvName, tvEmail;
    Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);

        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        openFragment(new DriverHomeFragment());

        tvName = findViewById(R.id.tvName);
        tvEmail = findViewById(R.id.tvEmail);
        btnLogout = findViewById(R.id.btnLogout);
    }

    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()){
                        case R.id.driver_home:
                            openFragment(new DriverHomeFragment());
                            return true;
                        case R.id.driver_profile:
                            openFragment(new DriverProfileFragment());
                            return true;
                    }
                    return false;
                }
            };

    public void openFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.layout_fragment_driver,fragment);
        transaction.commit();
    }
}