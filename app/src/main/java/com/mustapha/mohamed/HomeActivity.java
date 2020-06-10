package com.mustapha.mohamed;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mustapha.mohamed.Prevalent.Prevalent;
import com.mustapha.mohamed.ViewHolder.ProductViewHolder;
import com.mustapha.mohamed.chatbot.ChatActivity;
import com.mustapha.mohamed.model.Menu;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;


public class HomeActivity extends AppCompatActivity {

    RecyclerView.LayoutManager layoutManager;
    private DatabaseReference ProductsRef;
    RecyclerView recyclerView;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle actionBarDrawerToggle;




    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
/*
        //cart
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {

                                       Intent intent = new Intent(HomeActivity.this, CartActivity.class);
                                       startActivity(intent);
                                   }
                               });


 */
                ProductsRef = FirebaseDatabase.getInstance().getReference().child("Products");

        //Navigation

        drawerLayout=(DrawerLayout)findViewById(R.id.drawer);
        navigationView=(NavigationView)findViewById(R.id.navigation);
        actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.open, R.string.close);
        actionBarDrawerToggle.syncState();

        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId())
                {
                    case R.id.nav_restaurant:
                        Toast.makeText(HomeActivity.this,"Restaurant",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(HomeActivity.this, RestoActivity.class);
                        startActivity(intent);

                        break;


                    case R.id.nav_cart:
                        Toast.makeText(HomeActivity.this,"Cart",Toast.LENGTH_LONG).show();
                        Intent intent1 = new Intent(HomeActivity.this, CartActivity.class);
                        startActivity(intent1);
                        break;


                    case R.id.nav_position:
                        Toast.makeText(HomeActivity.this,"Maps google",Toast.LENGTH_LONG).show();
                        Intent intent2 = new Intent(HomeActivity.this, MapsActivity.class);
                        startActivity(intent2);
                        break;

                    case R.id.nav_home:
                        Toast.makeText(HomeActivity.this,"Accueil",Toast.LENGTH_LONG).show();
                        Intent intent3 = new Intent(HomeActivity.this, HomeActivity.class);
                        startActivity(intent3);
                        break;

                    case R.id.nav_robot:
                        Toast.makeText(HomeActivity.this,"Robot",Toast.LENGTH_LONG).show();
                        Intent intent4 = new Intent(HomeActivity.this, ChatActivity.class);
                        startActivity(intent4);
                        break;


                }


                return true;
            }
        });


        recyclerView=(RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


       // user profile
        View headerView = navigationView.getHeaderView(0);
        TextView userNameTextView = headerView.findViewById(R.id.user_profile_name);
        CircleImageView profileImageView = headerView.findViewById(R.id.user_profile_image);

        userNameTextView.setText(Prevalent.currentOnlineUser.getName());



    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return actionBarDrawerToggle.onOptionsItemSelected(item) ||super.onOptionsItemSelected(item);
    }
    @Override
    protected void onStart()
    {
        super.onStart();

        FirebaseRecyclerOptions<Menu> options =
                new FirebaseRecyclerOptions.Builder<Menu>()
                        .setQuery(ProductsRef, Menu.class)
                        .build();

        FirebaseRecyclerAdapter<Menu, ProductViewHolder> adapter =
                new FirebaseRecyclerAdapter<Menu, ProductViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull ProductViewHolder holder, int i, @NonNull final Menu model) {
                        holder.txtProductName.setText(model.getPname());
                        holder.txtProductDescription.setText(model.getDescription());
                        holder.txtProductPrice.setText("Price = " + model.getPrice() + "Dhs");
                        Picasso.get().load(model.getImage()).into(holder.imageView);

                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Intent intent = new Intent(HomeActivity.this, ProductDetailsActivity.class);
                                intent.putExtra("pid", model.getPid());
                                startActivity(intent);
                            }
                        });

                    }

                    @NonNull
                    @Override
                    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_items_layout, parent, false);
                        ProductViewHolder holder = new ProductViewHolder(view);
                        return holder;
                    }
                };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
}

}
