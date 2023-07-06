package com.urise.webapp.model.storage;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

public class ObjectStreamPathStorage extends AbstractStorage<Path> {
    private final Path path;
    private final StrategyFiles strategyFiles;

    public ObjectStreamPathStorage(String directory, StrategyFiles st) {
        Objects.requireNonNull(directory, "Directory must be not null");
        if (!Files.isDirectory(Path.of(directory))) {
            throw new IllegalArgumentException();
        }
        this.strategyFiles = st;
        path = Paths.get(directory);
    }

    @Override
    public void clear() {
        try {
            List<String> list = Files.readAllLines(path);
            for (String file : list) {
                doDelete(Path.of(file));
            }
        } catch (IOException e) {
            e.printStackTrace();
//            throw new StorageException("File is not exist", null);
        }
    }

    @Override
    public int size() {
        try {
            return (int) Files.size(path);
        } catch (IOException e) {
            throw new StorageException("File is not exist", null);
        }
    }

    @Override
    protected Path getSearchKey(String uuid) {
        return path.resolve(uuid);
    }

    @Override
    protected boolean isExist(Path path) {
        return Files.isRegularFile(this.path);
    }

    @Override
    protected void doSave(Resume resume, Path path) {
        try {
            Files.createFile(path);
            strategyFiles.doWrite(resume, new BufferedOutputStream(Files.newOutputStream(path)));
        } catch (IOException e) {
            throw new StorageException("File is not exist", null);
        }
    }

    @Override
    protected void doUpdate(Resume resume, Path searchKey) {
        try {
            strategyFiles.doWrite(resume, new BufferedOutputStream(Files.newOutputStream(path)));
        } catch (IOException e) {
            throw new NotExistStorageException("File is not exist");
        }
    }

    @Override
    protected void doDelete(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new StorageException("File is not exist", null);
        }
    }

    @Override
    protected Resume doGet(Path path) {
        try {
            return strategyFiles.doRead(new BufferedInputStream(Files.newInputStream(path)));
        } catch (IOException e) {
            throw new NotExistStorageException("File is not exist");
        }
    }

    @Override
    protected List<Resume> doCopyAll() {
        return null;
    }
}
