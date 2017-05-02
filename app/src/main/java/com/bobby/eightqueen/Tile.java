package com.bobby.eightqueen;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by bobby on 27/04/2017.
 */

public class Tile extends android.support.v7.widget.AppCompatButton {
    private boolean isQueen;


    public Tile(Context context, boolean isQueen) {
        super(context);
        this.isQueen=isQueen;
    }

    public boolean isQueen() {
        return isQueen;
    }

    public void setQueen(boolean queen) {
        isQueen = queen;
    }
}
