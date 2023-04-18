package com.urise.webapp.model.storage;

import com.urise.webapp.model.Resume;
import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    int resumesCount = 10000;
    Resume[] storage = new Resume[resumesCount];
    int size;

    public void clear() {
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (storage[i] != null) {
                count++;
            }
        }

        Arrays.fill(storage, 0, count, null);
        size = 0;
    }

    public void save(Resume r) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(r.getUuid())) {
                System.out.println("Error: резюме с uuid " + r.getUuid() + " уже существует");
                return;
            }
        }

        if (size == resumesCount) {
            System.out.println("Error: переполнение списка");
        }

        storage[size++] = r;
    }

    public void update(Resume resume) {
        if (checkResumes(resume.getUuid())) {
            for (int i = 0; i < size; i++) {
                if (storage[i].getUuid().equals(resume.getUuid())) {
                    storage[i] = resume;
                } else {
                    return;
                }
            }
        }
    }

    public Resume get(String uuid) {
        if (checkResumes(uuid)) {
            for (int i = 0; i < size; i++) {
                if (storage[i].getUuid().equals(uuid)) {
                    return storage[i];
                }
            }
        }
        return null;
    }

    public void delete(String uuid) {
        if (checkResumes(uuid)) {
            int index = -1;

            for (int i = 0; i < size; i++) {
                if (storage[i].getUuid().equals(uuid)) {
                    index = i;
                }
            }

            if (index == -1) {
                return;
            }

            while (index < size - 1) {
                storage[index] = storage[index + 1];
                index++;
            }
            storage[index] = null;
            size--;
        }
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

    boolean checkResumes(String uuid) {
        boolean checkResume = false;
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                checkResume = true;
                break;
            }
        }
        if (!checkResume) {
            System.out.println("Error: резюме c uuid " + uuid + " не существует");
        }
        return checkResume;
    }
}

