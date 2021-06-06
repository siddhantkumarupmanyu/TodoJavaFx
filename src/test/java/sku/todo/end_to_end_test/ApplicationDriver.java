package sku.todo.end_to_end_test;

import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import sku.todo.MainApp;

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
//        Label headingLabel = lookup("#heading_label").query();
//        Label contentLabel = lookup("#content_label").query();
//
//        assertThat(headingLabel.getText(), is(heading));
//        assertThat(contentLabel.getText(), is(content));
    }
}
