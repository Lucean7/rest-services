package ua.com.epam;

import ua.com.epam.entity.Response;
import org.testng.annotations.Test;
import ua.com.epam.entity.author.Author;
import ua.com.epam.service.EvaluatedAssert;

public class GetAuthorObjectByAuthorId extends BaseTest{
    private Author randomAuthor = testData.authors().getRandomOne();

    @Test
    public void getAuthorByExistId(){
        Response response = authorService.getAuthorById(randomAuthor);

        evaluatedAssert = new EvaluatedAssert(randomAuthor);
        evaluatedAssert.isResponseStatusCodeAndBodyTrue(response, 200);
    }

    @Test
    public void getAuthorByNoExistId(){
        authorService.deleteAuthorById(randomAuthor);
        Response response = authorService.getAuthorById(randomAuthor);

        evaluatedAssert = new EvaluatedAssert(randomAuthor);
        evaluatedAssert.isResponseStatusCodeAndBodyFalse(response,404);
    }
}
