package com.example.dagger2demo.LocalData;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "LocalUser")
public class LocalUser {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name="id")
    private int id;

    @ColumnInfo(name="userId")
    private String userId;

    @ColumnInfo(name="username")
    private String username;

    @ColumnInfo(name="email")
    private String email;

    @ColumnInfo(name="website")
    private String website;


    public LocalUser() {
    }


    @Ignore
    public LocalUser(String userId, String username, String email, String website) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.website = website;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Override
    public String toString() {
        return new StringBuilder(username).append("\n").append(userId).append("\n").append(email).append("\n").append(website).toString();
    }
}
