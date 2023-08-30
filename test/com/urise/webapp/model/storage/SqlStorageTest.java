package com.urise.webapp.model.storage;

import com.urise.webapp.Config;
import com.urise.webapp.Storage;

public class SqlStorageTest extends AbstractStorageTest{
    public SqlStorageTest() {
        super(Config.getInstance().getStorage());
    }
}
