package sku.todo.ui;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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

        main_list_view.setCellFactory(param -> {
            ListCell<Item> cell = new HeadingOnlyCell();

            ContextMenu contextMenu = createContentMenu(cell);

            cell.emptyProperty().addListener((obs, wasEmpty, isNowEmpty) -> {
                if (isNowEmpty) {
                    cell.setContextMenu(null);
                } else {
                    cell.setContextMenu(contextMenu);
                }
            });
            return cell;
        });

        main_list_view.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Item>() {
            @Override
            public void changed(ObservableValue<? extends Item> observable, Item oldValue, Item newValue) {
                if (newValue != null) {
                    main_heading.setText(newValue.heading);
                    main_content.setText(newValue.content);
                }
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
        showAddDialog(Item.emptyItem);
    }

    private void showAddDialog(Item item) throws IOException {
        FXMLLoader loader = DependencyInjection.getLoader("add_dialog_scene.fxml");
        Parent root = loader.load();

        AddDialogSceneController controller = loader.getController();
        controller.setItem(item);

        Scene scene = new Scene(root);

        Stage stage = new Stage();
        stage.setTitle("Add");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    private ContextMenu createContentMenu(ListCell<Item> cell) {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem editItem = editMenuItem(cell);
        MenuItem deleteItem = deleteMenuItem(cell);
        contextMenu.getItems().addAll(editItem, deleteItem);
        return contextMenu;
    }

    private MenuItem deleteMenuItem(ListCell<Item> cell) {
        MenuItem deleteItem = new MenuItem();
        deleteItem.setId("main_delete_context_menu");
        deleteItem.textProperty().bind(Bindings.format("Delete \"%s\"", cell.textProperty()));
        deleteItem.setOnAction(event -> model.deleteItem(cell.getItem()));
        return deleteItem;
    }

    private MenuItem editMenuItem(ListCell<Item> cell) {
        MenuItem editItem = new MenuItem();
        editItem.setId("main_edit_context_menu");
        editItem.textProperty().bind(Bindings.format("Edit \"%s\"", cell.textProperty()));
        editItem.setOnAction(event -> {
            Item item = cell.getItem();
            try {
                showAddDialog(item);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return editItem;
    }

    // I do not know how to test this
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
