package com.urise.webapp.model.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public final void save(Resume resume) {
        Object searchKey = getNotExistSearchKey(resume.getUuid());
        doSave(resume, (Integer) searchKey);
    }

    public final Resume get(String uuid) {
        Object searchKey = getExistSearchKey(uuid);
        return doGet((Integer) searchKey);
    }

    public final void delete(String uuid) {
        Object searchKey = getExistSearchKey(uuid);
        doDelete((Integer) searchKey);
    }

    public final void update(Resume resume) {
        Object searchKey = getExistSearchKey(resume.getUuid());
        doUpdate(resume, (Integer) searchKey);
    }

    protected final Integer getExistSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isExist((Integer) searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return (Integer) searchKey;
    }

    protected final Integer getNotExistSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (isExist((Integer) searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return (Integer) searchKey;
    }

    protected abstract Object getSearchKey(String uuid);

    protected abstract boolean isExist(Object searchKey);

    protected abstract void doSave(Resume resume, Object searchKey);

    protected abstract void doUpdate(Resume resume, Object searchKey);

    protected abstract void doDelete(Object searchKey);

    protected abstract Resume doGet(Object searchKey);
}
