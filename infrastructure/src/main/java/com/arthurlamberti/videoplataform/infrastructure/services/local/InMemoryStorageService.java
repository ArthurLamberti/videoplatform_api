package com.arthurlamberti.videoplataform.infrastructure.services.local;

import com.arthurlamberti.videoplataform.domain.resource.Resource;
import com.arthurlamberti.videoplataform.infrastructure.services.StorageService;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryStorageService implements StorageService {

    private final Map<String, Resource> storage;

    public InMemoryStorageService() {
        this.storage = new ConcurrentHashMap<>();
    }

    public Map<String, Resource> storage() {
        return this.storage;
    }

    public void clear() {
        this.storage.clear();
    }

    @Override
    public void store(String name, Resource resource) {
        this.storage.put(name, resource);
    }

    @Override
    public Optional<Resource> get(String name) {
        return Optional.ofNullable(this.storage.get(name));
    }

    @Override
    public void deleteAll(Collection<String> names) {
        names.forEach(this.storage::remove);
    }

    @Override
    public List<String> list(String prefix) {
        if (prefix == null)
            return null;
        return this.storage.keySet().stream()
                .filter(it -> it.startsWith(prefix))
                .toList();
    }
}
