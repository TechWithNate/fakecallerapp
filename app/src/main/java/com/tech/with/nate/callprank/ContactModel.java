package com.tech.with.nate.callprank;

public class ContactModel {

    private String name;
    private String contact;
    private String label;
    private String contactId;

    public ContactModel(String name, String contact, String contactId) {
        this.name = name;
        this.contact = contact;
        this.label = label;
        this.contactId = contactId;
    }

    public ContactModel() {
    }

    public ContactModel(String name, String contact) {
        this.name = name;
        this.contact = contact;
    }

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }



    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "ContactModel{" +
                "name='" + name + '\'' +
                ", contact='" + contact + '\'' +
                ", label='" + label + '\'' +
                ", contactId=" + contactId +
                '}';
    }
}
