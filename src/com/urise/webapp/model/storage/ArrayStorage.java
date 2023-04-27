package com.urise.webapp.model.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected void saveResume(Resume r) {
        STORAGE[size++] = r;
    }

    @Override
    protected void updateResume(Resume resume) {
        int index = getIndex(resume.getUuid());
        STORAGE[index] = resume;
    }

    @Override
    protected void deleteResume(String uuid) {
        int index = getIndex(uuid);
        System.arraycopy(STORAGE, size - 1, STORAGE, index, 1);
    }

    @Override
    protected Resume getResume(String uuid) {
        int index = getIndex(uuid);
        return STORAGE[index];
    }

    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (STORAGE[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}


