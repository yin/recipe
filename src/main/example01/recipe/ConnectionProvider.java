package recipe;

import java.io.IOException;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

/**
 * I am so stupid, that I forgotten to change this javadoc, me fool.
 */
interface ConnectionProvider {
    Connection getConnection() throws IOException;
    Connection getConnection(ConnectionRoute subroute) throws IOException;
    interface Connection {
        WritableByteChannel getTxChannel() throws IOException;
        void flush() throws IOException;
        ReadableByteChannel getRxChannel() throws IOException;
        void close();
    }
}
