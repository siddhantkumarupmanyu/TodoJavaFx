package sku.todo.ui;

import sku.todo.Database;
import sku.todo.Item;

public class AddDialogSceneModel {

    private final Database database;

    private Item currentItem;

    public AddDialogSceneModel(Database database) {
        this.database = database;
        currentItem = Item.emptyItem;
    }

    public void setItem(Item item) {
        currentItem = item;
    }

    public void editSaveOrAdd(String heading, String content) {
        if (currentItem.isANewItem()) {
            Item newItem = new Item(heading, content);
            if (!newItem.isEmpty()) {
                database.addItem(new Item(heading, content));
            }
        } else {
            database.saveEdit(new Item(currentItem.id, heading, content));
        }
    }
}
