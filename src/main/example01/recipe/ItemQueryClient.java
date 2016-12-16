package recipe;

/**
 * I am so stupid, that I forgotten to change this javadoc, me fool.
 */
public class ItemQueryClient {
    private final ConnectionProvider connectionProvider;
    public ItemQueryClient(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }
    public QueryResponse query(QueryRequest request) {
     //   connectionProvider.getOutputChannel();
        throw new UnsupportedOperationException("this is TODO");
    }
}
