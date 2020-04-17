package com.ext.teamformation.Modal;

public class PeopleModal {

    int Id ;
    String peopleName;
    String peopleLangauge;
    String peopleExperience;
    String peopleRatings;

    public PeopleModal(int id, String peopleName, String peopleLangauge, String peopleExperience, String peopleRatings) {
        Id = id;
        this.peopleName = peopleName;
        this.peopleLangauge = peopleLangauge;
        this.peopleExperience = peopleExperience;
        this.peopleRatings = peopleRatings;
    }

    public PeopleModal() {
    }

    public PeopleModal(String peopleName, String peopleLangauge, String peopleExperience, String peopleRatings) {
        this.peopleName = peopleName;
        this.peopleLangauge = peopleLangauge;
        this.peopleExperience = peopleExperience;
        this.peopleRatings = peopleRatings;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getPeopleName() {
        return peopleName;
    }

    public void setPeopleName(String peopleName) {
        this.peopleName = peopleName;
    }

    public String getPeopleLangauge() {
        return peopleLangauge;
    }

    public void setPeopleLangauge(String peopleLangauge) {
        this.peopleLangauge = peopleLangauge;
    }

    public String getPeopleExperience() {
        return peopleExperience;
    }

    public void setPeopleExperience(String peopleExperience) {
        this.peopleExperience = peopleExperience;
    }

    public String getPeopleRatings() {
        return peopleRatings;
    }

    public void setPeopleRatings(String peopleRatings) {
        this.peopleRatings = peopleRatings;
    }
}
