package com.example.sharingapp;

public class ContactController {
    private Contact contact;

    public ContactController(Contact contact) {
        this.contact = contact;
    }

    public void setUsername(String username) {
        contact.setUsername(username);
    }

    public void setEmail(String email) {
        contact.setEmail(email);
    }

    public String getUsername() {
        return contact.getUsername();
    }

    public String getEmail() {
        return contact.getEmail();
    }

    public String getId() {
        return contact.getId();
    }

    public void setId(String id) {
        contact.updateId(id);
    }

    public void addObserver(Observer observer) {
        contact.addObserver(observer);
    }

    public void removeObserver(Observer observer) {
        contact.removeObserver(observer);
    }
}
