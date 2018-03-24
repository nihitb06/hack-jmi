package team7.hack.com.hackjmiapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.NavigationView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawerLayout);
        toolbar = findViewById(R.id.toolbar);

        setToolbar();
        setNavigation();
    }

    private void setToolbar() {
        setSupportActionBar(toolbar);
    }
    private void setNavigation() {
        final LinearLayout contentView = findViewById(R.id.contentView);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
                this, drawerLayout,
                toolbar,
                R.string.drawer_open,
                R.string.drawer_close) {
            private float scaleFactor = 6f;

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                float slideX = drawerView.getWidth() * slideOffset;
                contentView.setTranslationX(slideX);
                contentView.setScaleX(1 - (slideOffset / scaleFactor));
                contentView.setScaleY(1 - (slideOffset / scaleFactor));
            }
        };

        drawerLayout.setScrimColor(Color.TRANSPARENT);
        drawerLayout.setDrawerElevation(0f);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        actionBarDrawerToggle.syncState();

        ((NavigationView) findViewById(R.id.navBarContainer)).setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.profile:
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                return true;
        }
        return false;
    }
}
