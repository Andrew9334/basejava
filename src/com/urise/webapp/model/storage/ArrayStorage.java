package com.urise.webapp.model.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {
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

    @Override
    public Resume get(String uuid) {
        return new Resume(uuid);
    }

    @Override
    public void delete(String uuid) {
        Resume resume = new Resume(uuid);
        Object searchKey = getSearchKey(uuid);
        doDelete(resume, STORAGE[(int) searchKey]);
    }
}


