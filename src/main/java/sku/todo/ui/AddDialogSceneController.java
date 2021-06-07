package sku.todo.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AddDialogSceneController {

    private final AddDialogSceneModel model;

    public AddDialogSceneController(AddDialogSceneModel model) {
        this.model = model;
    }

    @FXML
    private Button dialog_add_button;

    @FXML
    private TextArea dialog_content_textarea;

    @FXML
    private TextField dialog_heading_textfield;

    @FXML
    void addButtonClicked(ActionEvent event) {

    }
}
