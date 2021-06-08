package sku.todo;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;


public class ItemTest {

    @Test
    public void valueObject() {
        Item item1a = new Item("Heading1", "content1");
        Item item1b = new Item("Heading1", "content1");
        Item item2 = new Item("Heading2", "content1");
        Item item3 = new Item("Heading1", "content2");

        assertThat(item1b, is(equalTo(item1a)));
        assertThat(item2, is(not(equalTo(item1a))));
        assertThat(item3, is(not(equalTo(item1a))));
    }

    @Test
    public void isEmpty() {
        assertTrue(Item.emptyItem.isEmpty());
    }

}