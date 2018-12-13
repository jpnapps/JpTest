package com.jpndev.jpmusicplayer.room;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class AddStudent extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 234;
    private Uri filePath;
    ImageView imageView1,imageView2,imageView3;
    String studentid;
    EditText etid,etname,etaddress;
    String gender;
   // ArrayList<String> lo cation ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* setContentView(R.layout.activity_add_student);

        imageView1 = (ImageView) findViewById(R.id.imageViewpostimage1);
        imageView2 = (ImageView) findViewById(R.id.imageViewpostimage2);
        imageView3 = (ImageView) findViewById(R.id.imageViewpostimage3);

        etid=(EditText) findViewById(R.id.etid);

        etname=(EditText) findViewById(R.id.etname);

        etaddress=(EditText) findViewById(R.id.etaddress);*/
    }

/*    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_female:
                if (checked)
                    gender = "female";
                break;
            case R.id.radio_male:
                if (checked)
                    gender = "male";
                break;
        }
    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();
        location = new ArrayList<>();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.checkbox_town:
                if (checked)
                    location.add("town");
                else
                    location.remove("town");
                break;
            case R.id.checkbox_village:
                if (checked)
                    location.add("village");
                else
                    location.remove("village");
                break;
            // TODO: Veggie sandwich
        }
    }*/

    public void dosavestudent(View view) {

        uploadFile1();
    }

    private void uploadFile() {
        int id = Integer.parseInt(etid.getText().toString().trim());
        String name= etname.getText().toString().trim();
        String address=etaddress.getText().toString().trim();
        String location1;
      //  location1=location.get(0);

        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAddress(address);
        student.setGender(gender);
       // student.setLocation(location1);

        MainActivity.appDatabase.studentDao().addstudent(student);
        Toast.makeText(this, " Student added", Toast.LENGTH_SHORT).show();


        Intent intent = new Intent(AddStudent.this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }

    public byte[] convertImageToByte(Uri uri){
        byte[] data = null;
        try {
            ContentResolver cr = getBaseContext().getContentResolver();
            InputStream inputStream = cr.openInputStream(uri);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            data = baos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return data;
    }

    private void uploadFile1() {
        Toast.makeText(this, "filepath ="+filePath, Toast.LENGTH_SHORT).show();
        //if there is a file to upload
        if (filePath != null) {
            //displaying a progress dialog while upload is going on
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading");
            progressDialog.show();

            int id = Integer.parseInt(etid.getText().toString().trim());
            String name= etname.getText().toString().trim();
            String address=etaddress.getText().toString().trim();
            String location1;
           // location1=location.get(0);

            byte[] data = convertImageToByte(filePath);

            Student student = new Student();
            student.setId(id);
            student.setName(name);
            student.setAddress(address);
            student.setGender(gender);
         //   student.setLocation(location1);
            Toast.makeText(this, "data ="+ Arrays.toString(data), Toast.LENGTH_SHORT).show();
/*
            Bitmap photo = (Bitmap) "your Bitmap image";
            photo = Bitmap.createScaledBitmap(photo, 100, 100, false);
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.JPEG, 40, bytes);

            480x640

                    getResizedBitmap(data,480,640);*/

            student.setImage(data);

            MainActivity.appDatabase.studentDao().addstudent(student);

            Toast.makeText(AddStudent.this," New student added ",Toast.LENGTH_LONG).show();

            progressDialog.dismiss();

            Intent intent = new Intent(AddStudent.this,MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);


        }
        //if there is not any file
        else {
            Toast.makeText(this, "Add image", Toast.LENGTH_SHORT).show();
        }
    }

    public Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        return Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);
    }

    public void docancel(View view) {
        Intent intent = new Intent(AddStudent.this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void showFileChooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageView1.setImageBitmap(bitmap);
                imageView2.setImageBitmap(bitmap);     // later change to add three image max
                imageView3.setImageBitmap(bitmap);


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void doaddimage(View view) {
        showFileChooser();
    }

}
