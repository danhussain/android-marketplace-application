package com.example.project;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class PostActivity extends AppCompatActivity {

    private ImageView mImageView;
    private EditText mTitle;
    private EditText mDescription;
    private EditText mPrice;

    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        mTitle = (EditText) findViewById(R.id.name);
        mDescription = (EditText) findViewById(R.id.description);
        mPrice = (EditText) findViewById(R.id.price);
        mImageView = (ImageView) findViewById(R.id.imageView);
        Button mTakePictureButton = (Button) findViewById(R.id.take_picture_button);
        mTakePictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });
        Button mSellButton = (Button) findViewById(R.id.sell_button);
        mSellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchPostIntent();
            }
        });
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mImageView.setImageBitmap(imageBitmap);
        }
    }

    private void dispatchPostIntent() {
        Intent intent = new Intent(PostActivity.this, MainActivity.class);
        PostActivity.this.startActivity(intent);
    }
}