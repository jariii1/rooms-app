package com.example.tabletapp;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNav = findViewById(R.id.bottomNav);
        bottomNav.inflateMenu(R.menu.menu_employee);

        // When a bottom tab is tapped, show that screen
        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home)     { openScreen(new employeeHomeFragment(), false); return true; }
            if (id == R.id.nav_rooms)    { openScreen(new findAvailabilityFragment(), false); return true; }
            if (id == R.id.nav_bookings) { openScreen(new myBookingsFragment(), false); return true; }
            return false;
        });

        // First time the app opens, show sign-in
        if (savedInstanceState == null) {
            showSignIn();
        }
    }

    // Puts a fragment into the container. Pass true to let the Back button return to the previous screen.
    public void openScreen(Fragment fragment, boolean addToBackStack) {
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragmentContainer, fragment);
        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

    public void showSignIn() {
        bottomNav.setVisibility(View.GONE); // hide the bottom bar until the user signs in
        openScreen(new employeeSigninFragment(), false);
    }

    public void showEmployeeHome() {
        bottomNav.setVisibility(View.VISIBLE);
        bottomNav.setSelectedItemId(R.id.nav_home);
        openScreen(new employeeHomeFragment(), false);
    }

    // Used for screens you go deeper into, like Find a room
    public void openDetail(Fragment fragment) {
        openScreen(fragment, true);
    }
}