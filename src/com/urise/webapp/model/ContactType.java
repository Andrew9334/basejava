package com.urise.webapp.model;

public enum ContactType {
    PHONENUMBER("Тел.: "),
    SKYPE("Skype: "),
    EMAIL("Почта: "),
    PROFILELINKEDIN("Профиль LinkedIn"),
    PROFILEGITHUB("Профиль GitHub"),
    PROFILESTACKOVERFLOW("Профиль StackOverflow"),
    HOMEPAGE("Домшнаяя страница");

    private String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
