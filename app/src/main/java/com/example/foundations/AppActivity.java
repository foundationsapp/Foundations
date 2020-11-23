package com.example.foundations;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

public class AppActivity extends AppCompatActivity implements FragmentSwitcher {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    private MainViewModel mainViewModel;
    String lName, fName, License, Company, email, phone;

    private final static String TAG = AppActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_page);
        Intent intent = getIntent();
        Profile currentProfile = intent.getParcelableExtra(String.valueOf(R.string.userProfile));
        Log.d(TAG, "onCreate: " + currentProfile.getFullName());
//        RecyclerView reportRecyclerView = findViewById(R.id.report_recyclerview);
//        final ReportAdapter reportAdapter = new ReportAdapter(this);
//        reportRecyclerView.setAdapter(reportAdapter);
//        reportRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mainViewModel = new ViewModelProvider((ViewModelStoreOwner) this).get(MainViewModel.class);
//        mainViewModel.getAllReports().observe(this, reportAdapter::setReports);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        toggle = new ActionBarDrawerToggle( this,drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                Fragment fragment = null;
                switch (id){
                    case R.id.profile:
                        fragment=new ProfileFragment();
                        loadFragment(fragment);
                        Log.i("fragment","Click profile "+ currentProfile.getEmail());
                        lName = currentProfile.getLastName();
                        fName = currentProfile.getFirstName();
                        License = currentProfile.getLicenseNumber();
                        Company = currentProfile.getCompanyName();
                        email = currentProfile.getEmail();
                        phone = currentProfile.getPhone();
                        break;
                    case R.id.inspections:
                        fragment=new InspectionFragment(AppActivity.this);
                        loadFragment(fragment);
                        break;
                    case R.id.checklists:
                        fragment=new CheckListsFragment();
                        loadFragment(fragment);
                        break;
                    case R.id.pdfs:
                        fragment=new PDFsFragment();
                        loadFragment(fragment);
                        break;
                    case R.id.settings:
                        fragment=new SettingsFragment();
                        loadFragment(fragment);
                        break;
                    default:
                        return true;
                }
                return true;
            }
        });
        Fragment dashFragment = new DashFragment();
        loadFragment(dashFragment);
    }

    public void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment).commit();
        drawerLayout.closeDrawer(GravityCompat.START);
        fragmentTransaction.addToBackStack(null);

    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        if(toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
