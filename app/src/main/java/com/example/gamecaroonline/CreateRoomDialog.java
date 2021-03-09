package com.example.gamecaroonline;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class CreateRoomDialog extends DialogFragment {
    CreateListener listener;
    EditText roomName;
    EditText maxPlayerCount;
    Button create;
    Button cancel;

    public static CreateRoomDialog newInstance() {
        CreateRoomDialog dialog = new CreateRoomDialog();
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.custom_new_room, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        roomName = view.findViewById(R.id.et_room_name);
        maxPlayerCount = view.findViewById(R.id.et_max_player_count);
        create = view.findViewById(R.id.bt_create);
        cancel = view.findViewById(R.id.bt_cancel);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null){
                    String name = roomName.getText().toString();
                    int maxCount = Integer.parseInt(maxPlayerCount.getText().toString());
                    listener.onCreate(name, maxCount);
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    public void addCreateListener(CreateListener listener){
        this.listener = listener;
    }

    public interface CreateListener{
        void onCreate(String roomName, int maxPlayerCount);
    }

}
