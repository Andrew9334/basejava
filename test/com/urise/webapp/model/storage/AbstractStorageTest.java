package com.urise.webapp.model.storage;

import com.urise.webapp.Storage;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.*;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public abstract class AbstractStorageTest {
    protected static final File STORAGE_DIR = new File("C:\\Users\\MSI\\Desktop\\testStorage");
    protected final Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";

    private static final Resume RESUME_1;
    private static final Resume RESUME_2;
    private static final Resume RESUME_3;
    private static final Resume RESUME_4;

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    static {
        RESUME_1 = new Resume(UUID_1, "Name1");
        RESUME_2 = new Resume(UUID_2, "Name2");
        RESUME_3 = new Resume(UUID_3, "Name3");
        RESUME_4 = new Resume(UUID_4, "Name4");

        RESUME_1.addContact(ContactType.EMAIL, "vasilkov@gmail.com");
        RESUME_1.addContact(ContactType.PHONENUMBER, "8526486");
        RESUME_1.addSection(SectionType.OBJECTIVE, new TextSection("Object"));
        RESUME_1.addSection(SectionType.PERSONAL, new TextSection("Personal data"));
        RESUME_1.addSection(SectionType.ACHIEVEMENTS, new ListSection("Achievements data1", "Achievements data2",
                "Achievements data3"));
        RESUME_1.addSection(SectionType.QUALIFICATIONS, new ListSection("Java", "SQL", "JavaScript"));
        RESUME_1.addSection(SectionType.EXPERIENCE, new OrganizationSection(
                new Organization("Organization", "http://Organization.ru",
                        new Organization.Position(2014, Month.AUGUST, "position", "content"),
                        new Organization.Position(2019, Month.FEBRUARY, "position1",
                                "content1"))));
        RESUME_1.addSection(SectionType.EDUCATION,
                new OrganizationSection(new Organization("University", null,
                        new Organization.Position(2000, Month.SEPTEMBER, 2005, Month.MAY,
                                "student", "IT faculty"),
                        new Organization.Position(2006, Month.SEPTEMBER, 2010, Month.MAY,
                                "student", "IT faculty")),
                        new Organization("Organization1", "http://Organization1.ru")));
        RESUME_2.addContact(ContactType.SKYPE, "skypedata");
        RESUME_2.addContact(ContactType.PHONENUMBER, "9863254");
        RESUME_2.addSection(SectionType.EXPERIENCE, new OrganizationSection(
                new Organization("Organization2", "http://Organization2.ru",
                        new Organization.Position(2017, Month.FEBRUARY, "position40",
                                "content2"))));
    }

    @Before
    public void setUp() throws Exception{
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void clear() throws Exception{
        storage.clear();
        assertSize(0);
    }

    @Test
    public void save() throws Exception {
        storage.save(RESUME_4);
        assertSize(4);
        assertGet(RESUME_4);
    }

    @Test
    public void update() throws Exception {
        Resume RESUME_1 = new Resume(UUID_1, "");
        storage.update(RESUME_1);
        assertEquals(RESUME_1, storage.get(UUID_1));
    }

    @Test
    public void get() throws Exception {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() throws Exception {
        storage.delete(RESUME_1.getUuid());
        assertSize(2);
        storage.get(UUID_1);
    }

    @Test
    public void getAllSorted() throws Exception {
        List<Resume> list = storage.getAllSorted();
        assertEquals(3, list.size());
        assertEquals(list, Arrays.asList(RESUME_1, RESUME_2, RESUME_3));
    }

    @Test
    public void size() {
        assertSize(3);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get(RESUME_4.getUuid());
    }

    @Test(expected = StorageException.class)
    public void saveExist() {
        storage.save(RESUME_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.get("dummy");
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete("dummy");
    }

    private void assertSize(int size) {
        assertEquals(size, storage.size());
    }

    private void assertGet(Resume resume) {
        assertEquals(resume, storage.get(resume.getUuid()));
    }
}