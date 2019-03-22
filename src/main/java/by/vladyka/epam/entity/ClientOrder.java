package by.vladyka.epam.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by Vladyka Stas
 * on 09.03.2019 at 19:58
 **/
public class ClientOrder extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 4356189928633431320L;
    private Date createdOn;
    private Date finishedOn;
    private User client;
    private List<RemedyOrder> remedyOrders;
    private ClientOrderStatus status;

    public ClientOrderStatus getStatus() {
        return status;
    }

    public void setStatus(ClientOrderStatus status) {
        this.status = status;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getFinishedOn() {
        return finishedOn;
    }

    public void setFinishedOn(Date finishedOn) {
        this.finishedOn = finishedOn;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public List<RemedyOrder> getRemedyOrders() {
        return remedyOrders;
    }

    public void setRemedyOrders(List<RemedyOrder> remedyOrders) {
        this.remedyOrders = remedyOrders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientOrder that = (ClientOrder) o;
        if (getId() != that.getId()) return false;
        if (!createdOn.equals(that.createdOn)) return false;
        if (!Objects.equals(finishedOn, that.finishedOn)) return false;
        if (!client.equals(that.client)) return false;
        if (status != that.status) return false;
        return remedyOrders.equals(that.remedyOrders);
    }

    @Override
    public int hashCode() {
        int result = createdOn.hashCode();
        result = 31 * result + (finishedOn != null ? finishedOn.hashCode() : 0);
        result = 31 * result + client.hashCode();
        result = 31 * result + remedyOrders.hashCode();
        result = 31 * result + getId();
        result = 31 * result + status.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ClientOrder{" +
                "id=" + getId() +
                ", createdOn=" + createdOn +
                ", finishedOn=" + finishedOn +
                ", client=" + client +
                ", remedyOrders=" + remedyOrders +
                ", status=" + status +
                '}';
    }

    public enum ClientOrderStatus {
        REJECTED, EXECUTED, UNHANDLED
    }
}
