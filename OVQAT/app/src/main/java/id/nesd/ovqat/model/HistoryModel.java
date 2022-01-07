package id.nesd.ovqat.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HistoryModel {
    private String user;
    private String food;

    @JsonProperty("user")
    public String getUser() {
        return user;
    }

    @JsonProperty("user")
    public void setUser(String user) {
        this.user = user;
    }

    @JsonProperty("food")
    public String getFood() {
        return food;
    }

    @JsonProperty("food")
    public void setFood(String food) {
        this.food = food;
    }
}
