package com.ext.teamformation.Activity;

public class AnimalNames {
    private String animalName;

    private String name, lang, exp, rate;

    public AnimalNames(String name, String lang, String exp, String rate) {
        this.name = name;
        this.lang= lang;
        this.exp = exp;
        this.rate=rate;

    }

    public String getAnimalName() {
        return this.name;
    }
    public String getLang() {
        return this.lang;
    }
    public String getExp() {
        return this.exp;
    }
    public String getRate() {
        return this.rate;
    }

}