package com.urise.webapp.model.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected void saveResume(Resume resume, Object searchKey) {
        STORAGE[(int) searchKey] = resume;
    }

    @Override
    protected void deleteResume(Object searchKey) {
        System.arraycopy(STORAGE, size - 1, STORAGE, (int) searchKey, size - (int) searchKey - 1);
    }


    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < size; i++) {
            if (STORAGE[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}


