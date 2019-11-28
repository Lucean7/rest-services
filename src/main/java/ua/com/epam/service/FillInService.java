package ua.com.epam.service;

import com.jayway.jsonpath.JsonPath;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import ua.com.epam.core.rest.RestClient;
import ua.com.epam.entity.author.Author;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


import static ua.com.epam.config.URI.GET_ALL_AUTHORS_ARR;
import static ua.com.epam.config.URI.POST_AUTHOR_SINGLE_OBJ;
import static ua.com.epam.utils.JsonKeys.AUTHOR_ID;


public class FillInService {
    private static Logger log = Logger.getLogger(CleanUpService.class);

    private RestClient client;

    public FillInService(RestClient client) {
        this.client = client;
    }

    public void authorAdd(List<Author> authorList) {
        log.info("Start to get all authors...");

        client.get(GET_ALL_AUTHORS_ARR);

        List<Long> authorIds = getObjectIdsToAdd(AUTHOR_ID, client.getResponse().getBody());
        int size = authorIds.size();

        if (size == 0) {
            log.info(size + " authors !");
            log.info("Start to add authors...");
            authorList.forEach(author -> client.post(POST_AUTHOR_SINGLE_OBJ, author));
            return;
        }
        log.info("Author table is add!");
    }


    private List<Long> getObjectIdsToAdd(String keyName, String json) {
        JSONArray a = new JSONArray(json);

        if (a.length() == 0) return new ArrayList<>();

        return a.toList()
                .stream()
                .map(o -> Long.valueOf(JsonPath.read(o, "$." + keyName).toString()))
                .collect(Collectors.toList());
    }
}
