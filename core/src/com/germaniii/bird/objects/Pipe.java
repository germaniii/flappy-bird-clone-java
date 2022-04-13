package com.germaniii.bird.objects;

import com.badlogic.gdx.math.Rectangle;

public class Pipe extends Rectangle {

    boolean isCounted;

    public Pipe(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.isCounted = false;
    }


    public void setCounted(boolean counted){
        this.isCounted = counted;
    }

    public boolean getCounted(){
        return this.isCounted;
    }



}
