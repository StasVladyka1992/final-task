package by.vladyka.epam.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Vladyka Stas
 * on 09.03.2019 at 19:47
 **/
public class Receipt extends AbstractEntity implements Serializable {
    private Date expireDate;
    private Date prescriptionDate;
    private User client;
    private User doctor;
    private Remedy remedy;
    private String message;
    private Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getPrescriptionDate() {
        return prescriptionDate;
    }

    public void setPrescriptionDate(Date prescriptionDate) {
        this.prescriptionDate = prescriptionDate;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public User getDoctor() {
        return doctor;
    }

    public void setDoctor(User doctor) {
        this.doctor = doctor;
    }

    public Remedy getRemedy() {
        return remedy;
    }

    public void setRemedy(Remedy remedy) {
        this.remedy = remedy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Receipt receipt = (Receipt) o;
        if (!message.equals(receipt.message)) return false;
        if (getId() != receipt.getId())
            if (!expireDate.equals(receipt.expireDate)) return false;
        if (!prescriptionDate.equals(receipt.prescriptionDate)) return false;
        if (!client.equals(receipt.client)) return false;
        if (!doctor.equals(receipt.doctor)) return false;
        if (status != receipt.status) return false;
        return remedy.equals(receipt.remedy);
    }

    @Override
    public int hashCode() {
        int result = expireDate.hashCode();
        result = 31 * result + message.hashCode();
        result = 31 * result + prescriptionDate.hashCode();
        result = 31 * result + client.hashCode();
        result = 31 * result + doctor.hashCode();
        result = 31 * result + remedy.hashCode();
        result = 31 * result + getId();
        result = 31 * result + status.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "id=" + getId() +
                ", expireDate=" + expireDate +
                ", prescriptionDate=" + prescriptionDate +
                ", client=" + client +
                ", doctor=" + doctor +
                ", remedy=" + remedy +
                ", message=" + message +
                ", status=" + status +
                '}';
    }

    public enum Status {
        REJECTED, APPROVED, NONE, USED
    }
}
