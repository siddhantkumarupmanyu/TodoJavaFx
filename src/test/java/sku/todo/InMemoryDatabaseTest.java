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

    @Test
    public void addItem_GetItem() {
        Item item = new Item("heading1", "content1");

        database.addItem(item);

        List<Item> items = database.getItems();
        assertThat(items.get(0), is(equalTo(item)));
    }

    @Test
    public void notifyDataChange() {
        DatabaseListener listener = mock(DatabaseListener.class);
        database.addListener(listener);

        database.addItem(new Item("heading2", "content2"));

        verify(listener).dataChanged();
    }

}