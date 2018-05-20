package uk.ac.cam.cl.interaction_design.group19.app.util;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class PropertyFactory {
    public static <T> Property<T> createProperty(Supplier<T> get, Consumer<T> set) {
        return new Property<T>() {
            @Override
            public T get() {
                return get.get();
            }
            
            @Override
            public void set(T v) {
                set.accept(v);
            }
        };
    }
}
