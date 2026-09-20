package com.example.sharingapp;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Add a new contact
 */
public class AddContactActivity extends AppCompatActivity {

    private ContactList contact_list = new ContactList();
    private Context context;

    private ContactListController contact_list_controller = new ContactListController(contact_list);

    private EditText username;
    private EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        username = (EditText) findViewById(R.id.username);
        email = (EditText) findViewById(R.id.email);

        context = getApplicationContext();
        contact_list_controller.loadContacts(context);
    }

    private void showToast(String text) {
        int duration = Toast.LENGTH_SHORT;
        Toast.makeText(context, text, duration).show();
    }

    public void saveContact(View view) {

        String username_str = username.getText().toString();
        String email_str = email.getText().toString();

        if (username_str.isEmpty()) {
            username.setError("Empty field!");
            return;
        }

        if (email_str.isEmpty()) {
            email.setError("Empty field!");
            return;
        }

        if (!email_str.contains("@")) {
            email.setError("Must be an email address!");
            return;
        }

        if (!contact_list.isUsernameAvailable(username_str)) {
            username.setError("Username already taken!");
            return;
        }

        Contact contact = new Contact(username_str, email_str, null);

        if (contact_list_controller.addContact(contact, context)) {
            showToast("Contact added");
            finish();
        } else {
            showToast("Failed to add contact");
        }

    }
}
