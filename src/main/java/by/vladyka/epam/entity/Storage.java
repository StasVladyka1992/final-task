package by.vladyka.epam.entity;

import java.io.Serializable;

/**
 * Created by Vladyka Stas
 * on 09.03.2019 at 19:52
 **/
public class Storage extends AbstractEntity implements Serializable {
    private static final long serialVersionUID = -7270999481005115012L;
    private Integer remedyLeft;
    private Remedy remedy;

    public int getRemedyLeft() {
        return remedyLeft;
    }

    public void setRemedyLeft(int remedyLeft) {
        this.remedyLeft = remedyLeft;
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

        Storage storage = (Storage) o;
        if (getId() != storage.getId()) return false;
        if (remedyLeft.intValue() == storage.remedyLeft.intValue()) return false;
        return remedy.equals(storage.remedy);
    }

    @Override
    public int hashCode() {
        int result = remedyLeft;
        result = 31 * result + remedy.hashCode();
        result = 31 * result + getId();
        return result;
    }

    @Override
    public String toString() {
        return "Storage{" +
                "id=" + getId() +
                ", remedyLeft=" + remedyLeft +
                ", remedy=" + remedy +
                '}';
    }
}
