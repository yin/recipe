package recipe.example01;

import com.google.gson.Gson;
import recipe.client.Connection;
import recipe.client.ConnectionProvider;

import java.io.IOException;
import java.io.Reader;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.util.Collection;
import java.util.Optional;

/**
 * Fetches Items from a RESTful collection.
 */
public class ItemsRestClient {
    private final ConnectionProvider connectionProvider;

    public ItemsRestClient(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    public Item create(Item item) {
        throw new UnsupportedOperationException("this is TODO");
    }

    public long pages() {
        return 0L;
    }

    public Collection<Item> get(int page) throws IOException {
        Connection connection = connectionProvider.getConnection();
        WritableByteChannel tx = connection.getTxChannel();
        connection.flush();
        ReadableByteChannel rx = connection.getRxChannel();
        Reader reader = Channels.newReader(rx, "UTF-8");

        Gson g = new Gson();
        QueryResponse resp = g.fromJson(reader, QueryResponse.class);
        return resp.results();
    }

    public Optional<Item> getById(int id) {
        throw new UnsupportedOperationException("this is TODO");
    }

    public boolean update(Item item) {
        throw new UnsupportedOperationException("this is TODO");
    }

    public boolean delete(int id) {
        throw new UnsupportedOperationException("this is TODO");
    }
}
