package com.urise.webapp.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CompanySection extends AbstractSection {
    static List<Company.Period> company = new ArrayList<>();

    static class Company {
        List<Period> periods = new ArrayList<>();
        private String name;
        private String webSite;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getWebSite() {
            return webSite;
        }

        public void setWebSite(String webSite) {
            this.webSite = webSite;
        }

        static class Period {
            private String title;
            private String description;
            private LocalDate dateStart;
            private LocalDate dateEnd;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public LocalDate getDateStart() {
                return dateStart;
            }

            public void setDateStart(LocalDate dateStart) {
                this.dateStart = dateStart;
            }

            public LocalDate getDateEnd() {
                return dateEnd;
            }

            public void setDateEnd(LocalDate dateEnd) {
                this.dateEnd = dateEnd;
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CompanySection that = (CompanySection) o;
            return Objects.equals(company, that.company);
        }

        @Override
        public int hashCode() {
            return Objects.hash(company);
        }
    }
}

