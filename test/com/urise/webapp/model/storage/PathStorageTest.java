package com.urise.webapp.model.storage;

import com.urise.webapp.storage.strategy.ObjectStreamSerializer;
import com.urise.webapp.storage.PathStorage;

public class PathStorageTest extends AbstractStorageTest {
    public PathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new ObjectStreamSerializer()));
    }
}
