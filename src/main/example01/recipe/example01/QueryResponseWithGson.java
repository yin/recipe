package recipe.model.messages;

import com.google.auto.value.AutoValue;
import com.google.common.collect.ImmutableCollection;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import recipe.example01.AutoValue_QueryResponse;
import recipe.example01.Item;

/**
 * Represents a response to a query request.
 */
@AutoValue
public abstract class QueryResponse {
    public QueryResponse create(ImmutableCollection<Item> results) {
        return new AutoValue_QueryResponse(results);
    }
    public abstract ImmutableCollection<Item> results();

    public static TypeAdapter<QueryResponse> typeAdapter(Gson gson) {
        return new AutoValue_QueryResponse.GsonTypeAdapter(gson);
    }
}
