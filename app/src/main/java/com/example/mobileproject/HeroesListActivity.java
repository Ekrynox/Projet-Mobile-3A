package com.example.mobileproject;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileproject.controller.DotaRest;
import com.example.mobileproject.model.DotaHero;

import java.util.List;


public class HeroesListActivity extends AppCompatActivity {

    public static String EXTRA_HEROID = "com.example.mobileproject.heroId";
    public static String EXTRA_HERONAME = "com.example.mobileproject.heroName";
    public static String EXTRA_HEROIMG = "com.example.mobileproject.heroImg";

    private List<DotaHero> heroeslist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DotaRest controller = DotaRest.getInstance();
        controller.loadHeroes(this::updateHeroesList, null);
    }

    void updateHeroesList(List<DotaHero> heroeslist) {
        this.heroeslist = heroeslist;

        RecyclerView recyclerView = findViewById(R.id.heroeslist);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerView.Adapter mAdapter = new DotaHeroesList_RecyclerAdapter(heroeslist, this);
        recyclerView.setAdapter(mAdapter);
    }

    void showHeroesStat(int id) {
        if(heroeslist.size() <= id) {
            return;
        }
        Intent intent = new Intent(this, HeroStatActivity.class);
        intent.putExtra(EXTRA_HEROID, heroeslist.get(id).getId());
        intent.putExtra(EXTRA_HERONAME, heroeslist.get(id).getLocalized_name());
        intent.putExtra(EXTRA_HEROIMG, heroeslist.get(id).getImg());
        startActivity(intent);
    }
}
