package recipe;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.channels.Channel;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
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
        throw new UnsupportedOperationException("this is TODO");
    }

    long pages() {
        return 0L;
    }

    Collection<Item> get(int page) throws IOException {
        ConnectionProvider.Connection connection = connectionProvider.getConnection();
        WritableByteChannel tx = connection.getTxChannel();
        connection.flush();
        ReadableByteChannel rx = connection.getRxChannel();
        Reader reader = Channels.newReader(rx, "UTF-8");

        Gson g = new Gson();
        //g.
        return null;
    }

    Optional<Item> getById(int id) {
        throw new UnsupportedOperationException("this is TODO");
    }

    boolean update(Item item) {
        throw new UnsupportedOperationException("this is TODO");
    }

    boolean delete(int id) {
        throw new UnsupportedOperationException("this is TODO");
    }
}
