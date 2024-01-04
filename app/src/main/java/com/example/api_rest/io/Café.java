package com.example.api_rest.io;

import com.google.gson.annotations.SerializedName;
public class Café {

    private String title;
    private String description;
    private String ingredients;
    private String link;

    @SerializedName("image_url")
    private String imageUrl;

    // Constructor, getters y setters...

    public Café(String title, String description, String ingredients, String link, String imageUrl) {
        this.title = title;
        this.description = description;
        this.ingredients = ingredients;
        this.link = link;
        this.imageUrl = imageUrl;
    }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public String getIngredients() { return ingredients; }

    public void setIngredients(String ingredients) { this.ingredients = ingredients; }

    public String getLink() { return link; }

    public void setLink(String link) { this.link = link; }

    // Método adicional para obtener la URL de la imagen
    public String getImageUrl() { return imageUrl; }

    // Método adicional para establecer la URL de la imagen
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }


}
