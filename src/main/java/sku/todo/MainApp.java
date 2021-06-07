package sku.todo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Callback;
import sku.todo.ui.MainSceneModel;

import java.lang.reflect.InvocationTargetException;

public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
//        Parent root = FXMLLoader.load(MainSceneModel.class.getResource("main_scene.fxml"));

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainSceneModel.class.getResource("main_scene.fxml"));
//        loader.setController(new MainSceneController(new MainSceneModel(new InMemoryDatabase())));
        loader.setControllerFactory(controllerFactory);

        Parent root = loader.load();
        
        primaryStage.setTitle("Todo App");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    Callback<Class<?>, Object> controllerFactory = new Callback<>() {
        @Override
        public Object call(Class<?> param) {
//            return new MainSceneController(new MainSceneModel(new InMemoryDatabase()));
            try {
                return param.getConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
                throw new IllegalStateException(e);
            }
        }
    };

    public static void main(String[] args) {
        launch(args);
    }
}
