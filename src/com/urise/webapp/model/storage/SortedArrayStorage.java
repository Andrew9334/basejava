package com.urise.webapp.model.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    protected void saveResume(Resume r) {
        int indexSave = -getIndex(r.getUuid()) - 1;
        System.arraycopy(STORAGE, indexSave, STORAGE, indexSave + 1, size - indexSave  );
        STORAGE[indexSave] = r;
    }

    @Override
    protected void updateResume(Resume resume) {
        int indexSort = getIndex(resume.getUuid());
        STORAGE[indexSort] = resume;
    }

    @Override
    protected void deleteResume(String uuid) {
        int indexSort = getIndex(uuid);
        System.arraycopy(STORAGE, indexSort + 1, STORAGE, indexSort, size - indexSort - 1);
    }

    @Override
    protected Resume getResume(String uuid) {
        int indexSort = getIndex(uuid);
        return STORAGE[indexSort];
    }

    @Override
    public int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(STORAGE, 0, size, searchKey);
    }
}
