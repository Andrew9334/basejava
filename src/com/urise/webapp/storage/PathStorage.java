package com.urise.webapp.storage;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.strategy.StreamSerializer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PathStorage extends AbstractStorage<Path> {
    private final Path path;
    private final StreamSerializer streamSerializer;

    public PathStorage(String directory, StreamSerializer st) {
        Objects.requireNonNull(directory, "Directory must be not null");
        this.path = Paths.get(directory);
        this.streamSerializer = st;

        if (!Files.isDirectory(path) || !Files.isWritable(path)) {
            throw new IllegalArgumentException(directory + "is not a directory");
        }
    }

    @Override
    public void clear() {
        try {
            Files.list(path).forEach(this::doDelete);
        } catch (IOException e) {
            throw new StorageException("IO Error", null);
        }
    }

    @Override
    public int size() {
        try {
            return Files.list(path).toList().size();
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
        return Files.isRegularFile(path);
    }

    @Override
    protected void doSave(Resume resume, Path path) {
        try {
            Files.createFile(path);
            streamSerializer.doWrite(resume, new BufferedOutputStream(Files.newOutputStream(path)));
        } catch (IOException e) {
            throw new StorageException("File is not exist", resume.getUuid(), e);
        }
    }

    @Override
    protected void doUpdate(Resume resume, Path path) {
        try {
            streamSerializer.doWrite(resume, new BufferedOutputStream(Files.newOutputStream(path)));
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
            return streamSerializer.doRead(new BufferedInputStream(Files.newInputStream(path)));
        } catch (IOException e) {
            throw new NotExistStorageException("File cannot read");
        }
    }

    @Override
    protected List<Resume> doCopyAll() {
        try {
            return Files.list(path).map(this::doGet).collect(Collectors.toList());
        } catch (IOException e) {
            throw new StorageException("File cannot read", null, e);
        }
    }
}
