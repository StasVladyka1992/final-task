package by.vladyka.epam.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vladyka Stas
 * on 17.02.2019 at 15:53
 **/

public class RemedySearchingResult implements Serializable {
    private static final long serialVersionUID = -2650708455747923267L;
    private int foundRemediesNumber;
    private List<Storage> positions = new ArrayList<>();

    public int getFoundRemediesNumber() {
        return foundRemediesNumber;
    }

    public void setFoundRemediesNumber(int foundRemediesNumber) {
        this.foundRemediesNumber = foundRemediesNumber;
    }

    public List<Storage> getPositions() {
        return positions;
    }

    public void setPositions(List<Storage> positions) {
        this.positions = positions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RemedySearchingResult that = (RemedySearchingResult) o;
        if (!positions.containsAll(that.positions)&&positions.size()==that.positions.size()) return false;
        if (foundRemediesNumber != that.foundRemediesNumber) return false;
        return positions.equals(that.positions);
    }

    @Override
    public int hashCode() {
        int result = foundRemediesNumber;
        result = 31 * result + positions.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "StorageSearchingResult{" +
                "foundRemediesNumber=" + foundRemediesNumber +
                ", positions=" + positions +
                '}';
    }
}
