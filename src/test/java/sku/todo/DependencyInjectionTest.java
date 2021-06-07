package sku.todo;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import sku.todo.ui.AddDialogSceneController;
import sku.todo.ui.MainSceneController;
import sku.todo.ui.MainSceneModel;

import java.io.IOException;
import java.util.concurrent.Callable;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

// This is a integration Test
public class DependencyInjectionTest {

    @BeforeClass
    public static void setUp() {
        Platform.startup(() -> {
            // https://stackoverflow.com/a/53760312
            // This block will be executed on JavaFX Thread
        });
    }

    @AfterClass
    public static void tearDown() {
        Platform.exit();
    }

    @Test
    public void loadControllerWithSavedMethod() throws IOException {
        final MainSceneController mainSceneController = new MainSceneController(new MainSceneModel(new InMemoryDatabase()));
        Callable<MainSceneController> mainSceneControllerMethod = () -> mainSceneController;

        DependencyInjection.addInjection(MainSceneController.class, mainSceneControllerMethod);

        FXMLLoader mainSceneLoader = DependencyInjection.getLoader("main_scene.fxml");
        mainSceneLoader.load();

        assertThat(mainSceneLoader.getController().getClass(), is(MainSceneController.class));
        assertThat(mainSceneLoader.getController(), is(mainSceneController));
    }

    @Test
    public void loadControllerWithDefaultConstructor() throws IOException {
        FXMLLoader addSceneLoader = DependencyInjection.getLoader("add_dialog_scene.fxml");
        addSceneLoader.load();
        assertThat(addSceneLoader.getController().getClass(), is(AddDialogSceneController.class));
    }

    @Test
    public void loadMethod() throws IOException {
        Parent root = DependencyInjection.load("add_dialog_scene.fxml");
        assertThat(root.getClass(), is(VBox.class));
    }

}

// Since, DependencyInjection class incorporates JavaFx classes we will call this a integration test
// After Including Platform.start and exit this is definitely a integration test

// references: https://edencoding.com/dependency-injection/
// https://stackoverflow.com/questions/11273773/javafx-2-1-toolkit-not-initialized