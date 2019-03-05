package by.vladyka.epam.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vladyka Stas
 * on 17.02.2019 at 15:53
 **/
//TODO Нужно ли такие классы делать serializable и переопределять все необход. свойства
public class RemedySearchingResult implements Serializable {
    private static final long serialVersionUID = -85604143901893419L;
    private int foundRemediesNumber;
    private List<Remedy> remedies = new ArrayList<>();

    public int getFoundRemediesNumber() {
        return foundRemediesNumber;
    }

    public void setFoundRemediesNumber(int foundRemediesNumber) {
        this.foundRemediesNumber = foundRemediesNumber;
    }

    public List<Remedy> getRemedies() {
        return remedies;
    }

    public void setRemedies(List<Remedy> remedies) {
        this.remedies = remedies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RemedySearchingResult that = (RemedySearchingResult) o;

        if (foundRemediesNumber != that.foundRemediesNumber) return false;
        return remedies.equals(that.remedies);
    }

    @Override
    public int hashCode() {
        int result = foundRemediesNumber;
        result = 31 * result + remedies.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "RemedySearchingResult{" +
                "foundRemediesNumber=" + foundRemediesNumber +
                ", remedies=" + remedies +
                '}';
    }
}
