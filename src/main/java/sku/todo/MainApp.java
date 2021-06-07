package sku.todo;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sku.todo.ui.AddDialogSceneController;
import sku.todo.ui.AddDialogSceneModel;
import sku.todo.ui.MainSceneController;
import sku.todo.ui.MainSceneModel;

import java.util.concurrent.Callable;

public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        setUpDependencyInjector();

        Parent root = DependencyInjection.load("main_scene.fxml");
        primaryStage.setTitle("Todo App");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    private void setUpDependencyInjector() {
        final InMemoryDatabase database = new InMemoryDatabase();

        final MainSceneModel mainSceneModel = new MainSceneModel(database);
        Callable<MainSceneController> mainSceneFactory = () -> new MainSceneController(mainSceneModel);
        DependencyInjection.addInjection(MainSceneController.class, mainSceneFactory);

        final AddDialogSceneModel addDialogSceneModel = new AddDialogSceneModel(database);
        Callable<AddDialogSceneController> dialogSceneFactory = () -> new AddDialogSceneController(addDialogSceneModel);
        DependencyInjection.addInjection(AddDialogSceneController.class, dialogSceneFactory);

    }

    public static void main(String[] args) {
        launch(args);
    }
}
