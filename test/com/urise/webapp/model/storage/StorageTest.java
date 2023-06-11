package com.urise.webapp.model.storage;

import com.urise.webapp.model.Resume;

import java.util.List;

public class StorageTest extends AbstractArrayStorageTest {
    public StorageTest() {
        super(new ArrayStorage());
    }

    @Override
    public void clear() {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return null;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return false;
    }

    @Override
    protected void doSave(Resume resume, Object searchKey) {

    }

    @Override
    protected void doUpdate(Resume resume, Object searchKey) {

    }

    @Override
    protected void doDelete(Object searchKey) {

    }

    @Override
    protected Resume doGet(Object searchKey) {
        return null;
    }

    @Override
    protected List<Resume> doCopyAll() {
        return null;
    }
}