package com.ext.teamformation.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ext.teamformation.Activity.RequestActivity;
import com.ext.teamformation.Modal.PeopleModal;
import com.ext.teamformation.Modal.ProjectModal;
import com.ext.teamformation.Modal.RequestModal;
import com.ext.teamformation.Modal.UserModal;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contactsManager";
    private static final String TABLE_USER = "USERMODAL";
    private static final String TABLE_PROJECT = "TABLE_PROJECT";
    private static final String TABLE_PEOPLE = "TABLE_PEOPLE";
    private static final String userId = "userId";
    private static final String project_name = "project_name";
    private static final String project_description = "project_description";
    private static final String project_TeamMember = "project_TeamMember";
    private static final String people_name = "people_name";
    private static final String people_language = "people_language";
    private static final String people_experience = "people_experience";
    private static final String people_ratings = "people_ratings";
    private static final String firtName = "firtName";
    private static final String lastName = "lastName";
    private static final String email = "email";
    private static final String birthDate = "birthDate";
    private static final String gender = "gender";
    private static final String location = "location";
    private static final String mobile = "mobile";
    private static final String skill = "skill";
    private static final String expreience = "expreience";
    private static final String resume = "resume";
    private static final String certificate = "certificate";
    private static final String githubLink = "githubLink";
    private static final String summary = "summary";
    private static final String Request_Project = "Request_Project";
    private static final String Request_Language = "Request_Language";
    private static final String Request_Accept = "Request_Accept";
    private static final String Table_Request = "Table_Request";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance  
    }

    // Creating Tables

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
                + userId + " INTEGER PRIMARY KEY," + firtName + " TEXT," + lastName + " TEXT," + email + " TEXT," + birthDate + " TEXT,"
                + gender + " TEXT," + location + " TEXT," + mobile + " TEXT," + skill + " TEXT," + expreience + " TEXT,"
                + resume + " TEXT," + certificate + " TEXT," + githubLink + " TEXT," + summary + " TEXT" + ")";
        db.execSQL(CREATE_USER_TABLE);

        String CREATE_PPROJECT = "CREATE TABLE " + TABLE_PROJECT + "("
                + userId + " INTEGER PRIMARY KEY," + project_name + " TEXT," + project_description + " TEXT,"+ project_TeamMember + " TEXT" + ")";
        db.execSQL(CREATE_PPROJECT);

        String CREATE_PEOPLE = "CREATE TABLE " + TABLE_PEOPLE + "("
                + userId + " INTEGER PRIMARY KEY," + people_name + " TEXT,"
                + people_language + " TEXT,"+ people_experience + " TEXT," + people_ratings + " TEXT" + ")";
        db.execSQL(CREATE_PEOPLE);

        String CREATE_Request = "CREATE TABLE " + Table_Request + "("
                + userId + " INTEGER PRIMARY KEY," + Request_Project + " TEXT,"
                + Request_Language + " TEXT,"+ Request_Accept + " TEXT" + ")";
        db.execSQL(CREATE_Request);

    }

    // Upgrading database  
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed  
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);

        // Create tables again  
        onCreate(db);
    }

    // code to add the new UserModal
    public void addRegister(UserModal userModal) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(firtName, userModal.getFirtName());
        values.put(lastName, userModal.getLastName());
        values.put(email, userModal.getEmail());
        values.put(birthDate, userModal.getBirthDate());
        values.put(gender, userModal.getGender());
        values.put(location, userModal.getLocation());
        values.put(mobile, userModal.getMobile());
        values.put(skill, userModal.getSkill());
        values.put(expreience, userModal.getExpreience());
        values.put(resume, userModal.getResume());
        values.put(certificate, userModal.getCertificate());
        values.put(githubLink, userModal.getGithubLink());
        values.put(summary, userModal.getSummary());

        // Inserting Row  
        db.insert(TABLE_USER, null, values);
        //2nd argument is String containing nullColumnHack  
        db.close(); // Closing database connection  
    }

    public void addProject(ProjectModal projectModal) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(project_name, projectModal.getProjectName());
        values.put(project_description, projectModal.getProjectDescription());
        values.put(project_TeamMember, projectModal.getProjectMember());

        // Inserting Row
        db.insert(TABLE_PROJECT, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    public void addRequest(RequestModal requestModal) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Request_Project, requestModal.getProject());
        values.put(Request_Language, requestModal.getLaguage());
        values.put(Request_Accept, requestModal.getAccept());

        // Inserting Row
        db.insert(Table_Request, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    public void addPeople(PeopleModal peopleModal) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(people_name, peopleModal.getPeopleName());
        values.put(people_language, peopleModal.getPeopleLangauge());
        values.put(people_experience, peopleModal.getPeopleExperience());
        values.put(people_ratings, peopleModal.getPeopleRatings());

        // Inserting Row
        db.insert(TABLE_PEOPLE, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }


    // code to get the single userData
    UserModal getUserData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_USER, new String[]{userId,
                         firtName,  lastName,  email,  birthDate,  gender,  location,
                         mobile,  skill,  expreience,
                         resume,  certificate,  githubLink,  summary}, userId + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        UserModal userModal = new UserModal(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3),
                cursor.getString(4), cursor.getString(5), cursor.getString(6),
                cursor.getString(7), cursor.getString(8), cursor.getString(9),
                cursor.getString(10), cursor.getString(11), cursor.getString(12),
                cursor.getString(13));

        return userModal;
    }

    // code to get all UserModal in a list view  
    public List<UserModal> getAllUser() {
        List<UserModal> contactList = new ArrayList<UserModal>();
        // Select All Query  
        String selectQuery = "SELECT  * FROM " + TABLE_USER;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        // looping through all rows and adding to list  
        if (cursor.moveToFirst()) {
            do {
                UserModal userModal = new UserModal();
                userModal.setUserId(Integer.parseInt(cursor.getString(0)));
                userModal.setFirtName(cursor.getString(1));
                userModal.setLastName(cursor.getString(2));
                userModal.setEmail(cursor.getString(3));
                userModal.setBirthDate(cursor.getString(4));
                userModal.setGender(cursor.getString(5));
                userModal.setLocation(cursor.getString(6));
                userModal.setMobile(cursor.getString(7));
                userModal.setSkill(cursor.getString(8));
                userModal.setExpreience(cursor.getString(9));
                userModal.setResume(cursor.getString(10));
                userModal.setCertificate(cursor.getString(11));
                userModal.setGithubLink(cursor.getString(12));
                userModal.setSummary(cursor.getString(13));
                // Adding userModal to list
                contactList.add(userModal);
            } while (cursor.moveToNext());
        }

        // return userModal list  
        return contactList;
    }

    public List<ProjectModal> getAllProject() {
        List<ProjectModal> list = new ArrayList<ProjectModal>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PROJECT;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ProjectModal projectModal = new ProjectModal();
                projectModal.setId(Integer.parseInt(cursor.getString(0)));
                projectModal.setProjectName(cursor.getString(1));
                projectModal.setProjectDescription(cursor.getString(2));
                projectModal.setProjectMember(cursor.getString(3));
                // Adding userModal to list
                list.add(projectModal);
            } while (cursor.moveToNext());
        }

        return list;
    }

    public List<PeopleModal> getAllPeople() {
        List<PeopleModal> list = new ArrayList<PeopleModal>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PEOPLE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                PeopleModal peopleModal = new PeopleModal();
                peopleModal.setId(Integer.parseInt(cursor.getString(0)));
                peopleModal.setPeopleName(cursor.getString(1));
                peopleModal.setPeopleLangauge(cursor.getString(2));
                peopleModal.setPeopleExperience(cursor.getString(3));
                peopleModal.setPeopleRatings(cursor.getString(4));
                // Adding userModal to list
                list.add(peopleModal);
            } while (cursor.moveToNext());
        }

        return list;
    }

    public List<RequestModal> getAllRequest() {
        List<RequestModal> list = new ArrayList<RequestModal>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + Table_Request;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                RequestModal requestModal = new RequestModal();
                requestModal.setId(Integer.parseInt(cursor.getString(0)));
                requestModal.setProject(cursor.getString(1));
                requestModal.setLaguage(cursor.getString(2));
                requestModal.setAccept(cursor.getString(3));
                // Adding userModal to list
                list.add(requestModal);
            } while (cursor.moveToNext());
        }

        return list;
    }

    public void deleteallentry() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete  from " + TABLE_PEOPLE);
    }

    public void deleteProject() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete  from " + TABLE_PROJECT);
    }

    public int updateRequest(RequestModal requestModal) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Request_Project, requestModal.getProject());
        values.put(Request_Language, requestModal.getLaguage());
        values.put(Request_Accept, requestModal.getAccept());

        // updating row
        return db.update(Table_Request, values, userId + " = ?",
                new String[]{String.valueOf(requestModal.getId())});
    }



} 