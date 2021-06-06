package sku.todo;

import java.util.EventListener;

public interface DatabaseListener extends EventListener {
    void dataChanged();
}
