package com.example.gamecaroonline;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gamecaroonline.helper.Constraints;
import com.example.gamecaroonline.helper.FirebaseSingleton;
import com.example.gamecaroonline.models.Room;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FirebaseRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rv_room);
        recyclerView.setHasFixedSize(true);

        init();

        DatabaseReference myRef = FirebaseSingleton.getInstance().database.getReference("room");
        FirebaseRecyclerOptions<Room> options = new FirebaseRecyclerOptions.Builder<Room>()
                .setQuery(myRef, Room.class)
                .build();

        adapter = new FirebaseRecyclerAdapter<Room, RoomViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull RoomViewHolder holder, int position, @NonNull Room model) {
                holder.setName(model.getName());
                holder.setStatus(model.getStatus());
                holder.setPlayerCount(model.getPlayerCount());
                holder.setMaxPlayerCount(model.getMaxPlayerCount());
            }

            @NonNull
            @Override
            public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                View v = inflater.inflate(R.layout.item_room, parent, false);
                return new RoomViewHolder(v);
            }
        };
        adapter.startListening();

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void init() {

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private class RoomViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView status;
        private TextView playerCount;
        private TextView maxPlayerCount;

        public void setName(String name) {
            this.name.setText(name);
        }

        public void setStatus(int status) {
            this.status.setText(Constraints.roomStatus(status));
        }

        public void setPlayerCount(int playerCount) {
            this.playerCount.setText(Integer.toString(playerCount));
        }

        public void setMaxPlayerCount(int maxPlayerCount) {
            this.maxPlayerCount.setText(Integer.toString(maxPlayerCount));
        }

        public RoomViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.tv_room_name);
            status = itemView.findViewById(R.id.tv_status);
            playerCount = itemView.findViewById(R.id.tv_player_count);
            maxPlayerCount = itemView.findViewById(R.id.tv_max_player_count);
        }

    }
}

