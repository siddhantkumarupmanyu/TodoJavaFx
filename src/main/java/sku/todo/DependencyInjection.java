package sku.todo;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class DependencyInjection {

    private static final Map<Class<?>, Callable<?>> injectionMethods = new HashMap<>();

    public static Parent load(String resource) throws IOException {
        FXMLLoader loader = getLoader(resource);
        return loader.load();
    }

    public static FXMLLoader getLoader(String resource) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getResource(resource));
        loader.setControllerFactory(DependencyInjection::constructController);
        return loader;
    }

    public static void addInjection(Class<?> controllerClass, Callable<?> callback) {
        injectionMethods.put(controllerClass, callback);
    }

    private static Object constructController(Class<?> controllerClass) {
        if (injectionMethods.containsKey(controllerClass)) {
            return loadControllerWithSavedMethod(controllerClass);
        } else {
            return loadControllerWithDefaultConstructor(controllerClass);
        }
    }

    private static URL getResource(String resource) {
        return MainApp.class.getResource("ui/" + resource);
    }

    private static Object loadControllerWithSavedMethod(Class<?> controller) {
        try {
            return injectionMethods.get(controller).call();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    private static Object loadControllerWithDefaultConstructor(Class<?> controller) {
        try {
            return controller.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new IllegalStateException(e);
        }
    }
}
