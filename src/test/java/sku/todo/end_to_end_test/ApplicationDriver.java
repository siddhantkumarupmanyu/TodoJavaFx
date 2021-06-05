package sku.todo.end_to_end_test;

import javafx.scene.control.Label;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import sku.todo.MainApp;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ApplicationDriver extends ApplicationTest {

    public void setup() throws Exception {
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(MainApp.class);
    }

    public void addItem(String heading, String content) {
        clickOn("#heading_input");
        write(heading);
        clickOn("#content_input");
        write(content);

        clickOn("#applyButton");
    }

    public void isShowingItem(String heading, String content) {
        Label headingLabel = lookup("#heading_label").query();
        Label contentLabel = lookup("#content_label").query();

        assertThat(headingLabel.getText(), is(heading));
        assertThat(contentLabel.getText(), is(content));
    }
}
