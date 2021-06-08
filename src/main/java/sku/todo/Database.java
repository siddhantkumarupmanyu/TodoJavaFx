package sku.todo;

import java.util.List;

public interface Database {
    void addItem(Item item);

    void saveEdit(Item editedItem);

    List<Item> getItems();

    void addListener(DatabaseListener listener);

    void notifyDataChanged();
}
