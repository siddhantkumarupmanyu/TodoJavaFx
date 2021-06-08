package sku.todo;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class InMemoryDatabaseTest {

    private final InMemoryDatabase database = new InMemoryDatabase();

    // should this test know about the implementation of generation of id??
    @Test
    public void addItem_GetItem() {
        Item item0 = new Item("heading1", "content1");
        Item item1 = new Item("heading1", "content1");

        database.addItem(item0);
        database.addItem(item1);

        List<Item> items = database.getItems();
        assertThat(items.get(0), is(equalTo(new Item(0, "heading1", "content1"))));
        assertThat(items.get(1), is(equalTo(new Item(1, "heading1", "content1"))));
    }

    @Test
    public void saveEditedItem() {
        database.addItem(new Item("heading1", "content1"));
        database.addItem(new Item("heading1", "content1"));
        database.addItem(new Item("heading1", "content1"));

        Item item0 = database.getItems().get(1);

        Item editedItem = new Item(item0.id, "edited", "edited");

        database.saveEdit(editedItem);

        List<Item> items = database.getItems();
        assertThat(items.get(1), is(equalTo(editedItem)));
    }

    @Test
    public void notifyDataChange() {
        DatabaseListener listener = mock(DatabaseListener.class);
        database.addListener(listener);

        database.addItem(new Item("heading2", "content2"));

        verify(listener).dataChanged();
    }

}