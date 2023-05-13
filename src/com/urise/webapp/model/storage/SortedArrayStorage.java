package com.urise.webapp.model.storage;

import com.urise.webapp.model.Resume;

public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    protected void doSave(Resume resume, Object searchKey) {
        STORAGE[(int) searchKey] = resume;
    }

    @Override
    protected void doUpdate(Resume resume, Object searchKey) {
        STORAGE[(int) searchKey] = resume;
    }

    @Override
    protected void doDelete(Resume resume, Object searchKey) {
        System.arraycopy(STORAGE, size - 1, STORAGE, (int) searchKey, ((int) searchKey + 1));
    }

    @Override
    protected Resume doGet(Resume resume, Object searchKey) {
        return STORAGE[(int) searchKey] = resume;
    }
}
