package com.example.demo.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Notebook")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String date;
    private String title;
    private String text;

    public Note() {
    }

    public Note(String date, String title, String text) {
        this.date = date;
        this.title = title;
        this.text = text;
    }
    public Note(String title, String text) {
        this.title = title;
        this.text = text;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
