package com.solvd.navigator.model;

public class Distance {
    private int id;
    private double distance;
    private int city1Id;
    private int city2Id;

    public Distance() {
    }

    public Distance(int id, double distance, int city1Id, int city2Id) {
        this.id = id;
        this.distance = distance;
        this.city1Id = city1Id;
        this.city2Id = city2Id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getCity1_id() {
        return city1Id;
    }

    public void setCity1_id(int city1Id) {
        this.city1Id = city1Id;
    }

    public int getCity2_id() {
        return city2Id;
    }

    public void setCity2_id(int city2Id) {
        this.city2Id = city2Id;
    }

    @Override
    public String toString() {
        return "Distance{" +
                "id=" + id +
                ", distance=" + distance +
                ", city1Id=" + city1Id +
                ", city2Id=" + city2Id +
                '}';
    }
}
