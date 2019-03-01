package by.vladyka.epam.entity;

import java.io.Serializable;

/**
 * Created by Vladyka Stas
 * on 26.02.2019 at 1:38
 **/
public class Remedy implements Serializable {
    private int idRemedy;
    private String name;
    private String packing;
    private String maker;
    private int remainder;
    private double price;

    public int getIdRemedy() {
        return idRemedy;
    }

    public void setIdRemedy(int idRemedy) {
        this.idRemedy = idRemedy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPacking() {
        return packing;
    }

    public void setPacking(String packing) {
        this.packing = packing;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public int getRemainder() {
        return remainder;
    }

    public void setRemainder(int remainder) {
        this.remainder = remainder;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Remedy remedy = (Remedy) o;

        if (remainder != remedy.remainder) return false;
        if (idRemedy != remedy.idRemedy) return false;
        if (Double.compare(remedy.price, price) != 0) return false;
        if (!name.equals(remedy.name)) return false;
        if (!packing.equals(remedy.packing)) return false;
        return maker.equals(remedy.maker);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name.hashCode();
        result = 31 * result + packing.hashCode();
        result = 31 * result + maker.hashCode();
        result = 31 * result + remainder;
        result = 31 * result + idRemedy;
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Remedy{" +
                "name='" + name + '\'' +
                ", packing='" + packing + '\'' +
                ", maker='" + maker + '\'' +
                ", remainder=" + remainder +
                ", idRemedy=" + idRemedy +
                ", price=" + price +
                '}';
    }
}
