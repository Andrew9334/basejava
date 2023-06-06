package com.urise.webapp.model.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void saveResume(Resume resume, Object searchKey) {
        int indexSave = - (int) searchKey - 1;
        System.arraycopy(STORAGE, indexSave, STORAGE, indexSave + 1, size - indexSave  );
        STORAGE[indexSave] = resume;
    }

    @Override
    protected void deleteResume(Object searchKey) {
        System.arraycopy(STORAGE, (int) searchKey + 1, STORAGE, (int) searchKey, size - (int) searchKey - 1);
    }

    @Override
    protected List<Resume> doCopyAll() {
        List<Resume> list = new ArrayList<>();
        Collections.addAll(list, STORAGE);
        return list;
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid, "nothing");
        return Arrays.binarySearch(STORAGE, 0, size, searchKey);
    }
}
