package sku.todo;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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

        when(database.getItems()).thenReturn(items);

        final MainSceneModel model1 = new MainSceneModel(database1);

        verify(database1, atLeastOnce()).getItems();
    }

    @Test
    public void loadItemsAfterNotification() {
        when(database.getItems()).thenReturn(items);

        items.add(new Item("heading2", "content2"));

        model.dataChanged();

        verify(database).addListener(model);
        verify(database, atLeast(2)).getItems();
    }

}