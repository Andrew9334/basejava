package com.urise.webapp.model.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class ListStorage extends AbstractStorage {
    private static final List STORAGE = new ArrayList();

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < STORAGE.size(); i++) {
            if (uuid.equals(STORAGE.get(i))) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected boolean isExist(Integer searchKey) {
        return Objects.isNull(searchKey);
    }

    @Override
    protected void doSave(Resume resume, Integer searchKey) {
        STORAGE.add(resume);
    }

    @Override
    protected void doUpdate(Resume resume, Integer searchKey) {
        STORAGE.set(searchKey, resume);
    }

    @Override
    protected void doDelete(Integer searchKey) {
        STORAGE.remove(searchKey);
    }

    @Override
    protected Resume doGet(Integer searchKey) {
        return (Resume) STORAGE.get(searchKey);
    }

    @Override
    public void clear() {
        STORAGE.clear();
    }

    @Override
    public Resume[] getAll() {
        return (Resume[]) STORAGE.toArray(new Resume[0]);
    }

    public int size() {
        return STORAGE.size();
    }
}
