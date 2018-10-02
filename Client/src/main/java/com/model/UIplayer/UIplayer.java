package com.model.UIplayer;

import javafx.beans.property.SimpleStringProperty;

public class UIplayer {
    private final SimpleStringProperty username;
    private final SimpleStringProperty status;

    public UIplayer(String username, String status){
        this.username = new SimpleStringProperty(username);
        this.status = new SimpleStringProperty(status);
    }

    public String getUsername() {
        return username.get();
    }

    public SimpleStringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getStatus() {
        return status.get();
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }


}

