package sku.todo;

public class MainSceneModel implements DatabaseListener {

    private final Database database;

    public MainSceneModel(Database database) {
        this.database = database;
        database.getItems();
        database.addListener(this);
    }

    @Override
    public void dataChanged() {
        database.getItems();
    }
}
