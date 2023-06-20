package com.urise.webapp.model;

import com.urise.webapp.util.DateUtil;

import java.time.Month;

public class ResumeTestData {
    public static void main(String[] args) {
        fillResume("1", "Ivanov Ivan Ivanovich");
    }

    public static Resume fillResume(String uuid, String fullName) {
        Resume resume = new Resume(uuid, fullName);
        CompanySection.Company company = new CompanySection.Company();
        CompanySection.Company.Period period = new CompanySection.Company.Period();
        period.setTitle("Horns and hooves");
        period.setDescription("Trade company");
        DateUtil.of(2010, Month.of(6));
        company.setName("Horns and hooves");
        company.setWebSite("www.hornsandhooves.ussr");
        CompanySection.company.add(period);

        return null;
    }
}
