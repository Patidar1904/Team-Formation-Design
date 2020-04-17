package com.ext.teamformation.Modal;

public class UserModal {

    int userId ;
    String firtName;
    String lastName;
    String email;
    String birthDate;
    String gender;
    String location;
    String mobile;
    String skill;
    String expreience;
    String resume;
    String certificate;
    String githubLink;
    String summary;

    public UserModal() {
    }
    public UserModal(String firtName, String lastName, String email, String birthDate, String gender, String location, String mobile, String skill, String expreience, String resume, String certificate, String githubLink, String summary) {
        this.userId = userId;
        this.firtName = firtName;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
        this.gender = gender;
        this.location = location;
        this.mobile = mobile;
        this.skill = skill;
        this.expreience = expreience;
        this.resume = resume;
        this.certificate = certificate;
        this.githubLink = githubLink;
        this.summary = summary;
    }

    public UserModal(int userId, String firtName, String lastName, String email, String birthDate, String gender, String location, String mobile, String skill, String expreience, String resume, String certificate, String githubLink, String summary) {
        this.userId = userId;
        this.firtName = firtName;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
        this.gender = gender;
        this.location = location;
        this.mobile = mobile;
        this.skill = skill;
        this.expreience = expreience;
        this.resume = resume;
        this.certificate = certificate;
        this.githubLink = githubLink;
        this.summary = summary;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirtName() {
        return firtName;
    }

    public void setFirtName(String firtName) {
        this.firtName = firtName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getExpreience() {
        return expreience;
    }

    public void setExpreience(String expreience) {
        this.expreience = expreience;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public String getGithubLink() {
        return githubLink;
    }

    public void setGithubLink(String githubLink) {
        this.githubLink = githubLink;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
