package com.urise.webapp.model.storage;

import com.urise.webapp.storage.PathStorage;
import com.urise.webapp.storage.strategy.DataStreamSerializer;

public class DataPathStorageTest extends AbstractStorageTest {
    public DataPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new DataStreamSerializer()));
    }
}
