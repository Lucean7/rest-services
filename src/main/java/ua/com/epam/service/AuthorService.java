package ua.com.epam.service;

import ua.com.epam.core.rest.RestClient;
import ua.com.epam.entity.Fault;
import ua.com.epam.entity.Response;
import ua.com.epam.entity.author.Author;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

import static ua.com.epam.config.URI.*;

public class AuthorService {
    private RestClient client;
    private Map<String, String> mapParams = new HashMap<>();

    public AuthorService(RestClient client) {
        this.client = client;
    }

    public Response getListAuthorsByParam(
            String orderType,
            String number,
            String pagination,
            String size,
            String sortBy){
        Map<String, String> mapParam = new HashMap<>();
        mapParam.put("orderType",orderType);
        mapParam.put("page",number);
        mapParam.put("pagination",pagination);
        mapParam.put("size",size);
        mapParam.put("sortBy",sortBy);
        return getListAuthorsByParam(mapParam);
    }

    public Response getListAuthorsByParam(Map<String,String>mapParam) {
        client.get(GET_ALL_AUTHORS_ARR + mapToParams(mapParam));
        return client.getResponse();
    }

    public Response getAuthorByNameAndSurname(String... params)  {
        String encodedParams = encode(String.join(" ", params));
        String paramNameAndSurname = "?query=" + encodedParams;
        client.get(SEARCH_FOR_EXISTED_AUTHORS_ARR + paramNameAndSurname);
        return client.getResponse();
    }

    public Response postAuthorBySameId(Author randomAuthor){
        client.post(POST_AUTHOR_SINGLE_OBJ, randomAuthor);
        return client.getResponse();
    }

    public Response deleteAuthorById(Author randomAuthor){
        client.delete(String.format(DELETE_AUTHOR_SINGLE_OBJ, randomAuthor.getAuthorId()));
        return client.getResponse();
    }

    public Response getAuthorById(Author randomAuthor) {
        client.get(String.format(GET_AUTHOR_SINGLE_OBJ,randomAuthor.getAuthorId()));
        return client.getResponse();
    }

    public Response putAuthorIsExist(Author randomAuthor) {
        client.put(String.format(PUT_AUTHOR_SINGLE_OBJ, randomAuthor.getAuthorId()), randomAuthor);
        return client.getResponse();
    }

    private String mapToParams(Map<String, String> mapParam) {
        StringJoiner sj = new StringJoiner("&");
        for(String key : mapParam.keySet()) {
            String value = mapParam.get(key);
            String param = key + "=" + encode(value);
            sj.add(param);
        }
        return "?" + sj.toString();
    }

    private static String encode(String value){
        try {
            return URLEncoder.encode(value,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return value;
    }

}
