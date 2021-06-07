package sku.todo.end_to_end_test;

import javafx.collections.ObservableList;
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

    public void isShowingItem(String heading, String content) {
        ListView<Item> itemsList = lookup("#main_list_view").query();

        ObservableList<Item> items = itemsList.getItems();
        assertThat(items.size(), is(equalTo(1)));

//        Label headingLabel = lookup("#heading_label").query();
//        Label contentLabel = lookup("#content_label").query();
//
//        assertThat(headingLabel.getText(), is(heading));
//        assertThat(contentLabel.getText(), is(content));
    }
}
