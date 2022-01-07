package id.nesd.ovqat.model.app;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.IOException;

import id.nesd.ovqat.model.CategoryModel;
import id.nesd.ovqat.model.FoodModel;
import id.nesd.ovqat.model.HistoryModel;
import id.nesd.ovqat.model.UserModel;

public class ConverterModel {
    public static String jsonEncode(Object obj) throws JsonProcessingException {
        return getObjectMapper().writeValueAsString(obj);
    }

    public static UserModel userFromJsonString(String json) throws IOException {
        return getObjectMapper().readerFor(UserModel.class).readValue(json);
    }

    public static String userToJsonString(UserModel obj) throws JsonProcessingException {
        return getObjectMapper().writerFor(UserModel.class).writeValueAsString(obj);
    }

    public static HistoryModel historyFromJsonString(String json) throws IOException {
        return getObjectMapper().readerFor(HistoryModel.class).readValue(json);
    }

    public static String historyToJsonString(HistoryModel obj) throws JsonProcessingException {
        return getObjectMapper().writerFor(HistoryModel.class).writeValueAsString(obj);
    }

    public static CategoryModel categoryFromJsonString(String json) throws IOException {
        return getObjectMapper().readerFor(CategoryModel.class).readValue(json);
    }

    public static String categoryToJsonString(CategoryModel obj) throws JsonProcessingException {
        return getObjectMapper().writerFor(CategoryModel.class).writeValueAsString(obj);
    }

    public static FoodModel foodFromJsonString(String json) throws IOException {
        return getObjectMapper().readerFor(FoodModel.class).readValue(json);
    }

    public static String foodToJsonString(FoodModel obj) throws JsonProcessingException {
        return getObjectMapper().writerFor(FoodModel.class).writeValueAsString(obj);
    }

    private static ObjectMapper mapper;

    private static ObjectMapper getObjectMapper() {
        if (mapper == null) instantiateMapper();
        return mapper;
    }

    private static void instantiateMapper() {
        ObjectMapper m = new ObjectMapper();
        m.findAndRegisterModules();
        SimpleModule module = new SimpleModule();
        m.registerModule(module);
        mapper = m;
    }
}