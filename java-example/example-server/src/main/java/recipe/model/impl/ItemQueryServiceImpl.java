package recipe.model.impl;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import recipe.model.messages.Item;
import recipe.model.messages.QueryRequest;
import recipe.model.messages.QueryResponse;
import recipe.model.services.ItemQueryService;

/**
 * Serves queries by always returning the same set of Items, no matter what's the query.
 */
public class ItemQueryServiceImpl implements ItemQueryService {
    private static final Logger log = LoggerFactory.getLogger(ItemQueryServiceImpl.class);

    @Override
    public QueryResponse query(QueryRequest request) {
        log.info("A query has been requested");
        return new QueryResponse() {
            @Override
            public ImmutableCollection<Item> results() {
                return ImmutableList.of(
                        Item.create(1, "my content"),
                        Item.create(2, "This is I am")
                );
            }
        };
    }
}
