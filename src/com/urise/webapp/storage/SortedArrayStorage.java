package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void saveResume(Resume resume, int searchKey) {
        int indexSave = - searchKey - 1;
        System.arraycopy(STORAGE, indexSave, STORAGE, indexSave + 1, size - indexSave  );
        STORAGE[indexSave] = resume;
    }

    @Override
    protected void deleteResume(int searchKey) {
        System.arraycopy(STORAGE, searchKey + 1, STORAGE, searchKey, size - searchKey - 1);
    }

    @Override
    protected List<Resume> doCopyAll() {
        return new ArrayList<>(Arrays.asList(Arrays.copyOfRange(STORAGE, 0, size)));
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid, "nothing");
        return Arrays.binarySearch(STORAGE, 0, size, searchKey);
    }
}
