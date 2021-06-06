package sku.todo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MainSceneModel implements DatabaseListener {


    // I do not know if should use ObservableList in Model as I think Model is a domain level concept it should
    // not interact with framework components, on the other hand I would have to implement a mechanism for
    // propagating change back to controller too, so why not just use this only.
    public ObservableList<Item> items = FXCollections.observableArrayList();

    private final Database database;

    public MainSceneModel(Database database) {
        this.database = database;
        this.items.setAll(database.getItems());
        database.addListener(this);
    }

    @Override
    public void dataChanged() {
        this.items.setAll(database.getItems());
    }
}
