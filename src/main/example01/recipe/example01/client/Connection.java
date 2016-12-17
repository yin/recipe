package recipe.client;

import java.io.IOException;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

/**
 * Manages a single network connection in a strict Tx->Rx workflow.
 */
public interface Connection {
    WritableByteChannel getTxChannel() throws IOException;

    void flush() throws IOException;

    ReadableByteChannel getRxChannel() throws IOException;

    void close();
}
