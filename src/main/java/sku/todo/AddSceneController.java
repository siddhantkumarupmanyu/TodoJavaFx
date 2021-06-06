package sku.todo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AddSceneController {
    @FXML
    private Button add_button;

    @FXML
    private TextArea content;

    @FXML
    private TextField heading;

    @FXML
    void addButtonClicked(ActionEvent event) {
        // todo add item
    }
}
