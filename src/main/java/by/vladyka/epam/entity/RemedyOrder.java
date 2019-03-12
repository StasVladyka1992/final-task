package by.vladyka.epam.entity;

import java.io.Serializable;

/**
 * Created by Vladyka Stas
 * on 09.03.2019 at 19:56
 **/
public class RemedyOrder extends AbstractEntity implements Serializable {
    private static final long serialVersionUID = -3340520939385252632L;
    private Remedy remedy;
    private int quantity;
    private Receipt receipt;
    private ClientOrder order;

    public Remedy getRemedy() {
        return remedy;
    }

    public void setRemedy(Remedy remedy) {
        this.remedy = remedy;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Receipt getReceipt() {
        return receipt;
    }

    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }

    public ClientOrder getOrder() {
        return order;
    }

    public void setOrder(ClientOrder order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RemedyOrder that = (RemedyOrder) o;
        if (getId() != that.getId()) return false;
        if (quantity != that.quantity) return false;
        if (!remedy.equals(that.remedy)) return false;
        if (!receipt.equals(that.receipt)) return false;
        return order.equals(that.order);
    }

    @Override
    public int hashCode() {
        int result = remedy.hashCode();
        result = 31 * result + quantity;
        result = 31 * result + receipt.hashCode();
        result = 31 * result + order.hashCode();
        result = 31 * result + getId();
        return result;
    }

    @Override
    public String toString() {
        return "RemedyOrder{" +
                "id=" + remedy +
                ", remedy =" + remedy +
                ", quantity=" + quantity +
                ", receipt=" + receipt +
                ", order=" + order +
                '}';
    }
}
