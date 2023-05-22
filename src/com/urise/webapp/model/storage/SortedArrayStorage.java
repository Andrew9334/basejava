package com.urise.webapp.model.storage;

import com.urise.webapp.model.Resume;
import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void saveResume(Resume resume, Integer searchKey) {
        int indexSave = -searchKey - 1;
        System.arraycopy(STORAGE, indexSave, STORAGE, indexSave + 1, size - indexSave  );
        STORAGE[indexSave] = resume;
    }

    @Override
    protected void deleteResume(Integer searchKey) {
        System.arraycopy(STORAGE, searchKey + 1, STORAGE, searchKey, size - searchKey - 1);
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        Resume searchKey = new Resume();
        searchKey.getUuid();
        return Arrays.binarySearch(STORAGE, 0, size, searchKey);
    }
}
