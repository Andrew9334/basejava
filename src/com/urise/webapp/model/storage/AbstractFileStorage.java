package com.urise.webapp.model.storage;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File> {
    private File directory;

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
        try {
            new FileWriter(directory, false).close();
        } catch (IOException e) {
            e.printStackTrace();
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
            file.createNewFile();
        } catch (IOException e) {
            throw new NotExistStorageException(file.getName());
        }
    }

    @Override
    protected void doDelete(File file) {
        try {
            FileWriter fileWriter = new FileWriter(file, false);
            fileWriter.write("");
            fileWriter.close();
        } catch (IOException e) {
            throw new NotExistStorageException(file.getName());
        }

    }

    @Override
    protected Resume doGet(File file) {
        return null;
    }

    @Override
    protected List<Resume> doCopyAll() {
        List<String> listFile = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(directory))) {
            while (br.ready()) {
                listFile.add(br.readLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Resume> list = new ArrayList<>();
        list.add((Resume) listFile);
        return list;
    }

    protected abstract void doWrite(Resume resume, File file) throws IOException;
}
