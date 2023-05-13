package com.urise.webapp.model.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class ListStorage extends AbstractStorage {
    private static final List STORAGE = new ArrayList();

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
    public Resume[] getAll() {
        return (Resume[]) STORAGE.toArray(new Resume[]{(Resume) STORAGE});
    }

    public int size() {
        return STORAGE.size();
    }
}
