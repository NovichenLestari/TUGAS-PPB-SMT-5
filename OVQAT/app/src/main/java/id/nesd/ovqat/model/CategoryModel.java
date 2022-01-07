package id.nesd.ovqat.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CategoryModel {
    private String name;
    private String image;

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("image")
    public String getImage() {
        return image;
    }

    @JsonProperty("image")
    public void setImage(String image) {
        this.image = image;
    }
}
