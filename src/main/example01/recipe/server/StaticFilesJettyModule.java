package recipe.server;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.multibindings.Multibinder;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.handler.ResourceHandler;

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
