package com.urise.webapp.model.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;
    protected final Resume[] STORAGE = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void clear() {
        Arrays.fill(STORAGE, 0, size, null);
        size = 0;
    }

    public final void save(Resume r) {
        int index = getIndex(r.getUuid());

        if (size >= STORAGE_LIMIT) {
            System.out.println("Error: переполнение списка");
        } else if (index != -1) {
            System.out.println("Error: резюме с uuid " + r.getUuid() + " уже существует");
        } else {
            saveResume(r);
        }
    }

    public final void update(Resume resume) {
        int index = getIndex(resume.getUuid());

        if (index == -1) {
            System.out.println("Error: резюме " + resume.getUuid() + " не существует");
        } else {
            updateResume(resume);
        }
    }

    public final Resume get(String uuid) {
        int index = getIndex(uuid);

        if (index == -1) {
            System.out.println("Error: резюме " + uuid + " не существует");
            return null;
        }
        return STORAGE[index];
    }

    public final void delete(String uuid) {
        int index = getIndex(uuid);

        if (index == -1) {
            System.out.println("Error: резюме " + uuid + " не существует");
        } else {
            deleteResume(uuid);
            size--;
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOf(STORAGE, size);
    }

    public int size() {
        return size;
    }

    protected abstract void saveResume(Resume r);

    protected abstract void updateResume(Resume resume);

    protected abstract void deleteResume(String uuid);

    protected abstract Resume getResume(String uuid);

    protected abstract int getIndex(String uuid);
}
