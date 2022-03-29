package com.example.memvoca.database.zerobox;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ZeroBox {
    public ZeroBox(int id, String word, String pronunciation, String meaning, String etymology, String sod) {
        this.id = id;
        this.word = word;
        this.pronunciation = pronunciation;
        this.meaning = meaning;
        this.etymology = etymology;
        this.sod = sod;
    }

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    int id;

    @ColumnInfo(name = "word")
    private String word;

    @ColumnInfo(name = "pronunciation")
    private String pronunciation;

    @ColumnInfo(name = "meaning")
    private String meaning;

    @ColumnInfo(name = "etymology")
    private String etymology;

    @ColumnInfo(name = "sod")
    private String sod;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getPronunciation() {
        return pronunciation;
    }

    public void setPronunciation(String pronunciation) {
        this.pronunciation = pronunciation;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getEtymology() {
        return etymology;
    }

    public void setEtymology(String etymology) {
        this.etymology = etymology;
    }

    public String getSod() {
        return sod;
    }

    public void setSod(String sod) {
        this.sod = sod;
    }

    @NonNull
    @Override
    public String toString() {
        return  "\n[ id" + id +
                "\nword=" + word +
                "\npronunciation=" + pronunciation +
                "\nmeaning=" + meaning +
                "]\n";
    }
}
