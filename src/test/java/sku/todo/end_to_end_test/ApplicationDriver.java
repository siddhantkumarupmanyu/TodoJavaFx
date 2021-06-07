package sku.todo.end_to_end_test;

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

    public void isShowingItem(String heading, String content, int index) {
        assertThat("Add stage/window should be closed", listWindows().size(), is(equalTo(1)));

        ListView<Item> listViewItems = getMainListView();

        assertThat(listViewItems.getItems().get(index), is(equalTo(new Item(heading, content))));

        selectItem(index, listViewItems);

        Label headingLabel = lookup("#main_heading").query();
        Label contentLabel = lookup("#main_content").query();

        assertThat(headingLabel.getText(), is(heading));
        assertThat(contentLabel.getText(), is(content));
    }

    public void itemCount(int count) {
        assertThat(getMainListView().getItems().size(), is(equalTo(count)));
    }

    private void selectItem(int index, ListView<Item> itemsList) {
        clickOn(itemsList);
        for (int i = 0; i < index; i++) {
            press(KeyCode.DOWN);
            release(KeyCode.DOWN);
        }
        press(KeyCode.ENTER);
        release(KeyCode.ENTER);
    }

    private ListView<Item> getMainListView() {
        return lookup("#main_list_view").query();
    }
}
