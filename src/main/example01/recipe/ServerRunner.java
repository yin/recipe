package recipe;

import com.google.common.base.Throwables;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Provides;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static recipe.JavaLanguageUtilities.constructArray;

/**
 * I am so stupid, that I forgotten to change this javadoc, me fool.
 */
public class ServerRunner implements Runnable {
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new ServerRunner());

    }

    public void run() {
        try {
            Injector injectorStaticFiles = Guice.createInjector(new StaticFilesJettyModule());
            Handler staticFilesHandler = injectorStaticFiles.getInstance(Handler.class);
            Injector injector = Guice.createInjector(
                    new JettyHttpServerModule(ImmutableList.of(staticFilesHandler)));
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
        private final ImmutableCollection<Handler> handlers;

        public JettyHttpServerModule(ImmutableCollection<Handler> handlers) {
            this.handlers = handlers;
        }

        @Override
        protected void configure() {
        }

        @Provides
        public Server createServer() {
            Server server = new Server(8080);
            // Add the ResourceHandler to the server.
            HandlerList handlers = new HandlerList();
            handlers.setHandlers(constructArray(Handler.class, this.handlers, new DefaultHandler()));
            server.setHandler(handlers);
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
            bind(Handler.class).to(ResourceHandler.class);
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