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
        application.isShowingItem("Heading", "content");
    }
}
