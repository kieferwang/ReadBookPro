package com.krisitine.readbookpro.bean;

/**
 * Created by Kristine on 2016/1/24.
 */
public class Book {
    private String Title;
    private String Author;
    private String Bitmap;
    private String page;
    private String percent;

    private String Url;

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getPage() {
        return page;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getBitmap() {
        return Bitmap;
    }

    public void setBitmap(String bitmap) {
        Bitmap = bitmap;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }
}
