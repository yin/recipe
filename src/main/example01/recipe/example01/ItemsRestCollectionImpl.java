package recipe.example01;

import java.util.Collection;
import java.util.Optional;

/**
 * I am so stupid, that I forgotten to change this javadoc, me fool.
 */
public class ItemsRestCollectionImpl implements ItemsRestCollection {
    @Override
    public long size() {
        return 0;
    }

    @Override
    public Item create(Item item) {
        return null;
    }

    @Override
    public Collection<Item> get(int start, int end) {
        return null;
    }

    @Override
    public Optional<Item> get(int id) {
        return null;
    }

    @Override
    public boolean update(Item item) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
