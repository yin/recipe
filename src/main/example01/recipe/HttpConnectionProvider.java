package recipe;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

import com.google.common.io.Closeables;
import com.google.common.net.HttpHeaders;
import sun.net.www.http.HttpClient;

/**
 * I am so stupid, that I forgotten to change this javadoc, me fool.
 */
public class HttpConnectionProvider implements ConnectionProvider {

    private static final String USER_AGENT = "Mozilla/5.0";
    private static final ConnectionRoute NULL_SUBROUTE = ConnectionRoute.create("");

    private final String baseUrl;

    public HttpConnectionProvider(String baseUrl) {
        this.baseUrl = baseUrl.endsWith("/") ? baseUrl : baseUrl + '/';
    }

    @Override
    public Connection getConnection() throws IOException {
        return getOutputChannel(NULL_SUBROUTE);
    }

    @Override
    public Connection getConnection(ConnectionRoute subroute) throws IOException {
        URL url = new URL(baseUrl + subroute.subpath());
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty(HttpHeaders.USER_AGENT, USER_AGENT);
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        con.setDoOutput(true);
        return Channels.newChannel(con.getOutputStream());
    }

    public void c() {
        HttpClient c = HttpClient.New("http://localhost:8080");
        c.
    }

    static class HttpConnection implements Connection {
        private final URL url;
        private HttpURLConnection httpConnection;
        private OutputStream out;
        private WritableByteChannel tx;
        private InputStream in;
        private ReadableByteChannel rx;

        public HttpConnection(String url) throws MalformedURLException {
            this.url = new URL(url);
        }

        @Override
        public WritableByteChannel getTxChannel() {
            synchronized (this) {
                if (tx == null) {
                    tx = Channels.newChannel(getOut());
                }
            }
            return tx;
        }

        @Override
        public void flush() throws IOException {
            synchronized (this) {
                if (out != null) {
                    out.flush();

                }
            }
        }

        @Override
        public ReadableByteChannel getRxChannel() {
            return null;
        }

        @Override
        public void close() {
            synchronized (this) {
                closeOutNonThreadSafe();
                closeInNonThreadSafe();
            }
        }

        private void closeInNonThreadSafe() {
            if (in != null) {
                Closeables.closeQuietly(in);
                this.in = null;
            }
        }

        private void closeOutNonThreadSafe() throws IOException {
            if (out != null) {
                Closeables.close(out, true);
                this.out = null;
            }
        }

        private void closeInNonThreadSafe_2() {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
            } finally {
                in = null;
            }
        }

        private void closeOutNonThreadSafe_2() {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException ex) {
            } finally {
                out = null;
            }
        }

        public OutputStream getOut() throws IOException {
            synchronized (this) {
                if (out == null) {
                    out = httpConnection().getOutputStream();
                }
            }
            return out;
        }

        private HttpURLConnection httpConnection() throws IOException {
            synchronized (this) {
                if (httpConnection == null) {
                    httpConnection = (HttpURLConnection) url.openConnection();
                    configHttpConnection(httpConnection);
                }
            }
            return httpConnection;
        }

        private URLConnection openHttpConnection() throws IOException {
            return url.openConnection();
        }

        private void configHttpConnection(HttpURLConnection con) throws ProtocolException {
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", USER_AGENT);
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            con.setDoOutput(true);
        }
    }
}
