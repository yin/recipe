package recipe.client;

import java.io.IOException;

/**
 * Provides network connections of a particular type.
 */
public interface ConnectionProvider {
    Connection getConnection() throws IOException;
    Connection getConnection(ConnectionRoute subroute) throws IOException;
}
