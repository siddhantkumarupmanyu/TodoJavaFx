package sku.todo.end_to_end_test;

import javafx.scene.control.Cell;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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

        clickOn(getListCell(listViewItems, index));

        Label headingLabel = lookup("#main_heading").query();
        Label contentLabel = lookup("#main_content").query();

        assertThat(headingLabel.getText(), is(heading));
        assertThat(contentLabel.getText(), is(content));
    }

    public void itemCount(int count) {
        assertThat(getMainListView().getItems().size(), is(equalTo(count)));
    }

    public void deleteItem(int index) {
        ListView<Item> listView = getMainListView();

        Cell<Item> cell = getListCell(listView, index);

        rightClickOn(cell);

        clickOn("#main_delete_context_menu");
    }

    public void editItem(int index, String editedHeading, String editedContent) {
        ListView<Item> listView = getMainListView();

        Cell<Item> cell = getListCell(listView, index);

        rightClickOn(cell);

        clickOn("#main_edit_context_menu");

        clickOn("#dialog_heading_textfield");
        write(editedHeading);
        clickOn("#dialog_content_textarea");
        write(editedContent);
        clickOn("#dialog_add_button");
    }

    private ListView<Item> getMainListView() {
        return lookup("#main_list_view").query();
    }

    // https://stackoverflow.com/a/52754518
    public Cell<Item> getListCell(ListView<Item> list, int index) {
        Object[] cells = list.lookupAll(".cell").toArray();
        return (Cell<Item>) cells[index];
    }
}
