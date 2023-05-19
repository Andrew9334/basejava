package com.urise.webapp.model.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

public class MapStorage extends AbstractStorage {
    final static HashMap <UUID, Resume> STORAGE = new HashMap<>();


    @Override
    protected Object getSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        return searchKey;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        Resume resume = new Resume();
        if (searchKey == getSearchKey(resume.getUuid())) {
            return true;
        }
        return false;
    }

    @Override
    protected void doSave(Resume resume, Object searchKey) {
        searchKey = getSearchKey(resume.getUuid());
        STORAGE.put((UUID) searchKey, resume);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        Resume resume = new Resume();
        searchKey = getSearchKey(resume.getUuid());
        return STORAGE.get(searchKey);
    }

    @Override
    protected void doDelete(Resume resume, Object searchKey) {
        searchKey = getSearchKey(resume.getUuid());
        STORAGE.remove(searchKey, resume);
    }

    @Override
    protected void doUpdate(Resume resume, Object searchKey) {
        searchKey = getSearchKey(resume.getUuid());
        STORAGE.replace((UUID) searchKey, resume);
    }

    @Override
    public void clear() {
        STORAGE.clear();
    }

    @Override
    public Resume[] getAll() {
        return Arrays.asList(new HashMap[]{STORAGE});
    }

    @Override
    public int size() {
        return STORAGE.size();
    }
}
