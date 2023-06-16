package com.urise.webapp.model;

public class ResumeTestData {
    public static void main(String[] args) {
        Resume resumeTest = new Resume("Andrew");
        TextSection textSection = new TextSection();
        ListSection listSection = new ListSection();
        CompanySection companySection = new CompanySection();
        CompanySection.Company company = new CompanySection.Company();
        CompanySection.Company.Period period = new CompanySection.Company.Period();

        period.setTitle("Horns and hooves");
        period.setDescription("Trade company");
//        period.setDateStart(LocalDate.parse("03/1925"));
//        period.setDateEnd(LocalDate.parse(" - 06/1925"));

        System.out.println(period.getDescription());
        System.out.println(period.getTitle());

        company.setName("Horns and hooves");
        company.setWebSite("www.hornsandhooves.ussr");
        company.periods.add(period);
        companySection.company.add(period);

        for (int i = 0; i < companySection.company.size(); i++) {
            System.out.println(companySection.company.get(i));
        }
    }
}
