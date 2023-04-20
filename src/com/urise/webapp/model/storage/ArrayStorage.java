package com.urise.webapp.model.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    protected static final int STORAGE_LIMIT = 10000;
    private final Resume[] STORAGE = new Resume[STORAGE_LIMIT];
    int size;

    public void clear() {
        Arrays.fill(STORAGE, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        int index = getIndex(r.getUuid());

        if (size >= STORAGE_LIMIT) {
            System.out.println("Error: переполнение списка");
        } else if (index != -1) {
            System.out.println("Error: резюме с uuid " + r.getUuid() + " уже существует");
        } else {
            STORAGE[size++] = r;
        }
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());

        if (index == -1) {
            System.out.println("Error: резюме " + resume.getUuid() + " не существует");
        } else {
            STORAGE[index] = resume;
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);

        if (index == -1) {
            System.out.println("Error: резюме " + uuid + " не существует");
            return null;
        }
        return STORAGE[index];
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);

        if (index == -1) {
            System.out.println("Error: резюме " + uuid + " не существует");
        }

        System.arraycopy(STORAGE, size - 1, STORAGE, index, 1);
        size--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(STORAGE, size);
    }

    public int size() {
        return size;
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (STORAGE[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}

