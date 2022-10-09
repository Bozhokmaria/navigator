package models;

public class DistanceModel {
    private int id;
    private double distance;
    private int city1_id;
    private int city2_id;

    public DistanceModel() {
    }

    public DistanceModel(int id, double distance, int city1_id, int city2_id) {
        this.id = id;
        this.distance = distance;
        this.city1_id = city1_id;
        this.city2_id = city2_id;
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
        return city1_id;
    }

    public void setCity1_id(int city1_id) {
        this.city1_id = city1_id;
    }

    public int getCity2_id() {
        return city2_id;
    }

    public void setCity2_id(int city2_id) {
        this.city2_id = city2_id;
    }

    @Override
    public String toString() {
        return "DistanceModel{" +
                "id=" + id +
                ", distance=" + distance +
                ", id city 1=" + city1_id +
                ", id city 2=" + city2_id +
                '}';
    }
}
