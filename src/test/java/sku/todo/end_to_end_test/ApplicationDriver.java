package sku.todo.end_to_end_test;

import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import sku.todo.Item;
import sku.todo.MainApp;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class ApplicationDriver extends ApplicationTest {

    public void setup() throws Exception {
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(MainApp.class);
    }

    public void addItem(String heading, String content) {
        clickOn("#main_add_button");

        clickOn("#dialog_heading_textfield");
        write(heading);
        clickOn("#dialog_content_textarea");
        write(content);
        clickOn("#dialog_add_button");
    }

    public void isShowingItem(String heading, String content) {
        assertThat("Add stage/window should be closed", listWindows().size(), is(equalTo(1)));

        ListView<Item> itemListView = lookup("#main_list_view").query();

        ObservableList<Item> items = itemListView.getItems();
        assertThat(items.size(), is(equalTo(1)));

        assertThat(items.get(0), is(equalTo(new Item(heading, content))));

        selectTheFirstItem(itemListView);

        Label headingLabel = lookup("#main_heading").query();
        Label contentLabel = lookup("#main_content").query();

        assertThat(headingLabel.getText(), is(heading));
        assertThat(contentLabel.getText(), is(content));
    }

    private void selectTheFirstItem(ListView<Item> itemsList) {
        clickOn(itemsList);
        press(KeyCode.DOWN);
        release(KeyCode.DOWN);
        press(KeyCode.ENTER);
        release(KeyCode.ENTER);
    }
}
