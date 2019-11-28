package ua.com.epam;

import org.testng.annotations.Test;
import ua.com.epam.entity.Response;
import ua.com.epam.entity.author.Author;
import ua.com.epam.service.EvaluatedAssert;

public class DeleteExistedAuthor extends BaseTest{
    private Author randomAuthor = testData.authors().getRandomOne();

    @Test
    public void deleteExistAuthor(){
        Response response = authorService.deleteAuthorById(randomAuthor);

        evaluatedAssert = new EvaluatedAssert(randomAuthor);
        evaluatedAssert.checkDeleteResponseIsTrue(response);
    }

    @Test
    public void deleteNoExistAuthor(){
        randomAuthor.setAuthorId(-12L);
        Response responseDelete = authorService.deleteAuthorById(randomAuthor);

        evaluatedAssert = new EvaluatedAssert(randomAuthor);
        new EvaluatedAssert(randomAuthor).isResponseStatusCodeAndBodyFalse(responseDelete,404);
    }

}
