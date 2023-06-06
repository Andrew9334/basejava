package com.urise.webapp.model.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class ListStorage extends AbstractStorage {
    private static final List<Resume> STORAGE = new ArrayList<>();

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < STORAGE.size(); i++) {
            if (STORAGE.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return Objects.nonNull(searchKey);
    }

    @Override
    protected void doSave(Resume resume, Object searchKey) {
        STORAGE.add(resume);
    }

    @Override
    protected void doUpdate(Resume resume, Object searchKey) {
        STORAGE.set((int) searchKey, resume);
    }

    @Override
    protected void doDelete(Object searchKey) {
        STORAGE.remove(((int) searchKey));
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return STORAGE.get((int) searchKey);
    }

    @Override
    protected List<Resume> doCopyAll() {
        return new ArrayList<>(STORAGE);
    }

    @Override
    public void clear() {
        STORAGE.clear();
    }

    public int size() {
        return STORAGE.size();
    }
}
