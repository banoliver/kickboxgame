package kickboxgame;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public interface Initializable {

    default Map<Generable, Integer> initializeMap(Generable[] generableValues) {
        Map<Generable, Integer> map = new HashMap<>();
        Arrays.stream(generableValues)
                .forEach(generableValue -> map.put(generableValue, generableValue.getDefaultValue()));
        return map;
    }
}
