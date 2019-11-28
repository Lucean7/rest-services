package ua.com.epam;

import org.testng.annotations.Test;
import ua.com.epam.entity.Response;
import ua.com.epam.entity.author.Author;
import ua.com.epam.service.EvaluatedAssert;

import java.io.UnsupportedEncodingException;
import java.util.List;


public class GetAllAuthors extends BaseTest{
    private List<Author> authorList = testData.authors().getDefaultAuthors();

    @Test
    public void getAllAuthors() {
        Response response = authorService.getListAuthorsByParam("asc","1","true","8","authorId");

        evaluatedAssert = new EvaluatedAssert(authorList);
        evaluatedAssert.isListResponseStatusCodeAndBodyTrue(response,8, 200);
        evaluatedAssert.checkGetAllByASC(response);
    }
    @Test
    public void getAllAuthorsByFalseParam() throws UnsupportedEncodingException {
        Response response = authorService.getListAuthorsByParam("asc","1","true","8","authorI");

        evaluatedAssert = new EvaluatedAssert(authorList);
        evaluatedAssert.isResponseStatusCodeAndBodyFalse(response,400);
    }
}
