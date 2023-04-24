package com.urise.webapp.model.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage{

    @Override
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

    @Override
    public Resume get(String uuid) {
        int index = getIndex(uuid);

        if (index == -1) {
            System.out.println("Error: резюме " + uuid + " не существует");
            return null;
        }
        return STORAGE[index];
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);

        if (index == -1) {
            System.out.println("Error: резюме " + uuid + " не существует");
        }

        System.arraycopy(STORAGE, size - 1, STORAGE, index, 1);
        size--;
    }

    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (STORAGE[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}

