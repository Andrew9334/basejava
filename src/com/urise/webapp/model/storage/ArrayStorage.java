package com.urise.webapp.model.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected void saveResume(Resume resume, Object searchKey) {
        STORAGE[size] = resume;
    }

    @Override
    protected void deleteResume(Object searchKey) {
        System.arraycopy(STORAGE, size - 1, STORAGE, (int) searchKey, 1);
    }

    @Override
    protected List<Resume> doCopyAll() {
        List<Resume> list = new ArrayList<>();
        Collections.addAll(list, STORAGE);
        return list;
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


