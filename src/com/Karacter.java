package com;

public class Karacter {
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
}
