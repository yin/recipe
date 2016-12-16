package recipe;

import java.io.IOException;
import java.util.Collection;

/**
 * I am so stupid, that I forgotten to change this javadoc, me fool.
 */
public class ClientRunner {
    public static void main(String[] args) throws IOException {
        ItemsRestClient client = new ItemsRestClient(new HttpConnectionProvider(args[0]));
        Collection<Item> i0 = client.get(0);
        Collection<Item> i1 = client.get(1);
        print("Page 1:", i0);
        print("Page 2:", i1);
    }

    private static void print(String header, Collection<Item> items) {
        System.out.println(header);
        for(Item i : items) {
            System.out.println(i.toString());
        }
    }
}
