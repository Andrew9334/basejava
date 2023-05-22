package com.urise.webapp.model.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected void saveResume(Resume resume, Integer searchKey) {
        STORAGE[searchKey] = resume;
    }

    @Override
    protected void deleteResume(Integer searchKey) {
        System.arraycopy(STORAGE, size - 1, STORAGE, searchKey, searchKey + 1);
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


