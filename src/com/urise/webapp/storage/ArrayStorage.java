package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected void saveResume(Resume resume, int searchKey) {
        STORAGE[size] = resume;
    }

    @Override
    protected void deleteResume(int searchKey) {
        System.arraycopy(STORAGE, size - 1, STORAGE, (int) searchKey, 1);
    }

    @Override
    protected List<Resume> doCopyAll() {
        return new ArrayList<>(Arrays.asList(Arrays.copyOfRange(STORAGE, 0, size)));
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


