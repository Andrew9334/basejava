package com.urise.webapp.storage.strategy;

import com.urise.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;

public class DataStreamSerializer implements StreamSerializer {
    @Override
    public void doWrite(Resume resume, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());
            Map<ContactType, String> contacts = resume.getContacts();
            writeWithException(contacts.entrySet(), dos, entry -> {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            });
            Map<SectionType, Section> sections = resume.getSections();
            writeWithException(sections.entrySet(), dos, entry -> {
                dos.writeUTF(entry.getKey().name());
                switch (entry.getKey()) {
                    case PERSONAL, OBJECTIVE -> dos.writeUTF(((TextSection) entry.getValue()).getContent());
                    case ACHIEVEMENTS, QUALIFICATIONS -> {
                        List<String> list = (((ListSection) entry.getValue()).getItems());
                        writeWithException(list, dos, dos::writeUTF);
                    }
                    case EXPERIENCE, EDUCATION -> {
                        List<Organization> organizations = (((OrganizationSection) entry.getValue()).getOrganizations());
                        dos.writeInt(organizations.size());
                        writeOrganization(dos, organizations);
                    }
                    default -> {
                    }
                }
            });
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            readElement(dis, () -> resume.setContact(ContactType.valueOf(dis.readUTF()), dis.readUTF()));
            readElement(dis, () -> {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                switch (sectionType) {
                    case PERSONAL, OBJECTIVE -> resume.setSection(sectionType, new TextSection(dis.readUTF()));
                    case ACHIEVEMENTS, QUALIFICATIONS -> {
                        List<String> list = new ArrayList<>();
                        readWithException(dis, () -> {
                            list.add(dis.readUTF());
                            return list;
                        });
                        resume.setSection(sectionType, new ListSection(list));
                    }
                    case EXPERIENCE, EDUCATION -> readOrganizations(dis, sectionType, resume);
                    default -> {
                    }
                }
            });
            return resume;
        }
    }

    private void writeOrganization(DataOutputStream dos, List<Organization> organizations) throws IOException {
        for (Organization org : organizations) {
            dos.writeUTF(org.getHomePage().getName());
            String checkUrl = org.getHomePage().getUrl() != null ? org.getHomePage().getUrl() : "";
            dos.writeUTF(checkUrl);
            List<Organization.Position> positions = org.getPositions();
            dos.writeInt(positions.size());
            for (Organization.Position pos : positions) {
                writeLocalDate(dos, pos.getStartDate());
                writeLocalDate(dos, pos.getEndDate());
                dos.writeUTF(pos.getTitle());
                String checkDescription = pos.getDescription() != null ? pos.getDescription() : "";
                dos.writeUTF(checkDescription);
            }
        }
    }

    private void readOrganizations(DataInputStream dis, SectionType sectionType, Resume resume) throws IOException {
        List<Organization> organizations = new ArrayList<>();
        int size = dis.readInt();
        for (int org = 0; org < size; org++) {
            String name = dis.readUTF();
            String url = dis.readUTF();
            String checkUrl = url.isEmpty() ? url = null : url;
            List<Organization.Position> positions = new ArrayList<>();
            organizations.add(new Organization(new Link(name, checkUrl), positions));
            readWithException(dis, () -> {
                LocalDate startDate = readLocalDate(dis);
                LocalDate endDate = readLocalDate(dis);
                String title = dis.readUTF();
                String description = dis.readUTF();
                String checkDescription = description.isEmpty() ? description = null : description;
                positions.add(new Organization.Position(startDate, endDate, title, checkDescription));
                return positions;
            });
        }
        resume.setSection(sectionType, new OrganizationSection(organizations));
    }

    private void writeLocalDate(DataOutputStream dos, LocalDate localDate) throws IOException {
        dos.writeInt(localDate.getYear());
        dos.writeUTF(localDate.getMonth().name());
    }

    private LocalDate readLocalDate(DataInputStream dis) throws IOException {
        return LocalDate.of(dis.readInt(), Month.valueOf(dis.readUTF()), 1);
    }

    private <T> void writeWithException(Collection<T> collection, DataOutputStream dos, WriteCollections<T>
            writeCollections) throws IOException {
        dos.writeInt(collection.size());
        for (T element : collection) {
            writeCollections.write(element);
        }
    }

    private <T> void readWithException(DataInputStream dis, ReadCollections<T> readCollections) throws IOException {
        int size = dis.readInt();
        List<T> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(readCollections.read());
        }
    }

    private void readElement(DataInputStream dis, ReadElement readElement) throws IOException {
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            readElement.read();
        }
    }

    @FunctionalInterface
    private interface WriteCollections<T> {
        void write(T t) throws IOException;
    }

    private interface ReadCollections<T> {
        T read() throws IOException;
    }

    private interface ReadElement {
        void read() throws IOException;
    }
}
