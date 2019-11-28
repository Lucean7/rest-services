package ua.com.epam.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.checkerframework.checker.units.qual.A;
import org.testng.asserts.SoftAssert;
import ua.com.epam.config.AssertMessage;
import ua.com.epam.entity.Fault;
import ua.com.epam.entity.Response;
import ua.com.epam.entity.author.Author;
import ua.com.epam.utils.helpers.LocalDateAdapter;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EvaluatedAssert implements AssertMessage {
    private SoftAssert softAssert;
    private Type listType;
    private Type type;
    private Gson g;
    private Author randomAuthor;
    private List<Author> authorsList;

    public EvaluatedAssert(List<Author> authorsList) {
        this();
        this.authorsList = authorsList;
    }

    public EvaluatedAssert(Author randomAuthor) {
        this();
        this.randomAuthor = randomAuthor;

    }

    private EvaluatedAssert() {
        this.softAssert = new SoftAssert();
        this.g = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
        this.listType = new TypeToken<ArrayList<Author>>() {
        }.getType();
        this.type = Author.class;
    }

    public void isResponseStatusCodeAndBodyTrue(Response response, int statusCode) {
        Author author = g.fromJson(response.getBody(), type);
        softAssert.assertEquals(response.getStatusCode(), statusCode, FALSE_STARUSCODE);
        softAssert.assertEquals(author, randomAuthor, FALSE_BODY);
        softAssert.assertAll();
    }

    public void isResponseStatusCodeAndBodyFalse(Response response, int statusCode) {
        softAssert.assertEquals(response.getStatusCode(), statusCode, FALSE_STARUSCODE);
        softAssert.assertAll();
    }

    public void isListResponseStatusCodeAndBodyTrue(Response response, int size, int statusCode) {
        List<Author> authors = g.fromJson(response.getBody(), listType);
        softAssert.assertEquals(response.getStatusCode(), statusCode, FALSE_STARUSCODE);
        softAssert.assertEquals(authors.size(), size, FALSE_SIZE);
        softAssert.assertAll();
    }

    public void checkGetAllByASC(Response response) {
        int count = 1;
        List<Author> authors = g.fromJson(response.getBody(), new TypeToken<ArrayList<Author>>() {
        }.getType());
        for (int i = 0; i < authors.size() - 1; i++) {
            if (authors.get(i).getAuthorId() < authors.get(i + 1).getAuthorId()) {
                count++;
            }
        }
        softAssert.assertEquals(authors.size(), count);
        softAssert.assertAll();
    }

    public void checkSearchResponseIsFalse(Response response) {
        softAssert.assertEquals(response.getStatusCode(), 200, FALSE_STARUSCODE);
        softAssert.assertEquals(response.getBody(), "[]", FALSE_BODY);
        softAssert.assertAll();
    }

    public void checkDeleteResponseIsTrue(Response response) {
        softAssert.assertEquals(response.getStatusCode(), 204, FALSE_STARUSCODE);
        softAssert.assertAll();
    }

}
