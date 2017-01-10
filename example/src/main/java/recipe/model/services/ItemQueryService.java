package recipe.model.services;

import recipe.model.messages.QueryRequest;
import recipe.model.messages.QueryResponse;

/**
 * Answers queries for the model 'Item' objects.
 */
public interface ItemQueryService {
    QueryResponse query(QueryRequest request);
}
