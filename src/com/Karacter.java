package com;

import intefaces.Animatable;

public class Karacter implements Animatable {
    String name;
    Integer boom;
    Integer blast;
    Integer speed;
    Integer maxBoom;
    Integer maxBlast;
    Integer maxSpeed;

    public Karacter(String name, Integer boom, Integer blast, Integer speed, Integer maxBoom, Integer maxBlast, Integer maxSpeed) {
        this.name = name;
        this.boom = boom;
        this.blast = blast;
        this.speed = speed;
        this.maxBoom = maxBoom;
        this.maxBlast = maxBlast;
        this.maxSpeed = maxSpeed;
    }

    @Override
    public String toString() {
//        return "Character DONE!";
        return "[ " + this.name + ": " + this.boom + " | " + this.blast+ " | "+ this.speed + " | MAX: " + this.maxBoom + " | " + this.maxBlast + " | "+ this.maxSpeed + " ] ";
    }

    @Override
    public void loadAnimation(String url) {

    }
}
