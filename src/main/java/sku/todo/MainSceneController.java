package sku.todo;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MainSceneController {

    public TextField heading_input;
    public TextField content_input;
    public Label heading_label;
    public Label content_label;

    public Button applyButton;

    public void applyButtonClicked(ActionEvent actionEvent) {
        setTextOnLabel(heading_label, heading_input.getText());
        setTextOnLabel(content_label, content_input.getText());
    }

    private void setTextOnLabel(Label label, String text) {
        label.setText(text);
    }
}
