import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    int resumesCount = 10000;
    Resume[] storage = new Resume[resumesCount];
    int size;

    void clear() {
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (storage[i] != null) {
                count++;
            }
        }

        Arrays.fill(storage, 0, count, null);
        size = 0;
    }

    void save(Resume r) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(r.uuid)) {
                System.out.println("Error: резюме с uuid " + r.uuid + " уже существует");
                return;
            }
        }

        if (size == resumesCount) {
            System.out.println("Error: переполнение списка");
        }

        storage[size++] = r;
    }

    void update(Resume resume) {
        if (checkResumes(resume.uuid)) {
            for (int i = 0; i < size; i++) {
                if (storage[i].uuid.equals(resume.uuid)) {
                    storage[i] = resume;
                } else {
                    return;
                }
            }
        }
    }

    Resume get(String uuid) {
        if (checkResumes(uuid)) {
            for (int i = 0; i < size; i++) {
                if (storage[i].uuid.equals(uuid)) {
                    return storage[i];
                }
            }
        }
        return null;
    }

    void delete(String uuid) {
        if (checkResumes(uuid)) {
            int index = -1;

            for (int i = 0; i < size; i++) {
                if (storage[i].uuid.equals(uuid)) {
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
    Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    int size() {
        return size;
    }

    boolean checkResumes(String uuid) {
        boolean checkResume = false;
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                checkResume = true;
                break;
            }
        }
        if (checkResume == false) {
            System.out.println("Error: резюме c uuid " + uuid + " не существует");
        }
        return checkResume;
    }
}

