package recipe;

import java.io.IOException;
import java.io.Writer;
import java.nio.channels.Channel;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;
import java.util.Collection;
import java.util.Optional;

/**
 * I am so stupid, that I forgotten to change this javadoc, me fool.
 */
public class ItemsRestClient {
    private final ConnectionProvider connectionProvider;

    public ItemsRestClient(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    Item create(Item item) {
        return null;
    }

    long pages() {
        return 0L;
    }

    Collection<Item> get(int page) throws IOException {
        WritableByteChannel ch = connectionProvider.getConnection()
    }

    Optional<Item> getById(int id) {
    }

    boolean update(Item item) {
    }

    boolean delete(int id) {
    }
}
