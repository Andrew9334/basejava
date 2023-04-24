package com.urise.webapp;

import com.urise.webapp.model.Resume;
import com.urise.webapp.model.storage.AbstractArrayStorage;
import com.urise.webapp.model.storage.ArrayStorage;
import com.urise.webapp.model.storage.SortedArrayStorage;

/**
 * Test for your com.urise.webapp.model.storage.ArrayStorage implementation
 */
public class MainTestArrayStorage {
    static final ArrayStorage ARRAY_STORAGE = new ArrayStorage();

    public static void main(String[] args) {
        AbstractArrayStorage arrayStorage = new ArrayStorage();
        SortedArrayStorage sortedArrayStorage = new SortedArrayStorage();

        Resume r1 = new Resume();
        r1.setUuid("uuid1");
        Resume r2 = new Resume();
        r2.setUuid("uuid2");
        Resume r3 = new Resume();
        r3.setUuid("uuid3");

        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r3);

        ARRAY_STORAGE.update(r1);
        ARRAY_STORAGE.update(r2);
        ARRAY_STORAGE.update(r3);

        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size());

        System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));

        printAll();
        ARRAY_STORAGE.delete(r1.getUuid());
        arrayStorage.actionOnResume(r1,r2,r3);
        sortedArrayStorage.sort(r1, sortedArrayStorage.getIndex(r1.getUuid()));
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());


    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAll()) {
            System.out.println(r);
        }
    }
}
