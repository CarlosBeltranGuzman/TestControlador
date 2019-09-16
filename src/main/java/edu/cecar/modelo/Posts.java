package edu.cecar.modelo;

public class Posts {
    private String post_id, name, email, bodybody;

    public Posts(String post_id, String name, String email, String bodybody) {
        this.post_id = post_id;
        this.name = name;
        this.email = email;
        this.bodybody = bodybody;
    }

    public Posts() {
        this.post_id = "";
        this.name = "";
        this.email = "";
        this.bodybody = "";
    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBodybody() {
        return bodybody;
    }

    public void setBodybody(String bodybody) {
        this.bodybody = bodybody;
    }
}