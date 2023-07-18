package com.urise.webapp.util;

import com.google.gson.GsonBuilder;
import com.urise.webapp.model.Section;

import java.io.Reader;
import java.io.Writer;
import java.time.LocalDate;

public class JsonParser {
    private final static com.google.gson.Gson Gson = new GsonBuilder()
            .registerTypeAdapter(Section.class, new JsonSectionAdapter()).
            registerTypeAdapter(LocalDate.class, new JsonLocalDateAdapter())
            .create();

    public static <T> T read(Reader reader, Class<T> clazz) {
        return Gson.fromJson(reader, clazz);
    }

    public static <T> void write(T object, Writer writer) {
        Gson.toJson(object, writer);
    }
}
