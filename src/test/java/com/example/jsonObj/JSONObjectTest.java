package com.example.jsonObj;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.internal.StringUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.lang.model.type.UnknownTypeException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * kbsys
 *
 * @param model
 * @param menuModel
 * @param authId
 * @param request
 * @return
 */
public class JSONObjectTest {
    JSONArray jsonArray = new JSONArray();
    static JSONObject duplJSONObj=null;
    static int index = 0;
    static List<String> list = new ArrayList<>();
    @BeforeEach
    public void before(){
        jsonArray.put("test");
        jsonArray.put("test1");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("jsonobj","t111");
        jsonArray.put(jsonObject);
        jsonArray.put("test2");
        jsonArray.put(Arrays.asList("test1","test2"));

        initDupJSONArr();


    }

    private static void initDupJSONArr() {
        // 최상위 JSONObject 생성
        duplJSONObj = new JSONObject();

        // JSONArray 생성
        JSONArray jsonArray = new JSONArray();

        // 첫 번째 JSONObject 생성
        JSONObject obj1 = new JSONObject();
        obj1.put("name", "John Doe");
        obj1.put("age", 30);
        obj1.put("isStudent", false);

        // 두 번째 JSONObject 생성
        JSONObject obj2 = new JSONObject();
        obj2.put("name", "Jane Smith");
        obj2.put("age", 25);
        obj2.put("isStudent", true);

        // 첫 번째 JSONObject를 JSONArray에 추가
        jsonArray.put(obj1);

        // 두 번째 JSONObject를 JSONArray에 추가
        jsonArray.put(obj2);

        // JSONArray를 최상위 JSONObject에 추가
        duplJSONObj.put("people", jsonArray);

        // 추가적인 데이터 타입의 필드들을 최상위 JSONObject에 추가
        duplJSONObj.put("isAvailable", true);
        duplJSONObj.put("count", 42);
        duplJSONObj.put("average", 3.14);
        duplJSONObj.put("nullValue", JSONObject.NULL);

        // JSONArray를 최상위 JSONObject에 추가
        JSONArray additionalArray = new JSONArray();
        additionalArray.put("item1");
        additionalArray.put("item2");
        duplJSONObj.put("additionalItems", additionalArray);
    }

    @Test
    public void strTest(){
        String a = "aaa";
        String b= a;
        b="aaa-";
        Assertions.assertEquals(a,"aaa-");
    }
    @Test
    public void jsonForEachTest(){
        jsonArray.forEach(item->{
            if(item instanceof  String){
                item="modified";
            }
        });
        jsonArray.forEach(item->{
            Assertions.assertEquals((String)item,"modified");
        });
    }
    @Test
    public void jsonForOfTest(){
        for (Object item : jsonArray) {
            if(item instanceof  String){
                item="modified";
            }
        }
        for (Object item : jsonArray) {
            if(item instanceof  String){
                Assertions.assertEquals((String)item,"modified");
            }
        }
    }
    @Test
    public void jsonForEachTest2(){
        JSONArray tmpJsonArr = new JSONArray();

        jsonArray.forEach(item->{
            if(item instanceof  String){
                tmpJsonArr.put("modified");
            }
            else if(item instanceof JSONObject){
                JSONObject jsonObj = (JSONObject)item;
                jsonObj.keySet().forEach(childParam->{
                    jsonObj.put(childParam,"modified");
                });
                tmpJsonArr.put(jsonObj);
            }
            else if(item instanceof  JSONArray){
                JSONArray tmpJsonArr_ = (JSONArray) item;
                tmpJsonArr_.forEach(item_->{
                    list.add("modified");
                });
                tmpJsonArr.put(list);
                list = new ArrayList<>();
            }
        });
        jsonArray=tmpJsonArr;
        index=0;
        jsonArray.forEach(item->{
            Assertions.assertEquals((String)item,"Test"+index++);
        });
    }
    @Test
    public void duplJSONObjTest() throws IOException {
        duplJSONObj.keySet().forEach(item->{
            if(duplJSONObj.get(item) instanceof  JSONArray){
                modifiedJSONArray(duplJSONObj.getJSONArray(item));
            }else if(duplJSONObj.get(item) instanceof  JSONObject){
                modifiedJSONObject(duplJSONObj.getJSONObject(item));
            }else if(duplJSONObj.get(item) instanceof String){
                duplJSONObj.put(item,"modified");
            }else{
            }
        });
    }
    private void modifiedJSONObject(JSONObject jsonObject) {
        jsonObject.keySet().forEach(item->{
            if(duplJSONObj.get(item) instanceof  JSONArray){
                modifiedJSONArray(duplJSONObj.getJSONArray(item));
            }else if(duplJSONObj.get(item) instanceof  JSONObject){
                modifiedJSONObject(duplJSONObj.getJSONObject(item));
            }else if(duplJSONObj.get(item) instanceof String){
                duplJSONObj.put(item,"modified");
            }else{
            }
        });
    }

    private void modifiedJSONArray(JSONArray jsonArr) {
        for (int i = 0; i < jsonArr.length(); i++) {
            if(jsonArr.get(i) instanceof  JSONArray){
                modifiedJSONArray((JSONArray)jsonArr.get(i));
            }else if(jsonArr.get(i) instanceof  JSONObject){
                modifiedJSONObject((JSONObject)jsonArr.get(i));
            }else if(jsonArr.get(i) instanceof String){
                jsonArr.put(i,"modified");
            }else{
            }
        }
        /*
        jsonArr.forEach(item->{
            if(item instanceof  JSONArray){
                modifiedJSONArray((JSONArray)item);
            }else if(item instanceof  JSONObject){
                modifiedJSONObject((JSONObject) item);
            }else if(item instanceof String){
                item="modified";
            }else{
            }
        });
         */

    }

}
