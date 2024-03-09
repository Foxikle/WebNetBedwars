package net.cytonic.cytonicbedwars.data.types;

import org.jetbrains.annotations.NotNull;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

// this class is completley useless execpt it was fun to make :)
public class GeneratorItems<KEY, TIME, MAX_AMOUNT> implements Iterable<Map.Entry<KEY, Pair<TIME, MAX_AMOUNT>>> {
    private final Map<KEY, Pair<TIME, MAX_AMOUNT>> map = new HashMap<>();

    public GeneratorItems(KEY key, Pair<TIME, MAX_AMOUNT> value) {
        map.put(key, value);
    }

    public void put(KEY key, TIME value1, MAX_AMOUNT value2) {
        map.put(key, new Pair<>(value1, value2));
    }

    public TIME getFirstValue(KEY key) {
        return map.get(key).value1();
    }

    public MAX_AMOUNT getSecondValue(KEY key) {
        return map.get(key).value2();
    }

    public Pair<TIME, MAX_AMOUNT> get(KEY key) {
        return map.get(key);
    }

    public Set<KEY> getKeyset() {
        return map.keySet();
    }

    @NotNull
    @Override
    public Iterator<Map.Entry<KEY, Pair<TIME, MAX_AMOUNT>>> iterator() {
        return map.entrySet().iterator();
    }
}