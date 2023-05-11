package com.urise.webapp.model.storage;

import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;
    protected final Resume STORAGE = new Resume();
    protected int size = 0;

    protected abstract void saveResume(Resume r, int index);

    protected abstract void updateResume(Resume resume);

    protected abstract void deleteResume(String uuid, int index);

    protected abstract Resume getResume(String uuid);

    public abstract Resume[] getAll();

    protected abstract int getIndex(String uuid);

    public abstract int size();
}
