package com.urise.webapp.model;

import java.io.Serializable;
import java.util.*;

/**
 * Initial resume class
 */
public class Resume implements Comparable<Resume>, Serializable {
    private static final long serialVersionUID = 1L;
    // Unique identifier
    private String uuid;
    private String fullName;

    Map<ContactType, String> contactTypeMap = new EnumMap<ContactType, String>(ContactType.class);
    Map<SectionType, AbstractSection> sectionTypeMap = new EnumMap<SectionType, AbstractSection>(SectionType.class);

    public Resume(String uuid, String fullName) {
        Objects.requireNonNull(uuid, "uuid most not be null");
        Objects.requireNonNull(fullName, "fullname most be not null");
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getContact(ContactType type) {
        return contactTypeMap.get(type);
    }

    public AbstractSection getSection(SectionType type) {
        return sectionTypeMap.get(type);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resume resume = (Resume) o;

        return uuid.equals(resume.uuid);
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }

    @Override
    public String toString() {
        return uuid;
    }

    @Override
    public int compareTo(Resume o) {
        return uuid.compareTo(o.uuid);
    }
}
