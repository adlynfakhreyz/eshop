package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.AbstractItem;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class AbstractItemRepository<T extends AbstractItem> implements Repository<T> {
    protected final List<T> items = new ArrayList<>();

    @Override
    public T create(T item) {
        items.add(item);
        return item;
    }

    @Override
    public Iterator<T> findAll() {
        return items.iterator();
    }

    @Override
    public T findById(String id) {
        for (T item : items) {
            if (item.getId().equals(id)) {
                return item;
            }
        }
        return null;
    }

    @Override
    public abstract T update(String id, T item);

    @Override
    public void delete(String id) {
        items.removeIf(item -> item.getId().equals(id));
    }
}