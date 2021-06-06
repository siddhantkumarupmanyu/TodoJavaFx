package sku.todo;

import java.util.Objects;

public class Item {

    private final String heading;
    private final String content;

    public Item(String heading, String content) {
        this.heading = heading;
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return heading.equals(item.heading) && content.equals(item.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(heading, content);
    }
}
