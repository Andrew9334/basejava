package com.urise.webapp.util;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.*;

import java.time.Month;

public class ResumeTestData extends Resume {
    public Resume fillResume(String uuid, String fullName) {
        final Resume resume = new Resume(uuid, fullName);
        if (fullName == null || fullName == "") {
            return null;
        }

        resume.setContact(ContactType.EMAIL, "mail1@icloud.com");
        resume.setContact(ContactType.SKYPE, "skype1");
        resume.setContact(ContactType.PHONENUMBER, "89887526321");
        resume.setContact(ContactType.HOMEPAGE, "http://homepage1.com");
        resume.setContact(ContactType.PROFILEGITHUB, "http://github.com/github1");
        resume.setContact(ContactType.PROFILELINKEDIN, "http://www.linkedin.com/in/linkedin1");
        resume.setContact
                (ContactType.PROFILESTACKOVERFLOW, "https://stackoverflow.com/users/654845/stackoverflow1");
        resume.setSection(SectionType.PERSONAL, new TextSection("Personal1"));
        resume.setSection(SectionType.OBJECTIVE, new TextSection("Objective1"));
        resume.setSection(SectionType.ACHIEVEMENTS,
                new ListSection("Achievements1"));
        resume.setSection(SectionType.QUALIFICATIONS, new ListSection("Qualifications1"));
        resume.setSection(SectionType.EXPERIENCE, new OrganizationSection
                (new Organization("Organization1", null,
                        new Organization.Position(2004, Month.SEPTEMBER, 2008, Month.MAY,
                                "Analyst", "IT Department"))));
        resume.setSection(SectionType.EDUCATION, new OrganizationSection(
                new Organization("University", null,
                        new Organization.Position(1999, Month.SEPTEMBER, 2005, Month.JUNE,
                                "student", "IT Faculty"))));
        return resume;
    }
}