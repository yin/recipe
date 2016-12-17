package recipe.example01.server;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import recipe.example01.Item;
import recipe.example01.ItemQueryService;
import recipe.example01.QueryRequest;
import recipe.example01.QueryResponse;

/**
 * I am so stupid, that I forgotten to change this javadoc, me fool.
 */
public class ItemQueryServiceImpl implements ItemQueryService {
    @Override
    public QueryResponse query(QueryRequest request) {
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
