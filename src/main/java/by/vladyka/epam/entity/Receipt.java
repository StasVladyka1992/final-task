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

        if (expireDate != null ? !expireDate.equals(receipt.expireDate) : receipt.expireDate != null) return false;
        if (prescriptionDate != null ? !prescriptionDate.equals(receipt.prescriptionDate) : receipt.prescriptionDate != null)
            return false;
        if (client != null ? !client.equals(receipt.client) : receipt.client != null) return false;
        if (doctor != null ? !doctor.equals(receipt.doctor) : receipt.doctor != null) return false;
        if (remedy != null ? !remedy.equals(receipt.remedy) : receipt.remedy != null) return false;
        if (message != null ? !message.equals(receipt.message) : receipt.message != null) return false;
        return status == receipt.status;
    }

    @Override
    public int hashCode() {
        int result = expireDate != null ? expireDate.hashCode() : 0;
        result = 31 * result + (prescriptionDate != null ? prescriptionDate.hashCode() : 0);
        result = 31 * result + (client != null ? client.hashCode() : 0);
        result = 31 * result + (doctor != null ? doctor.hashCode() : 0);
        result = 31 * result + (remedy != null ? remedy.hashCode() : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
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
