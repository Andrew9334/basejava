package com.urise.webapp.model.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage{
    protected static final int STORAGE_LIMIT = 10000;
    protected final Resume[] STORAGE = new Resume[STORAGE_LIMIT];
    protected int size = 0;


    public final void actionOnResume(Resume... resume) {
        size();
        clear();
    }

    public void clear() {
        Arrays.fill(STORAGE, 0, size, null);
        size = 0;
    }

    public abstract void save(Resume r);

    public abstract void update(Resume resume);

    public abstract Resume get(String uuid);

    public abstract void delete(String uuid);

    public Resume[] getAll() {
        return Arrays.copyOf(STORAGE, size);
    }

    public int size() {
        return size;
    }

    protected abstract int getIndex(String uuid);
}
