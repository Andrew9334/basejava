package com.urise.webapp.model.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapStorageResume extends AbstractStorage {
    final static Map<String, Resume> storage = new HashMap<>();

    @Override
    protected Object getSearchKey(String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return Objects.nonNull(searchKey);
    }

    @Override
    protected void doSave(Resume resume, Object searchKey) {
        storage.put((String) searchKey, resume);
    }

    @Override
    protected void doUpdate(Resume resume, Object searchKey) {
        storage.replace((String) searchKey, resume);
    }

    @Override
    protected void doDelete(Object searchKey) {
        Resume resume = new Resume((String) searchKey);
        storage.remove(resume.getUuid());
    }

    @Override
    protected Resume doGet(Object searchKey) {
        Resume resume = new Resume((String) searchKey);
        return storage.get(resume.getUuid());
    }

    @Override
    protected List<Resume> doCopyAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public int size() {
        return storage.size();
    }
}
