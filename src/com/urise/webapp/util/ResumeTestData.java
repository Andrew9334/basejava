package com.urise.webapp.util;

import com.urise.webapp.model.*;

import java.time.Month;

public class ResumeTestData extends Resume {
    public Resume fillResume(String uuid, String fullName) {
        final Resume resume = new Resume(uuid, fullName);

        resume.addContact(ContactType.EMAIL, "mail1@icloud.com");
        resume.addContact(ContactType.SKYPE, "skype1");
        resume.addContact(ContactType.PHONENUMBER, "89887526321");
        resume.addContact(ContactType.HOMEPAGE, "http://homepage1.com");
        resume.addContact(ContactType.PROFILEGITHUB, "http://github.com/github1");
        resume.addContact(ContactType.PROFILELINKEDIN, "http://www.linkedin.com/in/linkedin1");
        resume.addContact
                (ContactType.PROFILESTACKOVERFLOW, "https://stackoverflow.com/users/654845/stackoverflow1");
        resume.addSection(SectionType.PERSONAL, new TextSection("Personal1"));
        resume.addSection(SectionType.OBJECTIVE, new TextSection("Objective1"));
        resume.addSection(SectionType.ACHIEVEMENTS,
                new TextSection("Achievements1"));
        resume.addSection(SectionType.QUALIFICATIONS, new TextSection("Qualifications1"));
        resume.addSection(SectionType.EXPERIENCE, new OrganizationSection
                (new Organization("Organization1", null,
                        new Organization.Position(2004, Month.SEPTEMBER, 2008, Month.MAY,
                                "Analyst", "IT Department"))));
        resume.addSection(SectionType.EDUCATION, new OrganizationSection(
                new Organization("University", null,
                        new Organization.Position(1999, Month.SEPTEMBER, 2005, Month.JUNE,
                                "student", "IT Faculty"))));
        return resume;
    }
}