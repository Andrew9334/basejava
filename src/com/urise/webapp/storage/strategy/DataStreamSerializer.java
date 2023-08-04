package com.urise.webapp.storage.strategy;

import com.urise.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements StreamSerializer {
    @Override
    public void doWrite(Resume resume, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());
            Map<ContactType, String> contacts = resume.getContacts();
            dos.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }
            Map<SectionType, Section> sections = resume.getSections();
            dos.writeInt(sections.size());
            for (Map.Entry<SectionType, Section> entry : sections.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                switch (entry.getKey()) {
                    case PERSONAL, OBJECTIVE -> dos.writeUTF(((TextSection) entry.getValue()).getContent());
                    case ACHIEVEMENTS, QUALIFICATIONS -> {
                        List<String> list = (((ListSection) entry.getValue()).getItems());
                        dos.writeInt(list.size());
                        for (String listAch : list) {
                            dos.writeUTF(listAch);
                        }
                    }
                    case EXPERIENCE, EDUCATION -> {
                        List<Organization> organizations = (((OrganizationSection) entry.getValue()).getOrganizations());
                        dos.writeInt(organizations.size());
                        writeOrganization(dos, organizations);
                    }
                    default -> {
                    }
                }
            }
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            int sizeContacts = dis.readInt();
            for (int i = 0; i < sizeContacts; i++) {
                resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }

            int sizeSections = dis.readInt();
            for (int i = 0; i < sizeSections; i++) {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                switch (sectionType) {
                    case PERSONAL, OBJECTIVE -> resume.addSection(sectionType, new TextSection(dis.readUTF()));
                    case ACHIEVEMENTS, QUALIFICATIONS -> {
                        List<String> list = new ArrayList<>();
                        int size = dis.readInt();
                        for (int ach = 0; ach < size; ach++) {
                            list.add(dis.readUTF());
                        }
                        resume.addSection(sectionType, new ListSection(list));
                    }
                    case EXPERIENCE, EDUCATION -> readOrganizations(dis, sectionType, resume);
                    default -> {
                    }
                }
            }
            return resume;
        }
    }

    private void writeOrganization(DataOutputStream dos, List<Organization> organizations) throws IOException {
        for (Organization org : organizations) {
            dos.writeUTF(org.getHomePage().getName());
            String checkUrl = org.getHomePage().getUrl() != null ? org.getHomePage().getUrl() : "";
            List<Organization.Position> positions = org.getPositions();
            dos.writeInt(positions.size());
            for (Organization.Position pos : positions) {
                writeLocalDate(dos, pos.getStartDate());
                writeLocalDate(dos, pos.getEndDate());
                dos.writeUTF(pos.getTitle());
                dos.writeUTF(pos.getDescription());
                String checkDescription = pos.getDescription() != null ? pos.getDescription() : "";
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
            organizations.add(new Organization(new Link(name, url), positions));
            int sizePos = dis.readInt();
            for (int pos = 0; pos < sizePos; pos++) {
                LocalDate startDate = readLocalDate(dis);
                LocalDate endDate = readLocalDate(dis);
                String title = dis.readUTF();
                String description = dis.readUTF();
                String checkDescription = description.isEmpty() ? description = null : description;
                positions.add(new Organization.Position(startDate, endDate, title, description));
            }
        }
        resume.addSection(sectionType, new OrganizationSection(organizations));
    }

    private void writeLocalDate(DataOutputStream dos, LocalDate localDate) throws IOException {
        dos.writeInt(localDate.getYear());
        dos.writeUTF(localDate.getMonth().name());
    }

    private LocalDate readLocalDate(DataInputStream dis) throws IOException {
//        return LocalDate.of(dis.readInt(), Month.valueOf(dis.readUTF()));
        return null;
    }
}
