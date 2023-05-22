package com.urise.webapp.model.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public final void save(Resume resume) {
        Integer searchKey = getNotExistSearchKey(resume.getUuid());
        doSave(resume, searchKey);
    }

    public final Resume get(String uuid) {
        Integer searchKey = getExistSearchKey(uuid);
        return doGet(searchKey);
    }

    public final void delete(String uuid) {
        Integer searchKey = getExistSearchKey(uuid);
        doDelete(searchKey);
    }

    public final void update(Resume resume) {
        Integer searchKey = getExistSearchKey(resume.getUuid());
        doUpdate(resume, searchKey);
    }

    protected final Integer getExistSearchKey(String uuid) {
        Integer searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    protected final Integer getNotExistSearchKey(String uuid) {
        Integer searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    protected abstract Integer getSearchKey(String uuid);

    protected abstract boolean isExist(Integer searchKey);

    protected abstract void doSave(Resume resume, Integer searchKey);

    protected abstract void doUpdate(Resume resume, Integer searchKey);

    protected abstract void doDelete(Integer searchKey);

    protected abstract Resume doGet(Integer searchKey);
}
