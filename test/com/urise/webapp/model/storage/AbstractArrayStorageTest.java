package com.urise.webapp.model.storage;

import com.urise.webapp.Storage;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Test;

import static com.urise.webapp.model.storage.AbstractArrayStorage.STORAGE_LIMIT;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {
    protected AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() throws Exception{
        try {
            for (int i = 0; i <= STORAGE_LIMIT; i++) {
                storage.save(new Resume(""));
            }
        } catch (Exception e) {
            Assert.fail("Overflow ahead of time");
        }
        storage.save(new Resume(""));
    }
}
