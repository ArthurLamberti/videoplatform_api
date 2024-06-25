package com.arthurlamberti.videoplataform.infrastructure.services;

import com.arthurlamberti.videoplataform.domain.video.Resource;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface StorageService {
    void store(String name, Resource resource);
    Optional<Resource> get(String name);
    void deleteAll(Collection<String> names);
    List<String> list(String prefix);
}
