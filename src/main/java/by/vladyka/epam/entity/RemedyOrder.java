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

        if (quantity != that.quantity) return false;
        if (remedy != null ? !remedy.equals(that.remedy) : that.remedy != null) return false;
        if (receipt != null ? !receipt.equals(that.receipt) : that.receipt != null) return false;
        return order != null ? order.equals(that.order) : that.order == null;
    }

    @Override
    public int hashCode() {
        int result = remedy != null ? remedy.hashCode() : 0;
        result = 31 * result + quantity;
        result = 31 * result + (receipt != null ? receipt.hashCode() : 0);
        result = 31 * result + (order != null ? order.hashCode() : 0);
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
