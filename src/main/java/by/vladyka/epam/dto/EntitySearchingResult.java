package by.vladyka.epam.dto;

import by.vladyka.epam.entity.AbstractEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vladyka Stas
 * on 17.02.2019 at 15:53
 **/

public class EntitySearchingResult<T extends AbstractEntity>implements Serializable {
    private static final long serialVersionUID = -2650708455747923267L;
    private int foundEntitiesNumber;
    private List<T> foundEntities = new ArrayList<>();

    public int getFoundEntitiesNumber() {
        return foundEntitiesNumber;
    }

    public void setFoundEntitiesNumber(int foundEntitiesNumber) {
        this.foundEntitiesNumber = foundEntitiesNumber;
    }

    public List<T> getFoundEntities() {
        return foundEntities;
    }

    public void setFoundEntities(List<T> foundEntities) {
        this.foundEntities = foundEntities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntitySearchingResult that = (EntitySearchingResult) o;
        if (!foundEntities.containsAll(that.foundEntities)&& foundEntities.size()==that.foundEntities.size()) return false;
        if (foundEntitiesNumber != that.foundEntitiesNumber) return false;
        return foundEntities.equals(that.foundEntities);
    }

    @Override
    public int hashCode() {
        int result = foundEntitiesNumber;
        result = 31 * result + foundEntities.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "StorageSearchingResult{" +
                "foundEntitiesNumber=" + foundEntitiesNumber +
                ", foundEntities=" + foundEntities +
                '}';
    }
}
