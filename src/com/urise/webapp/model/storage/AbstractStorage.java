package com.urise.webapp.model.storage;

import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {
    protected abstract Object getSearchKey(String uuid);

    protected abstract boolean isExist(Object searchKey);

    protected abstract Object getExistSearchKey(String uuid);

    protected abstract Object getNotExistSearchKey(String uuid);

    protected abstract void doSave(Resume resume, Object searchKey);

    protected abstract Resume doGet(Resume resume, Object searchKey);

    protected abstract void doDelete(Resume resume, Object searchKey);

    protected abstract void doUpdate(Resume resume, Object searchKey);


    public final void save(Resume resume) {
        Object searchKey = getExistSearchKey(resume.getUuid());
        doSave(resume, searchKey);
    }

    public final void get(Resume resume) {
        Object searchKey = getExistSearchKey(resume.getUuid());
        doGet(resume, searchKey);
    }

    public final void delete(Resume resume) {
        Object searchKey = getExistSearchKey(resume.getUuid());
        doDelete(resume, searchKey);
    }

    public final void update(Resume resume) {
        Object searchKey = getExistSearchKey(resume.getUuid());
        doUpdate(resume, searchKey);
    }
}
