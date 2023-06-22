package com.urise.webapp.model.storage;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File> {
    private final File directory;

    protected AbstractFileStorage(File directory) {
        Objects.requireNonNull(directory, "directory must not be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writeable");
        }
        this.directory = directory;
    }

    @Override
    public void clear() {
        try (RandomAccessFile raf = new RandomAccessFile(directory, "rw")) {
            raf.setLength(0);
        } catch (IOException e) {
            throw new NotExistStorageException("File is not exist");
        }
    }

    @Override
    public int size() {
        return (int) directory.length();
    }

    @Override
    protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected boolean isExist(File file) {
        return directory.exists();
    }

    @Override
    protected void doSave(Resume resume, File file) {
        try {
            file.createNewFile();
            doWrite(resume, file);
        } catch (IOException e) {
            throw new StorageException("IO error ", file.getName(), e);
        }

    }

    @Override
    protected void doUpdate(Resume resume, File file) {
        try {
            doWrite(resume, file);
        } catch (IOException e) {
            throw new NotExistStorageException(file.getName());
        }
    }

    @Override
    protected void doDelete(File file) {
        file.delete();
    }

    @Override
    protected Resume doGet(File file) {
        try {
            return doRead(file);
        } catch (IOException e) {
            throw new NotExistStorageException("File is not exist");
        }
    }

    @Override
    protected List<Resume> doCopyAll() {
        File[] file = directory.listFiles();
        if(file == null) {
            throw new StorageException("File is not exist", null);
        }
        List<Resume> list = new ArrayList<>(file.length);

        for (File files : file) {
            try {
                list.add(doRead(files));
            } catch (IOException e) {
                throw new NotExistStorageException("File is not exist");
            }
        }
        return list;
    }

    protected abstract void doWrite(Resume resume, File file) throws IOException;

    protected abstract Resume doRead(File file) throws IOException;
}
