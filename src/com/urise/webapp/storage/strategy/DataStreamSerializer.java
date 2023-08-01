package com.urise.webapp.storage.strategy;

import com.urise.webapp.model.*;

import java.io.*;
import java.time.Month;
import java.time.Period;
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
            int size = dos.size();
            for (Map.Entry<SectionType, Section> entry : sections.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                switch (entry.getKey()) {
                    case PERSONAL:
                    case OBJECTIVE:
                    case ACHIEVEMENTS:
                    case QUALIFICATIONS:
                        dos.writeUTF(((TextSection) entry.getValue()).getContent());
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        List<Organization> organizations = (((OrganizationSection) entry.getValue()).getOrganizations());
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
            return resume;
        }
    }
}
