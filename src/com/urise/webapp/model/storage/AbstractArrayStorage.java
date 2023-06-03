package com.urise.webapp.model.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.*;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10000;
    protected final Resume[] STORAGE = new Resume[STORAGE_LIMIT];
    protected int size = 0;
    private static final Comparator<Resume> RESUME_COMPARATOR = ((o1, o2) -> o1.getUuid().compareTo(o2.getUuid()));

    public void clear() {
        Arrays.fill(STORAGE, 0, size, null);
        size = 0;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return (int) searchKey >= 0;
    }

    @Override
    protected void doSave(Resume resume, Object searchKey) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage is overflow", resume.getUuid());
        }
        saveResume(resume, searchKey);
        size++;
    }

    @Override
    protected void doUpdate(Resume resume, Object searchKey) {
        STORAGE[(int) searchKey] = resume;
    }

    @Override
    protected void doDelete(Object searchKey) {
        deleteResume(searchKey);
        size--;
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return STORAGE[(int) searchKey];
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> list = new ArrayList<>();
        Collections.sort(list);
        return list;
    }

    public int size() {
        return size;
    }

    protected abstract void saveResume(Resume resume, Object searchKey);

    protected abstract void deleteResume(Object searchKey);
}
