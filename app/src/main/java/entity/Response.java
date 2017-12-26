package entity;

import java.io.Serializable;

/**
 * Created by wei on 2017/12/25.
 */

public class Response implements Serializable {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean is_promote() {
        return is_promote;
    }

    public void setIs_promote(boolean is_promote) {
        this.is_promote = is_promote;
    }

    private int id;
    private String image;
    private String title;
    private String description;
    private boolean is_promote;
    @Override
    public String toString() {
        return "Response{" +
                "id=" + id +
                ", image='" + image + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", is_promote=" + is_promote +
                '}';
    }



}
