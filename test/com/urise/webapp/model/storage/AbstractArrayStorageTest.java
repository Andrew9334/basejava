package com.urise.webapp.model.storage;

import com.urise.webapp.Storage;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.AbstractStorage;
import org.junit.Assert;
import org.junit.Test;

import static com.urise.webapp.storage.AbstractArrayStorage.STORAGE_LIMIT;


public abstract class AbstractArrayStorageTest extends AbstractStorage {
    protected final Storage storage;

    protected AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() throws Exception {
        try {
            for (int i = 0; i < STORAGE_LIMIT; i++) {
                storage.save(new Resume(""));
            }
        } catch (Exception e) {
            Assert.fail("Overflow ahead of time");
        }
        storage.save(new Resume(""));
    }
}
