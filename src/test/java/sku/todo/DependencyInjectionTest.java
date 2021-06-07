package sku.todo;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import org.junit.Test;
import sku.todo.ui.TestSceneController;

import java.io.IOException;
import java.util.concurrent.Callable;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

// This is a integration Test
public class DependencyInjectionTest {

    @Test
    public void loadControllerWithSavedMethod() throws IOException {
        final TestSceneController testSceneController = new TestSceneController(5);
        Callable<TestSceneController> testSceneControllerFactory = () -> testSceneController;

        DependencyInjection.addInjection(TestSceneController.class, testSceneControllerFactory);

        FXMLLoader loader = DependencyInjection.getLoader("test_scene.fxml");
        loader.load();

        assertThat(loader.getController().getClass(), is(TestSceneController.class));
        assertThat(loader.getController(), is(testSceneController));
    }

    @Test
    public void loadControllerWithDefaultConstructor() throws IOException {
        FXMLLoader testSceneLoader = DependencyInjection.getLoader("test_scene.fxml");
        testSceneLoader.load();
        assertThat(testSceneLoader.getController().getClass(), is(TestSceneController.class));
    }

    @Test
    public void loadMethod() throws IOException {
        Parent root = DependencyInjection.load("test_scene.fxml");
        assertThat(root.getClass(), is(Pane.class));
    }

}

// Since, DependencyInjection class incorporates JavaFx classes we will call this a integration test

// references: https://edencoding.com/dependency-injection/

// https://stackoverflow.com/questions/11273773/javafx-2-1-toolkit-not-initialized
// https://stackoverflow.com/a/53760312
//  - IDK, but now it does not require it.
//  - Still just for future note.