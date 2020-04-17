package com.ext.teamformation.Modal;

public class ProjectModal {

    int Id ;
    String projectName;
    String projectDescription;
    String projectMember;

    public ProjectModal() {
    }

    public ProjectModal(int id, String projectName, String projectDescription, String projectMember) {
        Id = id;
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.projectMember = projectMember;
    }

    public ProjectModal(String projectName, String projectDescription, String projectMember) {
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.projectMember = projectMember;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public String getProjectMember() {
        return projectMember;
    }

    public void setProjectMember(String projectMember) {
        this.projectMember = projectMember;
    }
}
