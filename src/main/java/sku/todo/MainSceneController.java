package sku.todo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class MainSceneController {

    @FXML
    private ListView<?> list_view;

    @FXML
    private Button add_button;

    @FXML
    private Label heading;

    @FXML
    private Label content;

    @FXML
    void applyButtonClicked(ActionEvent event) {
        // open the add stage
    }

    private void setTextOnLabel(Label label, String text) {
        label.setText(text);
    }
}
