package by.vladyka.epam.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vladyka Stas
 * on 26.02.2019 at 1:38
 **/
public class Remedy implements Serializable {
    private static final long serialVersionUID = -3165863950136927834L;
    private int idRemedy;
    private String name;
    private Packing packing;
    private String maker;
    private int quantity;
    private double price;
    private char receipt;

    public Remedy() {
    }

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

    public Packing getPacking() {
        return packing;
    }

    public void setPacking(Packing packing) {
        this.packing = packing;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public char getReceipt() {
        return receipt;
    }

    public void setReceipt(char receipt) {
        this.receipt = receipt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Remedy remedy = (Remedy) o;

        if (idRemedy != remedy.idRemedy) return false;
        if (quantity != remedy.quantity) return false;
        if (Double.compare(remedy.price, price) != 0) return false;
        if (receipt != remedy.receipt) return false;
        if (!name.equals(remedy.name)) return false;
        if (packing != remedy.packing) return false;
        return maker.equals(remedy.maker);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = idRemedy;
        result = 31 * result + name.hashCode();
        result = 31 * result + packing.hashCode();
        result = 31 * result + maker.hashCode();
        result = 31 * result + quantity;
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (int) receipt;
        return result;
    }

    @Override
    public String toString() {
        return "Remedy{" +
                "idRemedy=" + idRemedy +
                ", name='" + name + '\'' +
                ", packing=" + packing +
                ", maker='" + maker + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", receipt=" + receipt +
                '}';
    }

    public enum Packing {
        POWDER, PILL, TUBE, STANDARD, ANOTHER;

        @Override
        public String toString() {
            return valuesContainer.get(this);
        }

        private static Map<Packing, String> valuesContainer = new HashMap<>();

        static {
            valuesContainer.put(PILL, "таблетки");
            valuesContainer.put(TUBE, "туба");
            valuesContainer.put(STANDARD, "стандарт");
            valuesContainer.put(POWDER, "порошок");
            valuesContainer.put(ANOTHER, "другое");
        }

        public static Packing getEnumElementByValue(String packingValue) {
            Packing packing = null;
            for (Map.Entry<Packing, String> pair : valuesContainer.entrySet()) {
                if (packingValue.equals(pair.getValue())) {
                    packing = pair.getKey();
                }
            }
            return packing;
        }
    }
}
