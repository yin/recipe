package recipe.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Provides;
import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import com.googlecode.jsonrpc4j.ProxyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import recipe.io.JacksonModule;
import recipe.model.messages.Query;
import recipe.model.messages.QueryRequest;
import recipe.model.messages.QueryResponse;
import recipe.model.services.ItemQueryService;
import sun.util.resources.cldr.gl.LocaleNames_gl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Runs a REST client on url provided from cmdline.
 */
public class ClientRunner implements Runnable {
    private static final boolean DEBUG = true;
    private static final Logger log = LoggerFactory.getLogger(ClientRunner.class);
    private final URL url;

    public static void main(String[] args) throws IOException, InterruptedException {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new ClientRunner(parseArgs(args)));
        executor.shutdown();
        executor.awaitTermination(Integer.MAX_VALUE, TimeUnit.DAYS);
    }

    private static URL parseArgs(String[] args) throws MalformedURLException, IllegalArgumentException {
        if (args.length == 1) {
            return new URL(args[0]);
        } else {
            throw new IllegalArgumentException("Fuck ya, args broke, biyatch!");
        }
    }

    public ClientRunner(URL url) {
        this.url = checkNotNull(url);
    }

    @Override
    public void run() {
        Injector injector = Guice.createInjector(new ClientModule());
        ItemQueryService client = injector.getInstance(ItemQueryService.class);
        Query query = Query.create(3, 5);
        QueryRequest request = QueryRequest.create(query);
        QueryResponse response = client.query(request);
        log.info("query result: {}", response.results());
    }

    class ClientModule extends AbstractModule {
        @Override
        protected void configure() {
            install(new JacksonModule());
        }

        @Provides
        public JsonRpcHttpClient createJsonRpcHttpClient(ObjectMapper mapper) {
            return new JsonRpcHttpClient(mapper, url, ImmutableMap.of());
        }

        @Provides
        public ItemQueryService createItemQueryClient(JsonRpcHttpClient client) {
            ItemQueryService proxy =
                    ProxyUtil.createClientProxy(getClass().getClassLoader(), ItemQueryService.class, client);
            return proxy;
        }
    }
}
