package com.urise.webapp.model.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage{
    Resume[] STORAGE = new Resume[STORAGE_LIMIT];

    @Override
    public void clear() {

    }

    @Override
    public void update(Resume r) {

    }

    @Override
    public void save(Resume r) {

    }

    @Override
    public void delete(String uuid) {

    }

    @Override
    public Resume get(String uuid) {
        return null;
    }

    @Override
    public Resume[] getAll() {
        return new Resume[0];
    }

    public void sort (Resume resume, int index) {
        int indexSort = -index - 1;
        System.arraycopy(STORAGE, indexSort, STORAGE, indexSort + 1, size - indexSort);
        STORAGE[indexSort] = resume;
    }

    @Override
    public int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(STORAGE, 0, size, searchKey);
    }
}
