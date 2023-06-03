package com.urise.webapp.model.storage;

import com.urise.webapp.model.Resume;

import java.util.HashMap;

public class MapStorage extends AbstractStorage {
    final static HashMap <String, Resume> STORAGE = new HashMap<>();

    @Override
    protected Object getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return STORAGE.containsKey(searchKey);
    }

    @Override
    protected void doSave(Resume resume, Object searchKey) {
        STORAGE.put((String) searchKey, resume);
    }

    @Override
    protected void doUpdate(Resume resume, Object searchKey) {
        STORAGE.replace((String) searchKey, resume);
    }

    @Override
    protected void doDelete(Object searchKey) {
        STORAGE.remove(searchKey);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return STORAGE.get(searchKey);
    }

    @Override
    public void clear() {
        STORAGE.clear();
    }

    @Override
    public Resume[] getAll() {
        return STORAGE.values().toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return STORAGE.size();
    }
}
