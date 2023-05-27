package com.urise.webapp.model.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class ListStorage extends AbstractStorage {
    private static final List<Resume> STORAGE = new ArrayList<>();

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
        return (Resume) STORAGE.get((int) searchKey);
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
