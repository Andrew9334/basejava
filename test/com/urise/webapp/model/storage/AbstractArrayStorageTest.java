package com.urise.webapp.model.storage;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AbstractArrayStorageTest {
    private final Storage storage;
    private final int STORAGE_LIMIT = 10000;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";

    private static final Resume RESUME_1 = new Resume(UUID_1);
    private static final Resume RESUME_2 = new Resume(UUID_2);
    private static final Resume RESUME_3 = new Resume(UUID_3);

    protected AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void save() throws Exception{
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
        Assert.assertEquals(3,storage.size());
    }

    @Test
    public void update() {
        storage.update(RESUME_1);
        Assert.assertEquals(RESUME_1, RESUME_1);
    }

    @Test
    public void get() {
        storage.get(RESUME_1.getUuid());
        Assert.assertEquals(RESUME_1.getUuid(), RESUME_1.getUuid());
    }

    @Test
    public void delete() {
        storage.delete(RESUME_1.getUuid());
        Assert.assertEquals(2, storage.size());

    }

    @Test
    public void getAll() {
        storage.getAll();
        Assert.assertEquals(RESUME_1.getUuid(), RESUME_1.getUuid());
        Assert.assertEquals(RESUME_2.getUuid(), RESUME_2.getUuid());
        Assert.assertEquals(RESUME_3.getUuid(), RESUME_3.getUuid());
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }

    @Test
    public void stackOverflow() {
        Assert.assertEquals(STORAGE_LIMIT, storage.size());
    }

}