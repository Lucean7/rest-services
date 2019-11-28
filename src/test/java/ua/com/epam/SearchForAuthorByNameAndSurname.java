package ua.com.epam;

import org.testng.annotations.Test;
import ua.com.epam.entity.Response;
import ua.com.epam.entity.author.Author;
import ua.com.epam.service.EvaluatedAssert;

import java.util.List;

public class SearchForAuthorByNameAndSurname extends BaseTest {
    private List<Author> authorList = testData.authors().getDefaultAuthors();

    @Test
    public void SearchByExistNameAndSurname() {
        Response response = authorService.getAuthorByNameAndSurname("Marcelino", "Kreiger");

        evaluatedAssert = new EvaluatedAssert(authorList);
        evaluatedAssert.isListResponseStatusCodeAndBodyTrue(response,1, 200);
    }

    @Test
    public void SearchByNoExistNameAndSurname() {
        Response response = authorService.getAuthorByNameAndSurname("Anna", "Danter");

        evaluatedAssert = new EvaluatedAssert(authorList);
        evaluatedAssert.checkSearchResponseIsFalse(response);
    }
}
