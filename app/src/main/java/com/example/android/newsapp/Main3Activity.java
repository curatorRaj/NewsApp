package com.example.android.newsapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class Main3Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    DrawerLayout drawerLayout;
    GoogleSignInClient mGoogleSignInClient;
    Toolbar toolbar;
    TextView textView;
    ImageView imageView;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search,menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        toolbar =findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);

        NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        //textView= findViewById(R.id.name);
        imageView = findViewById(R.id.nav_image);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(Main3Activity.this);
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personGivenName = acct.getGivenName();
            String personFamilyName = acct.getFamilyName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();


            TextView userName = (TextView)navigationView.getHeaderView(0).findViewById(R.id.name);

           ImageView imageView = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.nav_image);


           // textView= findViewById(R.id.name);
            userName.setText(" "+personName);
            //emailTV.setText("Email: "+personEmail);
            //dTV.setText("ID: "+personId);

           Glide.with(getBaseContext()).load(personPhoto).into(imageView);
           if(savedInstanceState==null) {


               getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new ForYou()).commit();

               navigationView.setCheckedItem(R.id.yours);
           }


        }

        navigationView.setNavigationItemSelectedListener(this); }

            @Override
            public boolean onNavigationItemSelected( MenuItem menuItem) {
                menuItem.setChecked(true);
                switch (menuItem.getItemId())
                {
                    case R.id.signout:

                        mGoogleSignInClient.signOut()
                                .addOnCompleteListener(Main3Activity.this, new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Toast.makeText(Main3Activity.this,"Successfully signed out",Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(Main3Activity.this, Main2Activity.class));
                                        finish();
                                    }
                                });
                        break;
                    case R.id.yours:

                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment,new ForYou()).commit();
                        break;

                    case R.id.sports:

                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment,new Sports()).commit();
                        break;

                    case R.id.entertainment:

                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment,new entertainment ()).commit();
                        break;

                    case R.id.gadgets:

                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment,new gadgets()).commit();
                        break;
                    case R.id.lifestyle:

                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment,new LifestyleFragment()).commit();
                        break;
                    case R.id.search:

                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment,new SearchFragment()).commit();
                        break;



                }
                drawerLayout.closeDrawers();


                return true;
            }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }



}
