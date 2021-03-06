package by.vladyka.epam.dto;

import by.vladyka.epam.entity.Storage;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by Vladyka Stas
 * on 19.03.2019 at 23:10
 **/
public class OrderDto implements Serializable {
    private static final long serialVersionUID = 4125520138516427778L;
    private Map<Storage, Integer> goods;

    public Map<Storage, Integer> getGoods() {
        return goods;
    }

    public void setGoods(Map<Storage, Integer> goods) {
        this.goods = goods;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDto that = (OrderDto) o;
        return goods.equals(that.goods);
    }

    @Override
    public int hashCode() {
        int result = goods.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "goods=" + goods;
    }
}
