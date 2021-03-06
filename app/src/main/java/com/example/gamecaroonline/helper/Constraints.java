package com.example.gamecaroonline.helper;

public class Constraints {
    public static final int STATUS_WAITING = 0;
    public static final int STATUS_PLAYING = 1;
    public static final int STATUS_READY = 2;
    public static final int SPAN_COUNT_ITEM_NODE = 16;

    public static String roomStatus(int status){
        if(status == STATUS_WAITING)
            return "waiting";
        else if(status == STATUS_PLAYING)
            return "playing";
        return "ready";
    }
}
