package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MapStorage extends AbstractStorage<String> {
    final static HashMap <String, Resume> STORAGE = new HashMap<>();

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean isExist(String searchKey) {
        return STORAGE.containsKey(searchKey);
    }

    @Override
    protected void doSave(Resume resume, String searchKey) {
        STORAGE.put(searchKey, resume);
    }

    @Override
    protected void doUpdate(Resume resume, String searchKey) {
        STORAGE.replace(searchKey, resume);
    }

    @Override
    protected void doDelete(String searchKey) {
        STORAGE.remove(searchKey);
    }

    @Override
    protected Resume doGet(String searchKey) {
        return STORAGE.get(searchKey);
    }

    @Override
    protected List<Resume> doCopyAll() {
        return new ArrayList<>(STORAGE.values());
    }

    @Override
    public void clear() {
        STORAGE.clear();
    }

    @Override
    public int size() {
        return STORAGE.size();
    }
}
