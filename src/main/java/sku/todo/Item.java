package sku.todo;

import java.util.Objects;

public class Item {

    public final int id;
    public final String heading;
    public final String content;

    public Item(int id, String heading, String content) {
        this.id = id;
        this.heading = heading;
        this.content = content;
    }

    public Item(String heading, String content) {
        this.id = -1;
        this.heading = heading;
        this.content = content;
    }

    public boolean isEmpty() {
        return heading.isEmpty() && content.isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return id == item.id && heading.equals(item.heading) && content.equals(item.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(heading, content);
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", heading='" + heading + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public static Item emptyItem = new Item(-1, "", "");

}
