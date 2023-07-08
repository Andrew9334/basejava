package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import java.util.*;

public class MapStorageResume extends AbstractStorage<Resume> {
    final static Map<String, Resume> storage = new HashMap<>();

    @Override
    protected Resume getSearchKey(String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected boolean isExist(Resume searchKey) {
        return Objects.nonNull(searchKey);
    }

    @Override
    protected void doSave(Resume resume, Resume searchKey) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void doUpdate(Resume resume, Resume searchKey) {
        storage.replace(resume.getUuid(), resume);
    }

    @Override
    protected void doDelete(Resume searchKey) {
        Resume resume = searchKey;
        storage.remove(resume.getUuid());
    }

    @Override
    protected Resume doGet(Resume searchKey) {
        Resume resume = searchKey;
        return storage.get(resume.getUuid());
    }

    @Override
    protected List<Resume> doCopyAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public int size() {
        return storage.size();
    }
}
