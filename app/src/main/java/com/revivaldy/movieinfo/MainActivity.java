package com.revivaldy.movieinfo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.revivaldy.movieinfo.Fragment.MovieFragment;
import com.revivaldy.movieinfo.Fragment.TvShowFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    FloatingActionMenu fam;
    FloatingActionButton fabMovie, fabShow;
    SearchView searchView;
    private String searchedType;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_movie:
                    getSupportActionBar().setTitle(getResources().getString(R.string.actionbar_movie));
                    searchedType = "movie";
                    fragment = new MovieFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container_layout, fragment, fragment.getClass().getSimpleName())
                            .addToBackStack(null)
                            .commit();
                    return true;
                case R.id.navigation_tv_show:
                    getSupportActionBar().setTitle(getResources().getString(R.string.actionbar_tv_show));
                    searchedType = "tv_show";
                    fragment = new TvShowFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container_layout, fragment, fragment.getClass().getSimpleName())
                            .addToBackStack(null)
                            .commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navView = findViewById(R.id.nav_view);

        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        if (savedInstanceState == null) {
            navView.setSelectedItemId(R.id.navigation_movie);
        }

        prepareFavMenu();
    }

    private void prepareFavMenu() {


    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {

    }
}
