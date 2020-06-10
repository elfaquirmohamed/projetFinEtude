package com.mustapha.mohamed;

import android.os.Bundle;

import com.mustapha.mohamed.ViewHolder.RestoAdapter;
import com.mustapha.mohamed.model.RestoData;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RestoActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    List<RestoData> myFoodList;
    RestoData mFoodData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resto);

        mRecyclerView=findViewById(R.id.recycler_resto);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(RestoActivity.this,1);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        myFoodList=new ArrayList<>();


        mFoodData= new RestoData("Mc donald", "Fast Food",R.drawable.logo1);
        myFoodList.add(mFoodData);


        mFoodData= new RestoData("FASTFOOD", "Fast Food ",R.drawable.logo3);
        myFoodList.add(mFoodData);


        mFoodData= new RestoData("FASTFOOD", "Fast Food ",R.drawable.logo5);
        myFoodList.add(mFoodData);


        mFoodData= new RestoData("FASTFOOD", "Fast Food ",R.drawable.logo6);
        myFoodList.add(mFoodData);

        mFoodData= new RestoData("FOOD EXPRESS", "Fast Food",R.drawable.logo7);
        myFoodList.add(mFoodData);

        mFoodData= new RestoData("BEFOODIE", "Fast Food ",R.drawable.logo9);
        myFoodList.add(mFoodData);

        RestoAdapter myAdapter=new RestoAdapter(RestoActivity.this,myFoodList);
        mRecyclerView.setAdapter(myAdapter);
    }
}