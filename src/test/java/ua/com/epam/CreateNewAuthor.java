package ua.com.epam;

import org.testng.annotations.Test;
import ua.com.epam.entity.Response;
import ua.com.epam.entity.author.Author;
import ua.com.epam.service.EvaluatedAssert;

public class CreateNewAuthor extends BaseTest {
    private Author randomAuthor = testData.authors().getRandomOne();

    @Test
    public void postNewAuthor() {
        authorService.deleteAuthorById(randomAuthor);
        Response response = authorService.postAuthorBySameId(randomAuthor);

        evaluatedAssert = new EvaluatedAssert(randomAuthor);
        evaluatedAssert.isResponseStatusCodeAndBodyTrue(response, 201);
    }

    @Test
    public void postExistAuthorBySameId() {
        Response response = authorService.postAuthorBySameId(randomAuthor);

        evaluatedAssert = new EvaluatedAssert(randomAuthor);
        evaluatedAssert.isResponseStatusCodeAndBodyFalse(response,409);
    }
}
