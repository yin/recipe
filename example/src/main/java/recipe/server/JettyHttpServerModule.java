package recipe.server;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

import static recipe.server.JavaLanguageUtilities.constructArray;

/**
 * Constructs a ResourceHandler serving static files directly from filesystem.
 * See {@linkplain http://www.eclipse.org/jetty/documentation/9.3.x/embedded-examples.html}
 */
class JettyHttpServerModule extends AbstractModule {
    private final int port;
    private static final Logger log = LoggerFactory.getLogger(JettyHttpServerModule.class);

    public JettyHttpServerModule() {
        this(8080);
    }

    public JettyHttpServerModule(int port) {
        this.port = port;
    }

    @Override
    protected void configure() {
        log.info("jetty module config");
    }

    @Provides
    public Server createServer(Set<Handler> handlers) {
        log.info("creating server with handlers: {}", handlers);
        Server server = new Server(port);
        // Add the ResourceHandler to the server.
        HandlerList handlerList = new HandlerList();
        handlerList.setHandlers(constructArray(Handler.class, handlers, new DefaultHandler()));
        server.setHandler(handlerList);
        return server;
    }
}
