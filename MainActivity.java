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
                Toast t = Toast.makeText(getApplicationContext(),"Thank you for adding another inspector!",
                        Toast.LENGTH_LONG);
                t.setGravity(BOTTOM | Gravity.END, 0,0);
                t.show();

                openActivity2();

            }
        });

    }

    // Toast.makeText(getApplicationContext(), "Welcome - Thanks for Signing Up", Toast.LENGTH_LONG).show();

    public void openActivity2(){

        EditText editText1 = findViewById(R.id.editText1);
        String text1 = editText1.getText().toString();

        EditText editText2 = findViewById(R.id.editText2);
        int number = Integer.parseInt(editText2.getText().toString());

        EditText editText3 = findViewById(R.id.editText3);
        String text3 = editText3.getText().toString();

        EditText editText4 = findViewById(R.id.editText4);
        String text4 = editText4.getText().toString();

        EditText editText5 = findViewById(R.id.editText5);
        String text5 = editText5.getText().toString();

        EditText editText6 = findViewById(R.id.editText6);
        String text6 = editText6.getText().toString();

        // ImageView imageView = (ImageView)findViewById(R.id.imageView);
        // String image = imageView.getImage(src="@drawable/my_image")


        Intent intent = new Intent(this,Activity2.class);

        intent.putExtra(EXTRA_TEXT,text1);
        intent.putExtra(EXTRA_NUMBER,number);
        intent.putExtra(EXTRA_TEXT_TWO,text3);
        intent.putExtra(EXTRA_TEXT_THREE,text4);
        intent.putExtra(EXTRA_TEXT_FOUR,text5);
        intent.putExtra(EXTRA_TEXT_FIVE,text6);
        // intent.putExtra(EXTRA__MY_IMAGE_DESC);

        startActivity(intent);
    }
}
