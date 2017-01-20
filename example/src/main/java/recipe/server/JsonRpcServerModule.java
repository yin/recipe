package recipe.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.BindingAnnotation;
import com.google.inject.Module;
import com.google.inject.Provides;
import com.google.inject.multibindings.Multibinder;
import com.googlecode.jsonrpc4j.JsonRpcServer;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import recipe.io.JacksonModule;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Constructs a json-rpc server and an adapter for jetty.
 * See {@linkplain http://www.eclipse.org/jetty/documentation/9.3.x/embedded-examples.html}
 */
class JsonRpcServerModule extends AbstractModule {
    private final Module serviceModule;
    private static final Logger log  = LoggerFactory.getLogger(JsonRpcServerModule.class);

    /** Marks the backend service instance receiving RPC calls. */
    @BindingAnnotation
    @Retention(RUNTIME) @Target({FIELD, PARAMETER, METHOD})
    public @interface RpcService {
    }

    /** Marks the service interface implemented by the service. */
    @BindingAnnotation
    @Retention(RUNTIME) @Target({FIELD, PARAMETER, METHOD})
    public @interface RpcInterface {
    }

    public JsonRpcServerModule(Module serviceModule) {
        log.info("jsonrpc module created");
        this.serviceModule = serviceModule;
    }

    @Override
    protected void configure() {
        log.info("jsonrpc module config, with service-module: {}", serviceModule);
        install(serviceModule);
        install(new JacksonModule());
        Multibinder<Handler> multi = Multibinder.newSetBinder(binder(), Handler.class);
        multi.addBinding().to(JsonRpcJettyHandler.class);
    }

    @Provides
    public JsonRpcServer createJsonRpc(ObjectMapper mapper, @RpcService Object service, @RpcInterface Class iface) {
        log.info("creating jsonrpc server from service: {}", service);
        JsonRpcServer handler = new JsonRpcServer(mapper, service, iface);
        return handler;
    }

    @Provides
    public JsonRpcJettyHandler createRpcHandler(JsonRpcServer jsonrpc) {
        log.info("creating jetty handler for jsonrpc server: {}", jsonrpc);
        return new JsonRpcJettyHandler(jsonrpc);
    }
}
