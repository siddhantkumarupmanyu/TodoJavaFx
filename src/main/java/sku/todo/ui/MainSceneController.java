package sku.todo.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainSceneController {

    @FXML
    private ListView<?> main_list_view;

    @FXML
    private Button main_add_button;

    @FXML
    private Label main_heading;

    @FXML
    private Label main_content;

    @FXML
    void applyButtonClicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("add_dialog_scene.fxml"));

        Stage stage = new Stage();

        stage.setTitle("Add");
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

}
