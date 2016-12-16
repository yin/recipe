package recipe;

import java.util.Collection;
import java.util.Optional;

public interface ItemsRestCollection {
    long size();
    Item create(Item item);
    Collection<Item> get(int start, int end);
    Optional<Item> get(int id);
    boolean update(Item item);
    boolean delete(int id);
}

