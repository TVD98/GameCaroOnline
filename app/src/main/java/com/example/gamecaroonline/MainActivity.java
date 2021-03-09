package com.example.gamecaroonline;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gamecaroonline.helper.Constraints;
import com.example.gamecaroonline.helper.FirebaseSingleton;
import com.example.gamecaroonline.models.Room;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FirebaseRecyclerAdapter adapter;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getSupportFragmentManager();
                CreateRoomDialog userInfoDialog = CreateRoomDialog.newInstance();
                userInfoDialog.show(fm, null);
                userInfoDialog.setCancelable(false);

                userInfoDialog.addCreateListener(new CreateRoomDialog.CreateListener() {
                    @Override
                    public void onCreate(String roomName, int maxPlayerCount) {
                        Toast.makeText(MainActivity.this, String.format("%s, %d", roomName, maxPlayerCount), Toast.LENGTH_SHORT).show();
                        userInfoDialog.dismiss();
                    }
                });
            }
        });

        recyclerView = findViewById(R.id.rv_room);
        recyclerView.setHasFixedSize(true);

        init();

        DatabaseReference myRef = FirebaseSingleton.getInstance().database.getReference("room");
        FirebaseRecyclerOptions<Room> options = new FirebaseRecyclerOptions.Builder<Room>()
                .setQuery(myRef, Room.class)
                .build();

        FbRecyclerAdapter adapter = new FbRecyclerAdapter(options);
        adapter.startListening();

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter.addItemClickListener(new FbRecyclerAdapter.ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(MainActivity.this, String.format("%d", position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void init() {

    }

    @Override
    protected void onStart() {
        super.onStart();
    }


}

