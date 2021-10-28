package com.example.naiki;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

public class Naviagtion extends AppCompatActivity {

    MeowBottomNavigation meowBottomNavigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_naviagtion);

        meowBottomNavigation = findViewById(R.id.meowbottom);

        meowBottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.ic_nearme ));
        meowBottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.ic_donate ));
        meowBottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.ic_baseline_leaderboard_24 ));
        meowBottomNavigation.add(new MeowBottomNavigation.Model(4, R.drawable.ic_profile ));

        meowBottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener(

        ) {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                Fragment fragment = null;

                switch (item.getId()){
                    case 1:
                        fragment = new home();
                        break;
                    case 2:
                        fragment = new donate();
                        break;
                    case 3:
                        fragment = new MyAchievements();

                    case 4:
                        fragment = new profile();
                        break;




                }
                loadFragment(fragment);
            }
        });

        meowBottomNavigation.setCount(1 , "10");
        meowBottomNavigation.show(2 , true);
        meowBottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
//                Toast.makeText(getApplicationContext() , "You clicked" + item.getId(), Toast.LENGTH_SHORT).show();

                meowBottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
                    @Override
                    public void onReselectItem(MeowBottomNavigation.Model item) {
//                        Toast.makeText(getApplicationContext() , "You reselected" + item.getId(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout , fragment).commit();
    }
}