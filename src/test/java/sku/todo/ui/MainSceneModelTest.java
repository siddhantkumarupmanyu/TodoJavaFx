package sku.todo.ui;

import org.junit.Before;
import org.junit.Test;
import sku.todo.Database;
import sku.todo.Item;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class MainSceneModelTest {

    private final Database database = mock(Database.class);

    private final MainSceneModel model = new MainSceneModel(database);

    private final Item item = new Item("Heading", "content");
    private final List<Item> items = new ArrayList<>();

    @Before
    public void setUp() {
        items.add(item);
    }

    @Test
    public void initializesTheItems() {
        final Database database1 = mock(Database.class);

        when(database1.getItems()).thenReturn(items);

        final MainSceneModel model1 = new MainSceneModel(database1);

        assertTrue("Item's Observable List should be initialized", model1.items.containsAll(items));

        verify(database1, atLeastOnce()).getItems();
    }

    @Test
    public void loadItemsAfterNotification() {
        when(database.getItems()).thenReturn(items);

        items.add(new Item("heading2", "content2"));

        model.dataChanged();

        assertTrue("Item's Observable List should be updated", model.items.containsAll(items));

        verify(database).addListener(model);
        verify(database, atLeast(2)).getItems();
    }

}