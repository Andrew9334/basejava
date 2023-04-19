package com.urise.webapp.model.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    protected static final int STORAGE_LIMIT = 10000;
    Resume[] storage = new Resume[STORAGE_LIMIT];
    int size;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        int index = getIndex(r.getUuid());

        if (size >= STORAGE_LIMIT) {
            System.out.println("Error: переполнение списка");
        } else if (index != -1) {
            System.out.println("Error: резюме с uuid " + r.getUuid() + " уже существует");
        } else {
            storage[size++] = r;
        }
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());

        if (index == -1) {
            System.out.println("Error: резюме " + resume.getUuid() + " не существует");
        }
    }


    public Resume get(String uuid) {
        int index = getIndex(uuid);

        if (index == -1) {
            System.out.println("Error: резюме " + uuid + " не существует");
        }
        return null;
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);

        System.arraycopy(storage, index + 1, storage, index, size - 1);
        size--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}

