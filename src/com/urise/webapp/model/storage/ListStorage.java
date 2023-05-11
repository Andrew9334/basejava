package com.urise.webapp.model.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public abstract class ListStorage extends AbstractStorage {
    private static final List STORAGE = new ArrayList();

    @Override
    protected void saveResume(Resume r, int index) {
        STORAGE.add(r);
    }

    @Override
    protected void updateResume(Resume resume) {
        int index = getIndex(resume.getUuid());
        STORAGE.set(index, resume);
    }

    @Override
    protected void deleteResume(String uuid, int index) {
        index = getIndex(uuid);
        STORAGE.remove(index);
    }

    @Override
    protected Resume getResume(String uuid) {
        int index = getIndex(uuid);
        return (Resume) STORAGE.get(index);
    }

    @Override
    public Resume[] getAll() {
        return (Resume[]) STORAGE.toArray(new Resume[]{(Resume) STORAGE});
    }

    public int size() {
        return STORAGE.size();
    }

    @Override
    protected int getIndex(String uuid) {
        if (STORAGE.indexOf(uuid) > 0) {
            return STORAGE.indexOf(uuid);
        }
        return -1;
    }
}
