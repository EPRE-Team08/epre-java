package ch.hslu.refashioned.service;

import java.util.List;

public interface ReadService<T, P> {
    List<T> getAll();

    T getById(P id);
}
