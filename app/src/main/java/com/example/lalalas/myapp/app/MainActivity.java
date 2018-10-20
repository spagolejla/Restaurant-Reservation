package com.example.lalalas.myapp.app;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Fragment;

import com.example.lalalas.myapp.R;
import com.example.lalalas.myapp.fragments.BlankFragment;
import com.example.lalalas.myapp.fragments.HelpFragment;
import com.example.lalalas.myapp.fragments.KorisnickiProfilFragment;
import com.example.lalalas.myapp.fragments.RestoranListFragment;
import com.example.lalalas.myapp.fragments.RezervacijaListFragment;
import com.example.lalalas.myapp.helper.MyFragmentUtils;
import com.example.lalalas.myapp.helper.MySession;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    private DrawerLayout mDrawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





setUpToolbar();
//setUpDrawer();
        mDrawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        selectDrawerItem(menuItem);

                        // close drawer when item is tapped


                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        return true;
                    }
                });

        MyFragmentUtils.openAsReplace(this,R.id.mjestoFragment, RestoranListFragment.newInstance());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return true;
    }


    //  private void setUpDrawer() {

    //    NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.nav_drwr_fragment);
      //  DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
       // drawerFragment.setUpDrawer(R.id.nav_drwr_fragment, drawerLayout, toolbar);
//}

    private void setUpToolbar() {
        toolbar=(Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Restorani");
        toolbar.inflateMenu(R.menu.toolbar_menu);


        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the fragment to show based on nav item clicked
        Fragment fragment = null;
        Class fragmentClass;
        switch(menuItem.getItemId()) {
            case R.id.nav_restorani:
                fragmentClass = RestoranListFragment.class;
                break;
            case R.id.nav_rezervacije:
                fragmentClass = RezervacijaListFragment.class;

                break;
            case R.id.nav_korisnickiProfil:
                fragmentClass = KorisnickiProfilFragment.class;

                break;
            case R.id.nav_pomoc:
                fragmentClass = HelpFragment.class;

                break;
            default:
                fragmentClass = RestoranListFragment.class;
                //MySession.setKorisnik(this,null);
                startActivity(new Intent(this, LoginActivity.class));

        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment

       MyFragmentUtils.openAsReplace(this,R.id.mjestoFragment,fragment);
        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        // Set action bar title
       toolbar.setTitle(menuItem.getTitle());
        // Close the navigation drawer
        mDrawerLayout.closeDrawers();
    }


}
