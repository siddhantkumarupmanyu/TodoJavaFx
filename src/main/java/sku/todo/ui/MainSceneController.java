package sku.todo.ui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sku.todo.DependencyInjection;
import sku.todo.Item;

import java.io.IOException;

public class MainSceneController {

    private final MainSceneModel model;

    public MainSceneController(MainSceneModel model) {
        this.model = model;
    }

    @FXML
    public void initialize() {
        main_list_view.setItems(model.items);

        // I do not know how to test this
        main_list_view.setCellFactory(param -> new HeadingOnlyCell());

        main_list_view.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Item>() {
            @Override
            public void changed(ObservableValue<? extends Item> observable, Item oldValue, Item newValue) {
                main_heading.setText(newValue.heading);
                main_content.setText(newValue.content);
            }
        });
    }

    @FXML
    private ListView<Item> main_list_view;

    @FXML
    private Button main_add_button;

    @FXML
    private Label main_heading;

    @FXML
    private Label main_content;

    @FXML
    void applyButtonClicked(ActionEvent event) throws IOException {
        Parent root = DependencyInjection.load("add_dialog_scene.fxml");

        Stage stage = new Stage();

        stage.setTitle("Add");
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    private static class HeadingOnlyCell extends ListCell<Item> {

        // from createDefaultCellImpl in ListViewSkin.java in package javafx.scene.control.skin;
        @Override
        public void updateItem(Item item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                setText(item == null ? "null" : item.heading);
                setGraphic(null);
            }
        }
    }

}
