package ch.hslu.refashioned.service;

import java.util.List;

public interface ReadService<T> {
    List<T> get();
}
