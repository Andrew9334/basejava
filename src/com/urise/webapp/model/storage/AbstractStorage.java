package com.urise.webapp.model.storage;

import com.urise.webapp.Storage;
import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements Storage {

    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    Comparator<Resume> comparatorResume =
            Comparator.comparing(Resume::getFullName).
                    thenComparing(Resume::getUuid);

    public final void save(Resume resume) {
        LOG.info("Save " + resume);
        SK searchKey = getNotExistSearchKey(resume.getUuid());
        doSave(resume, searchKey);
    }

    public final Resume get(String uuid) {
        LOG.info("Get + " + uuid);
        SK searchKey = getExistSearchKey(uuid);
        return doGet(searchKey);
    }

    public final void delete(String uuid) {
        LOG.info("Delete " + uuid);
        SK searchKey = getExistSearchKey(uuid);
        doDelete(searchKey);
    }

    public final void update(Resume resume) {
        LOG.info("Update " + resume);
        SK searchKey = getExistSearchKey(resume.getUuid());
        doUpdate(resume, searchKey);
    }

    protected final SK getExistSearchKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    protected final SK getNotExistSearchKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    @Override
    public List<Resume> getAllSorted() {
        LOG.info("getAllSorted");
        List<Resume> list  = doCopyAll();
        list.sort(comparatorResume);
        return list;
    }

    protected abstract SK getSearchKey(String uuid);

    protected abstract boolean isExist(SK searchKey);

    protected abstract void doSave(Resume resume, SK searchKey);

    protected abstract void doUpdate(Resume resume, SK searchKey);

    protected abstract void doDelete(SK searchKey);

    protected abstract Resume doGet(SK searchKey);

    protected abstract List<Resume> doCopyAll();
}
