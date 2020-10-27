package com.example.foundations;

import androidx.appcompat.app.AppCompatActivity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.Gravity;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_TEXT = "com.example.profile.EXTRA_TEXT";
    public static final String EXTRA_NUMBER = "com.example.profile.EXTRA_NUMBER";
    public static final String EXTRA_TEXT_TWO = "com.example.profile.EXTRA_TEXT_TWO";
    public static final String EXTRA_TEXT_THREE = "com.example.profile.EXTRA_TEXT_THREE";
    public static final String EXTRA_TEXT_FOUR    = "com.example.profile.EXTRA_TEXT_FOUR";
    public static final String EXTRA_TEXT_FIVE      = "com.example.profile.EXTRA_TEXT_FIVE";
    private static final int BOTTOM = 0;
    // public static final String EXTRA_MY_IMAGE_DESC      = "com.example.profile.EXTRA_MY_IMAGE_DESC";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast t = Toast.makeText(getApplicationContext(),"NEW INSPECTION!",
                        Toast.LENGTH_LONG);
                t.setGravity(BOTTOM | Gravity.END, 0,0);
                t.show();

                openActivity2();

            }
        });

    }

    // Toast.makeText(getApplicationContext(), "Welcome - Thanks for Signing Up", Toast.LENGTH_LONG).show();

    public void openActivity2(){

        Intent intent = new Intent(this,Activity2.class);

        startActivity(intent);
    }
}
