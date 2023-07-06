package com.urise.webapp.model.storage;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileStorage extends AbstractStorage<File> {
    private final File directory;

    private final StrategyFiles st;

    protected FileStorage(File directory, StrategyFiles strategyFiles) {
        Objects.requireNonNull(directory, "directory must not be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writeable");
        }
        this.directory = directory;
        this.st = strategyFiles;
    }

    @Override
    public void clear() {
        File[] files = directory.listFiles();
        if (files == null) {
            throw new StorageException("File not found", null, new IOException());
        } else {
            for (File file : files) {
                doDelete(file);
            }
        }
    }

    @Override
    public int size() {
        String[] files = directory.list();
        if (files == null) {
            throw new StorageException("File is not exist", null, new IOException());
        }
        return files.length;
    }

    @Override
    protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    protected void doSave(Resume resume, File file) {
        try {
            file.createNewFile();
            st.doWrite(resume, new BufferedOutputStream(new FileOutputStream(file)));
        } catch (IOException e) {
            throw new StorageException("IO error ", file.getName(), e);
        }
    }

    @Override
    protected void doUpdate(Resume resume, File file) {
        try {
            st.doWrite(resume, new BufferedOutputStream(new FileOutputStream(file)));
        } catch (IOException e) {
            throw new NotExistStorageException(file.getName());
        }
    }

    @Override
    protected void doDelete(File file) {
        if (!file.delete()) {
            throw new StorageException("File is not delete", file.getName(), new IOException());
        }
    }

    @Override
    protected Resume doGet(File file) {
        try {
            return st.doRead(new BufferedInputStream(new FileInputStream(file)));
        } catch (IOException e) {
            throw new NotExistStorageException("File is not exist");
        }
    }

    @Override
    protected List<Resume> doCopyAll() {
        File[] file = directory.listFiles();
        if (file == null) {
            throw new StorageException("File is not exist", null, new IOException());
        }
        List<Resume> list = new ArrayList<>(file.length);

        for (File files : file) {
            list.add(doGet(files));
        }
        return list;
    }
}
