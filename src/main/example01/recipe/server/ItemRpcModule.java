package recipe.server;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import recipe.example01.ItemQueryService;
import recipe.example01.server.ItemQueryServiceImpl;

/**
 * Constructs an Item query service.
 */
class ItemRpcModule extends AbstractModule {
    private Logger log = LoggerFactory.getLogger(ItemRpcModule.class);

    @Override
    protected void configure() {
        log.info("item rpc config");
        bind(Object.class).annotatedWith(JsonRpcServerModule.RpcService.class).to(ItemQueryServiceImpl.class);
    }

    @Provides
    public ItemQueryService createRpcService() {
        log.info("item rpc config");
        return new ItemQueryServiceImpl();
    }

}
