package ua.com.epam;

import org.testng.annotations.Test;
import ua.com.epam.entity.Response;
import ua.com.epam.entity.author.Author;
import ua.com.epam.service.EvaluatedAssert;

public class UpdateExistedAuthor extends BaseTest {
    private Author randomAuthor = testData.authors().getRandomOne();

    @Test
    public void putExistedAuthor() {
        randomAuthor.getAuthorName().setFirst("Anna");
        Response response = authorService.putAuthorIsExist(randomAuthor);

        evaluatedAssert = new EvaluatedAssert(randomAuthor);
        evaluatedAssert.isResponseStatusCodeAndBodyTrue(response, 200);
    }

    @Test
    public void putNoExistedAuthor() {
        randomAuthor.setAuthorId(-12L);
        Response response = authorService.putAuthorIsExist(randomAuthor);

        evaluatedAssert = new EvaluatedAssert(randomAuthor);
        evaluatedAssert.isResponseStatusCodeAndBodyFalse(response,400);
    }
}
