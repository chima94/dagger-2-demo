package com.example.dagger2demo.LocalData.PostLocalDatabase;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "userPosts")
public class UserPosts {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name="id")
    private int id;

    @ColumnInfo(name="userId")
    private String userId;

    @ColumnInfo(name="title")
    private String title;

    @ColumnInfo(name="body")
    private String body;

    public UserPosts() {
    }

    @Ignore
    public UserPosts(int id, String userId, String title, String body) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.body = body;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return new StringBuilder(userId).append("\n").append(title).append("\n").append(body).toString();
    }
}
