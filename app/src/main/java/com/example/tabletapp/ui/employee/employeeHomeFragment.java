package com.example.tabletapp.ui.employee;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.tabletapp.concurrency.AppExecutors;
import com.example.tabletapp.MainActivity;
import com.example.tabletapp.R;

public class employeeHomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_employee_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MainActivity activity = (MainActivity) requireActivity();

        view.findViewById(R.id.btnFindRoom).setOnClickListener(v ->
                activity.openDetail(new findAvailabilityFragment()));

        view.findViewById(R.id.btnMyBookings).setOnClickListener(v ->
                activity.openDetail(new myBookingsFragment()));

        AppExecutors.getInstance().runOnWorker(() -> {

            // pausing this background thread for 2 seconds
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ignored) {
                // Sleep can be interrupted if the thread is stopped early.

            }
            AppExecutors.getInstance().runOnMain(() -> {
                if (isAdded()) {
                    Toast.makeText(requireContext(), "Task finished", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}