package com.nadantas.courseplatform.shared.enums;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

@JsonDeserialize(using = Category.CategoryDeserializer.class)
public enum Category {
    FRONTEND("frontend"),
    BACKEND("backend"),
    FULLSTACK("fullstack"),
    MOBILE("mobile"),
    DATA_SCIENCE("data_science"),
    MACHINE_LEARNING("machine_learning"),
    DEVOPS("devops"),
    CLOUD("cloud"),
    CYBER_SECURITY("cyber_security");

    private final String type;

    Category(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static Category fromType(String type) {
        for (Category category : Category.values()) {
            if (category.getType().equalsIgnoreCase(type)) {
                return category;
            }
        }
        throw new IllegalArgumentException("Unknown category type: " + type);
    }

    // Custom deserializer for Category
    public static class CategoryDeserializer extends JsonDeserializer<Category> {
        @Override
        public Category deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            String type = p.getText();
            return Category.fromType(type);
        }
    }

    public static String getPossibleCategories() {
        return Arrays.stream(Category.values())
                .map(Category::getType)
                .collect(Collectors.joining(", "));
    }
}
