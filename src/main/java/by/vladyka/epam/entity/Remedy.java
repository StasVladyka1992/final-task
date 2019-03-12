package by.vladyka.epam.entity;

import java.io.Serializable;

/**
 * Created by Vladyka Stas
 * on 26.02.2019 at 1:38
 **/
public class Remedy extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 6142050425358602007L;
    private String name;
    private String description;
    private double price;
    private boolean receiptRequired;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isReceiptRequired() {
        return receiptRequired;
    }

    public void setReceiptRequired(boolean receiptRequired) {
        this.receiptRequired = receiptRequired;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Remedy remedy = (Remedy) o;
        if (getId() != remedy.getId()) return false;
        if (Double.compare(remedy.price, price) != 0) return false;
        if (receiptRequired != remedy.receiptRequired) return false;
        if (!name.equals(remedy.name)) return false;
        return description.equals(remedy.description);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name.hashCode();
        result = 31 * result + description.hashCode();
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (receiptRequired ? 1 : 0);
        result = 31 * result + getId();
        return result;
    }

    @Override
    public String toString() {
        return "Remedy{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", receiptRequired=" + receiptRequired +
                '}';
    }
}
