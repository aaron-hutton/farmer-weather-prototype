package uk.ac.cam.cl.interaction_design.group19.app.util;

/**
 * Getter / Setter interface to abstract fields
 * where implementation should be opaque to clients
 *
 * @param <T> type of the represented field
 */
public interface Property<T> {
    T get();
    
    void set(T v);
}
