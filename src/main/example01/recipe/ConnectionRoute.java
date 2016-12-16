package recipe;

import com.google.auto.value.AutoValue;

/**
 * I am so stupid, that I forgotten to change this javadoc, me fool.
 */
@AutoValue
public abstract class ConnectionRoute {
    public static ConnectionRoute create(String subpath) {
        return new AutoValue_ConnectionRoute(subpath);
    }
    public abstract String subpath();
}
