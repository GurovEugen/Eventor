

import javax.ws.rs.core.Application;
import javax.ws.rs.ApplicationPath;
import java.util.HashSet;
import java.util.Set;


@ApplicationPath("/api")
public class App extends Application {
        public Set<Class<?>> getClasses() {
        HashSet h = new HashSet<Class<?>>();
        h.add(WebController.class);
        return h;
    }
}