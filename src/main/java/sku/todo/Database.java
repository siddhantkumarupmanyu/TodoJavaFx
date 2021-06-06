package sku.todo;

import java.util.List;

public interface Database {
    void addItem(Item item);

    List<Item> getItems();

    void addListener(DatabaseListener listener);

    void notifyDataChanged();
}
