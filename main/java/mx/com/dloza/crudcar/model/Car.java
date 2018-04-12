package mx.com.dloza.crudcar.model;

/**
 * Created by Jairo on 09/04/2018.
 */

public class Car {

    private long id;
    private String name;
    private String mark;
    private String model;
    private String transmission;
    private String combustible;
    private String year;
    private String image;

    public Car() {
    }

    public Car(String name, String mark, String model, String transmission, String combustible, String year, String image) {
        this.name = name;
        this.mark = mark;
        this.model = model;
        this.transmission = transmission;
        this.combustible = combustible;
        this.year = year;
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getCombustible() {
        return combustible;
    }

    public void setCombustible(String combustible) {
        this.combustible = combustible;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}

