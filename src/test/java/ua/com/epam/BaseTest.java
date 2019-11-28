package ua.com.epam;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ua.com.epam.core.rest.RestClient;
import ua.com.epam.service.AuthorService;
import ua.com.epam.service.CleanUpService;
import ua.com.epam.service.EvaluatedAssert;
import ua.com.epam.service.FillInService;
import ua.com.epam.utils.DataFactory;
import ua.com.epam.utils.helpers.LocalDateAdapter;

import java.time.LocalDate;

public class BaseTest {
    //to parse JSON String to needed model (with correct date parsing possibility)
    protected Gson g = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();

    protected RestClient client = new RestClient();
    protected DataFactory testData = new DataFactory();
    protected CleanUpService clean = new CleanUpService(client);
    protected FillInService addAuthors = new FillInService(client);
    protected AuthorService authorService = new AuthorService(client);
    protected EvaluatedAssert evaluatedAssert;

    //don't delete this!!!
    @BeforeMethod
    public void reinitialize() {
        client = new RestClient();
        testData = new DataFactory();
        clean = new CleanUpService(client);
        addAuthors = new FillInService(client);
        authorService = new AuthorService(client);
    }

    @BeforeMethod
    public void sendAuthorToService(){
        addAuthors.authorAdd(testData.authors().getDefaultAuthors());
    }

    @AfterMethod
    public void cleanUp() {
        clean.authors();
    }

}
