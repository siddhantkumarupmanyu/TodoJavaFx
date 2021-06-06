package sku.todo.ui;

import org.junit.Test;
import sku.todo.Database;
import sku.todo.Item;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AddDialogSceneModelTest {

    private final Database database = mock(Database.class);

    private final AddDialogSceneModel model = new AddDialogSceneModel(database);

    @Test
    public void addItem() {
        model.addItem("heading", "content");

        verify(database).addItem(new Item("heading", "content"));
    }


}