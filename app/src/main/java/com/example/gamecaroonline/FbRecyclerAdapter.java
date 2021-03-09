package com.example.gamecaroonline;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gamecaroonline.helper.Constraints;
import com.example.gamecaroonline.models.Room;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class FbRecyclerAdapter extends FirebaseRecyclerAdapter<Room, FbRecyclerAdapter.RoomViewHolder> {
    private ItemClickListener listener;

    public FbRecyclerAdapter(@NonNull FirebaseRecyclerOptions<Room> options) {
        super(options);
    }

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

    public void addItemClickListener(ItemClickListener listener){
        this.listener = listener;
    }

    public class RoomViewHolder extends RecyclerView.ViewHolder {
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

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        listener.onItemClick(getAdapterPosition());
                    }
                }
            });
        }

    }

    public interface ItemClickListener{
        void onItemClick(int position);
    }
}
