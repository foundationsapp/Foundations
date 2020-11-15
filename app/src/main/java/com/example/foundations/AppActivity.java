package com.example.foundations;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.io.File;
import java.util.List;

public class AppActivity extends AppCompatActivity implements FragmentSwitcher {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    private MainViewModel mainViewModel;
    String lName, fName, License, Company, email, phone;
    int profileid;
    Uri contentUri;

    private final static String TAG = AppActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_page);
        Intent intent = getIntent();
        Profile currentProfile = intent.getParcelableExtra(String.valueOf(R.string.userProfile));
        Log.d(TAG, "onCreate: " + currentProfile.getFullName());
        MainViewModel mainViewModel = new ViewModelProvider((ViewModelStoreOwner) this).get(MainViewModel.class);

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
        String profile_pic_path = "/sdcard/DCIM/Camera/" + currentProfile.getEmail()+ ".jpg";
        navigationView = findViewById(R.id.navigation_view);

        View header_view = navigationView.getHeaderView(0);

        File file = new File(profile_pic_path);

        contentUri= Uri.fromFile(file);
        Bitmap bitmap = BitmapFactory.decodeFile(contentUri.getPath());
        float degree = getDegree();
        System.out.println(degree);

        Bitmap bitmap2 = rotateBitmap(bitmap, degree);


        header_view.setBackgroundResource(R.drawable.logo);
        ImageView header_image = (ImageView)header_view.findViewById(R.id.profile_picture);
        header_image.setImageBitmap(bitmap2);

        //user_name & user_email

        TextView user_name = (TextView)header_view.findViewById(R.id.user_name);
        TextView user_email = (TextView)header_view.findViewById(R.id.user_email);

        String uName = currentProfile.getFullName();
        String uEmail = currentProfile.getEmail();

        user_name.setText(uName);
        user_email.setText(uEmail);

        user_name.setTextColor(Color.BLACK);
        user_email.setTextColor(Color.BLACK);
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
                    case R.id.logout:
                        startActivity(new Intent(AppActivity.this, MainActivity.class));
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

    public Bitmap rotateBitmap(Bitmap bitmap, float degree){
        try{
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();

            Matrix matrix = new Matrix();

            matrix.postRotate(degree);
            Bitmap resizeBitmap = Bitmap.createBitmap(bitmap, 0, 0,width, height, matrix, true);

            bitmap.recycle();
            return resizeBitmap;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public float getDegree(){
        try{
            ExifInterface exif = new ExifInterface(contentUri.getPath());
            int degree = 0;

            int ori = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, -1);
            switch (ori){
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;

            }
            return (float)degree;
        }catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }
}
