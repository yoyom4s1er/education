package com.example.education.dao;

import com.example.education.model.User;

public class UserInfo {
    private String mail;
    private String firstName;
    private String lastName;
    private String thirdName;
    private boolean task1Completed;
    private int task1Mistakes;
    private boolean task2Completed;
    private int task2Mistakes;
    private boolean task3Completed;
    private int task3Mistakes;
    private boolean task4Completed;
    private int task4Mistakes;

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getThirdName() {
        return thirdName;
    }

    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }

    public boolean isTask1Completed() {
        return task1Completed;
    }

    public void setTask1Completed(boolean task1Completed) {
        this.task1Completed = task1Completed;
    }

    public int getTask1Mistakes() {
        return task1Mistakes;
    }

    public void setTask1Mistakes(int task1Mistakes) {
        this.task1Mistakes = task1Mistakes;
    }

    public boolean isTask2Completed() {
        return task2Completed;
    }

    public void setTask2Completed(boolean task2Completed) {
        this.task2Completed = task2Completed;
    }

    public int getTask2Mistakes() {
        return task2Mistakes;
    }

    public void setTask2Mistakes(int task2Mistakes) {
        this.task2Mistakes = task2Mistakes;
    }

    public boolean isTask3Completed() {
        return task3Completed;
    }

    public void setTask3Completed(boolean task3Completed) {
        this.task3Completed = task3Completed;
    }

    public int getTask3Mistakes() {
        return task3Mistakes;
    }

    public void setTask3Mistakes(int task3Mistakes) {
        this.task3Mistakes = task3Mistakes;
    }

    public boolean isTask4Completed() {
        return task4Completed;
    }

    public void setTask4Completed(boolean task4Completed) {
        this.task4Completed = task4Completed;
    }

    public int getTask4Mistakes() {
        return task4Mistakes;
    }

    public void setTask4Mistakes(int task4Mistakes) {
        this.task4Mistakes = task4Mistakes;
    }

    public static UserInfo fromUser(User user) {
        UserInfo created = new UserInfo();
        created.mail = user.getMail();
        created.firstName = user.getFirstName();
        created.lastName = user.getLastName();
        created.thirdName = user.getThirdName();
        created.task1Completed = user.isTask1Completed();
        created.task1Mistakes = user.getTask1Mistakes();
        created.task2Completed = user.isTask2Completed();
        created.task2Mistakes = user.getTask2Mistakes();
        created.task3Completed = user.isTask3Completed();
        created.task3Mistakes = user.getTask3Mistakes();
        created.task4Completed = user.isTask4Completed();
        created.task4Mistakes = user.getTask4Mistakes();

        return created;
    }
}
