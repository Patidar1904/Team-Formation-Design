package com.ext.teamformation.Modal;

public class RequestModal {
    int Id ;
    String Project;
    String Laguage;
    String Accept;

    public RequestModal() {
    }

    public RequestModal(String project, String laguage, String accept) {
        Project = project;
        Laguage = laguage;
        Accept = accept;
    }

    public RequestModal(int id, String project, String laguage, String accept) {
        Id = id;
        Project = project;
        Laguage = laguage;
        Accept = accept;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getProject() {
        return Project;
    }

    public void setProject(String project) {
        Project = project;
    }

    public String getLaguage() {
        return Laguage;
    }

    public void setLaguage(String laguage) {
        Laguage = laguage;
    }

    public String getAccept() {
        return Accept;
    }

    public void setAccept(String accept) {
        Accept = accept;
    }
}
