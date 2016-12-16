package recipe.server;

import com.google.common.base.Throwables;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Provides;
import com.google.inject.multibindings.Multibinder;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;

import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static recipe.server.JavaLanguageUtilities.constructArray;

/**
 * Run a demo of the Jetty server with some simple services.
 */
public class ServerRunner implements Runnable {
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new ServerRunner());
    }

    public void run() {
        try {
            Injector injector = Guice.createInjector(
                    new JettyHttpServerModule(),
                    new StaticFilesJettyModule());
            Server server = injector.getInstance(Server.class);
            // Start things up! By using the server.join() the server thread will join with the current thread.
            // See "http://docs.oracle.com/javase/1.5.0/docs/api/java/lang/Thread.html#join()" for more details.
            server.start();
            server.join();
        } catch (Exception e) {
            Throwables.propagate(e);
        }
    }

    /**
     * Constructs a ResourceHandler serving static files directly from filesystem.
     * See {@linkplain http://www.eclipse.org/jetty/documentation/9.3.x/embedded-examples.html}
     */
    class JettyHttpServerModule extends AbstractModule {
        private final int port;

        public JettyHttpServerModule() {
            this(8080);
        }

        public JettyHttpServerModule(int port) {
            this.port = port;
        }

        @Override
        protected void configure() {
        }

        @Provides
        public Server createServer(Set<Handler> handlers) {
            Server server = new Server(port);
            // Add the ResourceHandler to the server.
            HandlerList handlerList = new HandlerList();
            handlerList.setHandlers(constructArray(Handler.class, handlers, new DefaultHandler()));
            server.setHandler(handlerList);
            return server;
        }
    }

    /**
     * Constructs a ResourceHandler serving static files directly from filesystem.
     * See {@linkplain http://www.eclipse.org/jetty/documentation/9.3.x/embedded-examples.html}
     */
    class StaticFilesJettyModule extends AbstractModule {
        private String webroot;
        private String[] welcomeFiles;
        private boolean showFolderListing;

        public StaticFilesJettyModule() {
            this(".", new String[]{"index.html"}, true);
        }

        public StaticFilesJettyModule(String webroot, String[] welcomeFiles, boolean showFolderListing) {
            this.webroot = webroot;
            this.welcomeFiles = welcomeFiles;
            this.showFolderListing = showFolderListing;
        }

        @Override
        protected void configure() {
            Multibinder<Handler> multi = Multibinder.newSetBinder(binder(), Handler.class);
            multi.addBinding().to(ResourceHandler.class);
        }

        @Provides
        public ResourceHandler createResourceHandler() {
            // Create the ResourceHandler. It is the object that will actually handle the request for a given file. It is
            // a Jetty Handler object so it is suitable for chaining with other handlers as you will see in other examples.
            ResourceHandler handler = new ResourceHandler();

            // Configure the ResourceHandler. Setting the resource base indicates where the files should be served out of.
            // In this example it is the current directory but it can be configured to anything that the jvm has access to.
            handler.setDirectoriesListed(showFolderListing);
            handler.setWelcomeFiles(welcomeFiles);
            handler.setResourceBase(webroot);
            return handler;
        }
    }
}