package sku.todo.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sku.todo.Item;

public class AddDialogSceneController {

    private final AddDialogSceneModel model;

    public AddDialogSceneController(AddDialogSceneModel model) {
        this.model = model;
    }

    public void setItem(Item item) {
        model.setItem(item);
        dialog_heading_textfield.setText(item.heading);
        dialog_content_textarea.setText(item.content);
    }

    @FXML
    private Button dialog_add_button;

    @FXML
    private TextArea dialog_content_textarea;

    @FXML
    private TextField dialog_heading_textfield;

    @FXML
    void addButtonClicked(ActionEvent event) {
        model.editSaveOrAdd(dialog_heading_textfield.getText(), dialog_content_textarea.getText());
        closeStage();
    }

    private void closeStage() {
        Stage stage = (Stage) dialog_add_button.getScene().getWindow();
        stage.close();
    }
}
