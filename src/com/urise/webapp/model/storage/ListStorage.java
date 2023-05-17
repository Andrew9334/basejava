package com.urise.webapp.model.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class ListStorage extends AbstractStorage {
    private static final List STORAGE = new ArrayList();

    @Override
    protected Object getSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        return searchKey;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        Resume resume = new Resume();
        if (searchKey == getSearchKey(resume.getUuid())) {
            return true;
        }
        return false;
    }

    @Override
    protected void doSave(Resume resume, Object searchKey) {
        STORAGE.add(resume);
    }

    @Override
    protected void doUpdate(Resume resume, Object searchKey) {
        searchKey = getSearchKey(resume.getUuid());
        STORAGE.set((int) searchKey, resume);
    }

    @Override
    protected void doDelete(Resume resume, Object searchKey) {
        searchKey = getSearchKey(resume.getUuid());
        STORAGE.remove(searchKey);
    }

    @Override
    protected Resume doGet(Resume resume, Object searchKey) {
        searchKey = getSearchKey(resume.getUuid());
        return (Resume) STORAGE.get((int) searchKey);
    }

    @Override
    public void clear() {
        STORAGE.clear();
    }

    @Override
    public Resume get(String uuid) {
        return new Resume(uuid);
    }

    @Override
    public void delete(String uuid) {
        Resume resume = new Resume(uuid);
        Object searchKey = getSearchKey(uuid);
        doDelete(resume, STORAGE.get((int) searchKey));
    }

    @Override
    public Resume[] getAll() {
        return (Resume[]) STORAGE.toArray(new Resume[]{(Resume) STORAGE});
    }

    public int size() {
        return STORAGE.size();
    }
}
