package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.IOException;
import java.util.*;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
    public static final int STORAGE_LIMIT = 10000;
    protected final Resume[] STORAGE = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void clear() {
        Arrays.fill(STORAGE, 0, size, null);
        size = 0;
    }

    @Override
    protected boolean isExist(Integer searchKey) {
        return searchKey >= 0;
    }

    @Override
    protected void doSave(Resume resume, Integer searchKey) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage is overflow", resume.getUuid(), new IOException());
        }
        saveResume(resume, searchKey);
        size++;
    }

    @Override
    protected void doUpdate(Resume resume, Integer searchKey) {
        STORAGE[searchKey] = resume;
    }

    @Override
    protected void doDelete(Integer searchKey) {
        deleteResume(searchKey);
        size--;
    }

    @Override
    protected Resume doGet(Integer searchKey) {
        return STORAGE[searchKey];
    }

    public int size() {
        return size;
    }

    protected abstract void saveResume(Resume resume, int searchKey);

    protected abstract void deleteResume(int searchKey);
}
