package com.example.tabletapp;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.tabletapp.session.SessionManager;
import com.example.tabletapp.ui.employee.employeeHomeFragment;
import com.example.tabletapp.ui.employee.employeeSigninFragment;
import com.example.tabletapp.ui.employee.findAvailabilityFragment;
import com.example.tabletapp.ui.employee.myBookingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private SessionManager session;
    private BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, windowInsets) -> {
            Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(insets.left, insets.top, insets.right, insets.bottom);
            return WindowInsetsCompat.CONSUMED;
        });

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

        session = new SessionManager(this);// reads the phone storage for saved login

        // First time the app opens, show sign-in
        if (savedInstanceState == null) {
            if (session.isLoggedIn()){
                showEmployeeHome();
            }else {
                showSignIn();
            }

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