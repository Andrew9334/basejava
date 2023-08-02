package com.urise.webapp.storage.strategy;

import com.urise.webapp.model.*;

import java.io.*;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.urise.webapp.model.SectionType.valueOf;

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
                    case PERSONAL, OBJECTIVE, ACHIEVEMENTS, QUALIFICATIONS ->
                            dos.writeUTF(((TextSection) entry.getValue()).getContent());
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
            int size = dis.readInt();
            for (int i = 0; i < size; i++) {
                resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }

            for (int i = 0; i < size; i++) {
                SectionType sectionType = valueOf(dis.readUTF());
                switch (sectionType) {
                    case PERSONAL, OBJECTIVE, ACHIEVEMENTS, QUALIFICATIONS ->
                            resume.addSection(sectionType, new TextSection(dis.readUTF()));
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
            if (org.getHomePage().getUrl() != null) {
                dos.writeUTF(org.getHomePage().getUrl());
            } else {
                dos.writeUTF("");
            }

            List<Organization.Position> positions = org.getPositions();
            dos.writeInt(positions.size());
            for (Organization.Position pos : positions) {
                dos.writeInt(pos.getStartDate().getYear());
                dos.writeUTF(pos.getStartDate().getMonth().name());
                dos.writeInt(pos.getEndDate().getYear());
                dos.writeUTF(pos.getEndDate().getMonth().name());
                dos.writeUTF(pos.getTitle());
                if (pos.getDescription() != null) {
                    dos.writeUTF(pos.getDescription());
                } else {
                    dos.writeUTF("");
                }
            }
        }
    }

    private void readOrganizations(DataInputStream dis, SectionType sectionType, Resume resume) throws IOException {
        List<Organization> organizations = new ArrayList<>();
        int size = dis.readInt();
        for (int org = 0; org < size; org++) {
            String name = dis.readUTF();
            String url = dis.readUTF();
            if (url.isEmpty()) {
                url = null;
            }
            List<Organization.Position> positions = new ArrayList<>();
            organizations.add(new Organization(new Link(name, url), positions));
            int sizePos = dis.readInt();
            for (int pos = 0; pos < sizePos; pos++) {
                int startDate = dis.readInt();
                Month monthStartDate = Month.valueOf(dis.readUTF());
                int endDate = dis.readInt();
                Month monthEndDate = Month.valueOf(dis.readUTF());
                String title = dis.readUTF();
                String description = dis.readUTF();
                if (description.isEmpty()) {
                    description = null;
                }
                    positions.add(new Organization.Position(startDate, monthStartDate,
                            endDate, monthEndDate, title, description));
            }
        }
        resume.addSection(sectionType, new OrganizationSection(organizations));
    }
}
