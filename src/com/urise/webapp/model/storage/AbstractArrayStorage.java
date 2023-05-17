package com.urise.webapp.model.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10000;
    protected final Resume[] STORAGE = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void clear() {
        Arrays.fill(STORAGE, 0, size, null);
        size = 0;
    }

    @Override
    protected Object getSearchKey(String uuid) {
        Resume searchKey = new Resume();
        searchKey.getUuid();
        return Arrays.binarySearch(STORAGE, 0, size, searchKey);
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
        searchKey = getSearchKey(resume.getUuid());
    }

    @Override
    protected void doUpdate(Resume resume, Object searchKey) {
        searchKey = getSearchKey(resume.getUuid());
    }

    @Override
    protected void doDelete(Resume resume, Object searchKey) {
        searchKey = getSearchKey(resume.getUuid());
    }

    @Override
    protected Resume doGet(Resume resume, Object searchKey) {
        searchKey = getSearchKey(resume.getUuid());
        return null;
    }

    public Resume[] getAll() {
        return Arrays.copyOf(STORAGE, size);
    }

    public int size() {
        return size;
    }
}
