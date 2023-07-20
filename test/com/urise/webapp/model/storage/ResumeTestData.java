package com.urise.webapp.model.storage;

import com.urise.webapp.model.*;

import java.time.Month;

public class ResumeTestData extends Resume {
    private static Resume RESUME_1;
    private static Resume RESUME_2;
    private static Resume RESUME_3;
    private static Resume RESUME_4;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";

    public ResumeTestData(Resume resume) {
        RESUME_1 = resume;
        RESUME_2 = resume;
        RESUME_3 = resume;
        RESUME_4 = resume;
    }

    static {
        RESUME_1 = new Resume(UUID_1, "name1");
        RESUME_2 = new Resume(UUID_2, "name2");
        RESUME_3 = new Resume(UUID_3, "name3");
        RESUME_4 = new Resume(UUID_4, "name4");

        RESUME_1.addContact(ContactType.EMAIL, "mail1@icloud.com");
        RESUME_2.addContact(ContactType.EMAIL, "mail2@icloud.com");
        RESUME_3.addContact(ContactType.EMAIL, "mail3@icloud.com");
        RESUME_4.addContact(ContactType.EMAIL, "mail4@icloud.com");

        RESUME_1.addContact(ContactType.SKYPE, "skype1");
        RESUME_2.addContact(ContactType.SKYPE, "skype2");
        RESUME_3.addContact(ContactType.SKYPE, "skype3");
        RESUME_4.addContact(ContactType.SKYPE, "skype4");

        RESUME_1.addContact(ContactType.PHONENUMBER, "89887526321");
        RESUME_2.addContact(ContactType.PHONENUMBER, "76315478925");
        RESUME_3.addContact(ContactType.PHONENUMBER, "89635241236");
        RESUME_4.addContact(ContactType.PHONENUMBER, "65328942563");

        RESUME_1.addContact(ContactType.HOMEPAGE, "http://homepage1.com");
        RESUME_2.addContact(ContactType.HOMEPAGE, "http://homepage2.com");
        RESUME_3.addContact(ContactType.HOMEPAGE, "http://homepage3.com");
        RESUME_4.addContact(ContactType.HOMEPAGE, "http://homepage4.com");

        RESUME_1.addContact(ContactType.PROFILEGITHUB, "http://github.com/github1");
        RESUME_2.addContact(ContactType.PROFILEGITHUB, "http://github.com/github2");
        RESUME_3.addContact(ContactType.PROFILEGITHUB, "http://github.com/github3");
        RESUME_4.addContact(ContactType.PROFILEGITHUB, "http://github.com/github4");

        RESUME_1.addContact(ContactType.PROFILELINKEDIN, "http://www.linkedin.com/in/linkedin1");
        RESUME_2.addContact(ContactType.PROFILELINKEDIN, "http://www.linkedin.com/in/linkedin2");
        RESUME_3.addContact(ContactType.PROFILELINKEDIN, "http://www.linkedin.com/in/linkedin3");
        RESUME_4.addContact(ContactType.PROFILELINKEDIN, "http://www.linkedin.com/in/linkedin4");

        RESUME_1.addContact
                (ContactType.PROFILESTACKOVERFLOW, "https://stackoverflow.com/users/654845/stackoverflow1");
        RESUME_1.addContact
                (ContactType.PROFILESTACKOVERFLOW, "https://stackoverflow.com/users/584562/stackoverflow2");
        RESUME_1.addContact
                (ContactType.PROFILESTACKOVERFLOW, "https://stackoverflow.com/users/216248/stackoverflow3");
        RESUME_1.addContact
                (ContactType.PROFILESTACKOVERFLOW, "https://stackoverflow.com/users/346584/stackoverflow4");

        RESUME_1.addSection(SectionType.PERSONAL, new TextSection("Personal1"));
        RESUME_2.addSection(SectionType.PERSONAL, new TextSection("Personal2"));
        RESUME_3.addSection(SectionType.PERSONAL, new TextSection("Personal3"));
        RESUME_4.addSection(SectionType.PERSONAL, new TextSection("Personal4"));

        RESUME_1.addSection(SectionType.OBJECTIVE, new TextSection("Objective1"));
        RESUME_2.addSection(SectionType.OBJECTIVE, new TextSection("Objective2"));
        RESUME_3.addSection(SectionType.OBJECTIVE, new TextSection("Objective3"));
        RESUME_4.addSection(SectionType.OBJECTIVE, new TextSection("Objective4"));

        RESUME_1.addSection(SectionType.ACHIEVEMENTS,
                new TextSection("Achievements1"));
        RESUME_2.addSection(SectionType.ACHIEVEMENTS,
                new TextSection("Achievements2"));
        RESUME_3.addSection(SectionType.ACHIEVEMENTS,
                new TextSection("Achievements3"));
        RESUME_4.addSection(SectionType.ACHIEVEMENTS,
                new TextSection("Achievements4"));

        RESUME_1.addSection(SectionType.QUALIFICATIONS, new TextSection("Qualifications1"));
        RESUME_2.addSection(SectionType.QUALIFICATIONS, new TextSection("Qualifications2"));
        RESUME_3.addSection(SectionType.QUALIFICATIONS, new TextSection("Qualifications3"));
        RESUME_4.addSection(SectionType.QUALIFICATIONS, new TextSection("Qualifications4"));

        RESUME_1.addSection(SectionType.EXPERIENCE, new OrganizationSection
                (new Organization( "Organization1", null,
                        new Organization.Position(2004, Month.SEPTEMBER, 2008, Month.MAY,
                                "Analyst", "IT Department"))));
        RESUME_2.addSection(SectionType.EXPERIENCE, new OrganizationSection
                (new Organization("Organization", null,
                        new Organization.Position(2000, Month.FEBRUARY, 2010, Month.JANUARY,
                                "Java Developer", "IT Department"))));
        RESUME_3.addSection(SectionType.EXPERIENCE, new OrganizationSection(
                new Organization("Organization1", null,
                        new Organization.Position(2010, Month.SEPTEMBER, 2015, Month.SEPTEMBER,
                                "JavaScript Developer", "IT Department"))));
        RESUME_4.addSection(SectionType.EXPERIENCE,
                new OrganizationSection(new Organization("Organization4", null,
                        new Organization.Position(2015, Month.AUGUST, 2018, Month.APRIL,
                                "HR", "HR Department"))));

        RESUME_1.addSection(SectionType.EDUCATION, new OrganizationSection(
                new Organization("University", null,
                        new Organization.Position(1999, Month.SEPTEMBER, 2005, Month.JUNE,
                                "student", "IT Faculty"))));
        RESUME_2.addSection(SectionType.EDUCATION, new OrganizationSection(
                new Organization("University", null,
                        new Organization.Position(1995, Month.SEPTEMBER, 2000, Month.JUNE,
                                "student", "IT Faculty"))));
        RESUME_3.addSection(SectionType.EDUCATION, new OrganizationSection(
                new Organization("University", null,
                        new Organization.Position(2004, Month.SEPTEMBER, 2010, Month.JUNE,
                                "student", "IT Faculty"))));
        RESUME_4.addSection(SectionType.EDUCATION, new OrganizationSection(
                new Organization("University", null,
                        new Organization.Position(2011, Month.SEPTEMBER, 2015, Month.JUNE,
                                "Student", "Management Faculty"))));
    }
}