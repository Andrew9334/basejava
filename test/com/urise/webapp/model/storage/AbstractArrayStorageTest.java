package com.urise.webapp.model.storage;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public abstract class AbstractArrayStorageTest {
    private final Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";

    private static final Resume RESUME_1 = new Resume(UUID_1);
    private static final Resume RESUME_2 = new Resume(UUID_2);
    private static final Resume RESUME_3 = new Resume(UUID_3);
    private static final Resume RESUME_4 = new Resume(UUID_4);

    protected AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
        storage.save(RESUME_4);
    }

    @Test
    public void clear() {
        storage.clear();
        assertSize(0);
        Assert.assertArrayEquals(storage.getAll(), storage.getAll());
    }

    @Test
    public void save() {
        assertSize(4);
        assertGet(RESUME_4);
    }

    @Test
    public void update() {
        storage.update(RESUME_4);
        assertSame(RESUME_4, RESUME_4);
    }

    @Test
    public void get() {
        assertGet(RESUME_1);
    }

    @Test
    public void delete() {
        storage.delete(RESUME_1.getUuid());
        assertSize(3);
        try {
            assertGet(null);
        } catch (Exception e) {
            System.out.println("Resume does not exist");
        }
    }

    @Test
    public void getAll() {
        Resume[] expected = new Resume[4];
        expected[0] = RESUME_1;
        expected[1] = RESUME_2;
        expected[2] = RESUME_3;
        expected[3] = RESUME_4;
        Assert.assertArrayEquals(expected, storage.getAll());
    }

    @Test
    public void size() {
        assertSize(4);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("UUID_NOT_EXIST");
    }

    @Test
    public void saveExist() {
        try {
            storage.save(RESUME_4);
        } catch (Exception e) {
            System.out.println("Resume " + RESUME_4 + " exist.");
        }
    }


    @Test
    public void updateNotExist() {
        try {
            storage.update(RESUME_4);
        } catch (Exception e) {
            System.out.println("Resume " + RESUME_4 + " not exist.");
        }
    }

    @Test
    public void deleteNotExist() {
        try {
            storage.delete(RESUME_2.getUuid());
        } catch (Exception e) {
            System.out.println("Resume " + RESUME_1 + " not exist.");
        }
    }

    @Test
    public void stackOverflow() {
        try {
            for (int i = AbstractArrayStorage.STORAGE_LIMIT; i <= AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume());
            }
        } catch (Exception e) {
            Assert.fail("Storage is overflow");
        }
    }

    private void assertSize(int size) {
        assertEquals(size, storage.size());
    }

    private void assertGet(Resume resume) {
        assertEquals(storage.get(resume.getUuid()), resume);
    }

}