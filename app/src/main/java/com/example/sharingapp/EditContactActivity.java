package com.example.sharingapp;

import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Editing a pre-existing contact consists of deleting the old contact and adding a new contact with the old
 * contact's id.
 * Note: You will not be able contacts which are "active" borrowers
 */
public class EditContactActivity extends AppCompatActivity implements Observer {

    private ContactList contact_list = new ContactList();
    private Contact contact;

    private ContactListController contact_list_controller = new ContactListController(contact_list);

    private ContactController contact_controller;

    private EditText email;
    private EditText username;
    private Context context;

    private int pos;

    private Boolean on_create_update = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        context = getApplicationContext();

        Intent intent = getIntent();
        pos = intent.getIntExtra("position", 0);

        contact = contact_list_controller.getContact(pos);

        username = (EditText) findViewById(R.id.username);
        email = (EditText) findViewById(R.id.email);

        username.setText(contact.getUsername());
        email.setText(contact.getEmail());

        on_create_update = true;
        contact_list_controller.addObserver(this);
        contact_list_controller.loadContacts(context);
    }

    private void showToast(String text) {
        int duration = Toast.LENGTH_SHORT;
        Toast.makeText(context, text, duration).show();
    }

    public void saveContact(View view) {

        String email_str = email.getText().toString();

        if (email_str.equals("")) {
            email.setError("Empty field!");
            return;
        }

        if (!email_str.contains("@")) {
            email.setError("Must be an email address!");
            return;
        }

        String username_str = username.getText().toString();
        String id = contact.getId(); // Reuse the contact id

        // Check that username is unique AND username is changed (Note: if username was not changed
        // then this should be fine, because it was already unique.)
        if (!contact_list.isUsernameAvailable(username_str) && !(contact.getUsername().equals(username_str))) {
            username.setError("Username already taken!");
            return;
        }

        Contact updated_contact = new Contact(username_str, email_str, id);

        if (contact_list_controller.editContact(contact, updated_contact, context)) {
            showToast("Contact updated");
            finish();
        } else showToast("Failed to update contact");
    }

    public void deleteContact(View view) {
        if (contact_list_controller.deleteContact(contact, context)) {
            showToast("Contact deleted");
            finish();
        } else showToast("Failed to delete contact");
    }

    @Override
    public void update() {
        if (on_create_update && contact_list_controller.getSize() > pos) {
            contact = contact_list_controller.getContact(pos);
            contact_controller = new ContactController(contact);
            username.setText(contact_controller.getUsername());
            email.setText(contact_controller.getEmail());
            on_create_update = false;
        }
    }
}
