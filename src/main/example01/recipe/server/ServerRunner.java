package recipe.server;

import com.google.common.base.Throwables;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.eclipse.jetty.server.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Run a demo of the Jetty server with some simple services.
 */
public class ServerRunner implements Runnable {
    private static final Logger log = LoggerFactory.getLogger(ServerRunner.class);
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new ServerRunner());
    }

    public void run() {
        try {
            if (log.isInfoEnabled()) {
                log.info("Initializing server");
            }
            Injector injector = Guice.createInjector(
                    new JettyHttpServerModule(),
                    new JsonRpcServerModule(new ItemRpcModule()));
            Server server = injector.getInstance(Server.class);
            // Start things up! By using the server.join() the server thread will join with the current thread.
            // See "http://docs.oracle.com/javase/1.5.0/docs/api/java/lang/Thread.html#join()" for more details.
            if (log.isInfoEnabled()) {
                log.info("Starting server");
            }
            server.start();
            server.join();
            if (log.isInfoEnabled()) {
                log.info("Server ended");
            }
        } catch (Exception e) {
            Throwables.propagate(e);
        }
    }

}