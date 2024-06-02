package org.kira.automation.framework;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.kira.automation.utils.JsonParserUtil;
import org.testng.annotations.Test;

public class JsonParserUtilTest {

  @Test
  void testReadJsonFile() {
    Gson gsonMock = mock(Gson.class);
    String jsonContent =
      """
      {
      "name":%s,
      "age":%d
      }
      """.formatted("Kira", 31);

    Person expectedObject = new Person("Kira", 31);

    when(gsonMock.fromJson(jsonContent, Person.class)).thenReturn(expectedObject);

    Person actualObject = JsonParserUtil.readJsonFile(jsonContent, Person.class);

    assertEquals(expectedObject, actualObject);
  }

  @AllArgsConstructor
  @Data
  @ToString
  static class Person {

    private String name;
    private int age;
  }
}
