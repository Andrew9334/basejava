package com.urise.webapp.storage.strategy;

import com.urise.webapp.model.*;

import java.io.*;
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
            for (Map.Entry<SectionType, Section> sectionEntry : sections.entrySet()) {
                dos.writeUTF(sectionEntry.getKey().name());
                switch (sectionEntry.getKey()) {
                    case PERSONAL:
                    case ACHIEVEMENTS:
                    case EDUCATION:
                        break;
                    case OBJECTIVE:
                        dos.writeUTF(((TextSection) sectionEntry.getValue()).getContent());
                        break;
                    case QUALIFICATIONS:
                        List<String> list = ((ListSection) sectionEntry.getValue()).getItems();
                        dos.writeInt(list.size());
                        for (String listQua : list) {
                            dos.writeUTF(listQua);
                        }
                        break;
                    case EXPERIENCE:
                        List<Organization> organizationSectionList = ((OrganizationSection) sectionEntry.getValue()).getOrganizations();
                        dos.writeInt(organizationSectionList.size());
                        writeOrganizations(dos, organizationSectionList);
                        break;
                    default:
                        break;
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
            int sizeSections = dis.readInt();
            for (int i = 0; i < sizeSections; i++) {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                switch (sectionType) {
                    case PERSONAL:
                    case OBJECTIVE:
                        return new Resume(dis.readUTF());
                    case ACHIEVEMENTS:
                    case QUALIFICATIONS:
                        List<String> list = new ArrayList<>();
                        int sizeList = dis.readInt();
                        for (int j = 0; j < sizeList; j++) {
                            list.add(dis.readUTF());
                        }
                        resume.addSection(sectionType, new ListSection(list));
                        break;
                    case EDUCATION:
                    case EXPERIENCE:
                        readOrganization(dis, sectionType, resume);
                        break;
                    default:
                        break;
                }
            }
            return resume;
        }
    }

    private void writeOrganizations(DataOutputStream dos, List<Organization> organizationList) throws IOException {
        for (Organization org : organizationList) {
            dos.writeUTF(org.getHomePage().getName());
            if (org.getHomePage().getUrl() != null) {
                dos.writeUTF(org.getHomePage().getUrl());
            } else {
                dos.writeUTF("");
            }

            List<Organization.Position> listOrgPos = org.getPositions();
            dos.writeInt(listOrgPos.size());
            for (Organization.Position position : listOrgPos) {
                dos.writeInt(position.getStartDate().getYear());
                dos.writeUTF(position.getStartDate().getMonth().name());
                dos.writeInt(position.getEndDate().getYear());
                dos.writeUTF(position.getEndDate().getMonth().name());
                dos.writeUTF(position.getTitle());
                if (position.getDescription() != null) {
                    dos.writeUTF(position.getDescription());
                } else {
                    dos.writeUTF("");
                }
            }
        }
    }

    private void readOrganization(DataInputStream dis, SectionType sectionType, Resume resume) throws IOException {
        List<Organization> organizationList = new ArrayList<>();
        int sizeOrg = dis.readInt();
        for (int i = 0; i < sizeOrg; i++) {
            String name = dis.readUTF();
            String url = dis.readUTF();
            if (url.equals("")) {
                url = null;
            }

            List<Organization.Position> positionList = new ArrayList<>();
            organizationList.add(new Organization(new Link(name, url), positionList));
            int sizePosition = dis.readInt();
            for (int j = 0; j < sizePosition; j++) {
                int startDate = dis.readInt();
                Month monthStart = Month.valueOf(dis.readUTF());
                int endDate = dis.readInt();
                Month monthEnd = Month.valueOf(dis.readUTF());
                String title = dis.readUTF();
                String description = dis.readUTF();
                if (description.equals("")) {
                    description = null;
                }
                positionList.add(new Organization.Position(startDate, monthStart, endDate, monthEnd,
                        title, description));
            }
        }
        resume.addSection(sectionType, new OrganizationSection(organizationList));
    }
}
