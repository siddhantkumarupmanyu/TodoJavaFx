package sku.todo.ui;

import sku.todo.Database;
import sku.todo.Item;

public class AddDialogSceneModel {

    private final Database database;

    public AddDialogSceneModel(Database database) {
        this.database = database;
    }

    public void addItem(String heading, String content) {
        database.addItem(new Item(heading, content));
    }
}
