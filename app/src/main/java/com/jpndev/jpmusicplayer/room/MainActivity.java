package com.jpndev.jpmusicplayer.room;  // works only image less than 2mb

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jpndev.jpmusicplayer.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static AppDatabase appDatabase;
    private List<Student> movieList = new ArrayList<>();
    private RecyclerView recyclerView;
    private StudentAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

         appDatabase = Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"studentdb").allowMainThreadQueries()
                .build();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AddStudent.class);
                startActivity(intent);
            }
        });

        mAdapter = new StudentAdapter(movieList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        prepareStudentData();


        TextView tvdisplayinfo = (TextView) findViewById(R.id.tvdisplayinfo);
        List<Student> students = MainActivity.appDatabase.studentDao().getstudent();

        String info = "";
        for(Student std : students){
            int id = std.getId();
            String name = std.getName();
            String address = std.getAddress();
            String gender = std.getGender();
            String location = std.getLocation();

            byte[] data = std.getImage();
            Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
//            imageview.setImageBitmap(bmp);

            Toast.makeText(this, "image = "+bmp, Toast.LENGTH_SHORT).show();

            info = info + "\n"+" Id : "+id+"\n Name : "+name+"\n Address : "+address
                    +"\n Gender : "+gender+"\n Location : "+location+"\n";

        }
        tvdisplayinfo.setText(info);

     }

    private void prepareStudentData() {

        List<Student> students = MainActivity.appDatabase.studentDao().getstudent();

        String info = "";
        /*
            int id = std.getId();
            String name = std.getName();
            String address = std.getAddress();
            String gender = std.getGender();
            String location = std.getLocation();
*/
        movieList.addAll(students);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /*apply plugin: 'com.android.application'

android {
    compileSdkVersion 26
    defaultConfig {
        applicationId "com.ceino.demoroomexample9"
        minSdkVersion 19
        targetSdkVersion 26
        versionCode 1
        versionName "1.0"
        testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }
}

dependencies {
    implementation fileTree(include: ['*.jar'], dir: 'libs')
    implementation 'com.android.support:appcompat-v7:26.0.1'
    implementation 'com.android.support.constraint:constraint-layout:1.0.2'
    implementation 'com.android.support:design:26.0.1'
    implementation 'com.google.firebase:firebase-database:11.0.4'
    implementation 'com.google.firebase:firebase-storage:11.0.4'
    implementation 'com.google.firebase:firebase-auth:11.0.4'
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'com.android.support.test:runner:1.0.1'
    androidTestImplementation 'com.android.support.test.espresso:espresso-core:3.0.1'
    implementation 'com.android.support:cardview-v7:26.0.1'
    implementation 'com.android.support:recyclerview-v7:26.0.1'
    implementation 'com.github.bumptech.glide:glide:3.7.0'
    implementation 'com.firebaseui:firebase-ui-database:0.4.1'
    implementation "android.arch.persistence.room:runtime:1.0.0-alpha1"
    annotationProcessor "android.arch.persistence.room:compiler:1.0.0-alpha1"

/*
    implementation fileTree(include: ['*.jar'], dir: 'libs')
    implementation 'com.android.support.constraint:constraint-layout:1.0.2'
    implementation 'android.arch.persistence.room:runtime:1.0.0'
    annotationProcessor "android.arch.persistence.room:compiler:1.0.0"
    testImplementation 'junit:junit:4.12'
    implementation "com.android.support:cardview-v7:27.0.2"*/
}

buildscript {

        repositories {
        google()
        jcenter()
        }
        dependencies {
        classpath 'com.android.tools.build:gradle:3.0.1'


        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
        classpath 'com.google.gms:google-services:3.1.0'
        }
        }

        allprojects {
        repositories {
        google()
        jcenter()
        }
        }

        task clean(type: Delete) {
        delete rootProject.buildDir
        }

*/
}
