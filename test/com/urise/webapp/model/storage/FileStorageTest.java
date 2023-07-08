package com.urise.webapp.model.storage;

import com.urise.webapp.storage.FileStorage;
import com.urise.webapp.storage.strategy.ObjectStreamSerializer;

public class FileStorageTest extends AbstractStorageTest{
    public FileStorageTest() {
        super(new FileStorage(STORAGE_DIR, new ObjectStreamSerializer()));
    }
}
