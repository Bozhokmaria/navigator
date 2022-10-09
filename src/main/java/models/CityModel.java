package models;

public class CityModel {
    private int id;
    private String name;

    public CityModel() {
    }

    public CityModel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "CityModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
