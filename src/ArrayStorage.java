import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size = 0;
    Resume[] resumes = new Resume[size];

    void clear() {
        for (int i = 0; i < size; i++) {
            if (size == 0) {
                return;
            } else {
                storage[i] = null;
                resumes = Arrays.copyOf(storage, size);
            }
        }
    }

    void save(Resume r) {
        storage[size++] = r;
    }

    Resume get(String uuid) {
        if (size == 0) {
            return null;
        }
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        Resume[] newStorage = new Resume[size];
        boolean check = false;

        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                check = true;
            } else {
                if (!check) {
                    newStorage[i] = storage[i];
                } else {
                    newStorage[i - 1] = storage[i];
                }
            }
        }
        storage = newStorage;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume resume = new Resume();

        for (int i = 0; i < size; i++) {
            if (storage[i] != null) {
                System.out.print(storage[i] + " ");
            } else if (resume.uuid == null) {
                return resumes;
            }
        }
        return new Resume[0];
    }

    int size() {
        if (size == 0) {
            return 0;
        } else {
            return size;
        }
    }
}
