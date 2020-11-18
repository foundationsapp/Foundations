package com.example.foundations;

import androidx.fragment.app.Fragment;

public interface FragmentSwitcher {
    void loadFragment(Fragment fragment);
    Profile getProfile();
    Report getCurrentReport();
    void setCurrentReport(Report report);
}
