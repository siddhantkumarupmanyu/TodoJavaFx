package sku.todo.end_to_end_test;

import org.junit.Before;
import org.junit.Test;

public class TodoAppEndToEndTest {

    private final ApplicationDriver application = new ApplicationDriver();

    @Before
    public void setUp() throws Exception {
        application.setup();
    }

    @Test
    public void addSingleItem() {
        application.addItem("Heading", "content");
        application.isShowingItem("Heading", "content", 0);
    }

    @Test
    public void addMultipleItems() {
        application.addItem("Heading1", "Content1");
        application.addItem("Heading2", "Content2");
        application.addItem("Heading3", "Content3");

        application.itemCount(3);
        application.isShowingItem("Heading1", "Content1", 0);
        application.isShowingItem("Heading2", "Content2", 1);
        application.isShowingItem("Heading3", "Content3", 2);

    }
}
