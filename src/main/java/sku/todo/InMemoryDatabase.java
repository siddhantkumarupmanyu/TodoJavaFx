package sku.todo;

import sku.todo.utils.Announcer;

import java.util.ArrayList;
import java.util.List;

public class InMemoryDatabase implements Database {

    private final ArrayList<Item> items = new ArrayList<>();

    private final Announcer<DatabaseListener> listeners = Announcer.to(DatabaseListener.class);

    private int index = 0;

    @Override
    public void addItem(Item item) {
        Item itemToAdd = new Item(index++, item.heading, item.content);
        items.add(itemToAdd);
        notifyDataChanged();
    }

    @Override
    public void saveEdit(Item editedItem) {
        items.remove(editedItem.id);
        items.add(editedItem.id, editedItem);
    }

    @Override
    public List<Item> getItems() {
        return items;
    }

    @Override
    public void addListener(DatabaseListener listener) {
        listeners.addListener(listener);
    }

    @Override
    public void notifyDataChanged() {
        listeners.announce().dataChanged();
    }
}
