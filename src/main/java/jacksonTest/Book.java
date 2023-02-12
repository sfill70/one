package jacksonTest;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.util.Date;

//анотация информация о классе
@JsonTypeInfo(use= JsonTypeInfo.Id.CLASS, include= JsonTypeInfo.As.PROPERTY, property="class")
//@JsonSubTypes(value = jacksonTest.Book.class)
class Book {
    //Меняет название поля при сеарилизации
    @JsonProperty("nameBook")
    public String title;
    public String author;
    public int pages;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public Date createdDate = new Date();

    /*@JsonProperty("nameBook")
    public String getQuotedTitle() {
        return "«" + title + "»";
    }*/

    @Override
    public String toString() {
        return "jacksonTest.Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", pages=" + pages +
                ", createdDate=" + createdDate +
                '}';
    }

    public static class Solution {
        public static  void main(String[] args) throws Exception {

            Book book = new Book();
            System.out.println(book.getClass());
            book.title = "Обитаемый остров";
            book.author = "Стругацкий А., Стругацкий Б.";
            book.pages = 413;

            Class<? extends Book> cl = book.getClass();
            System.out.println(cl == Book.class);
//            var boo = jacksonTest.Book.class

            //Сериализация Json
            ObjectMapper mapper = new ObjectMapper();
            //Настройка вывода
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            String jsonBook = mapper.writeValueAsString(book);
            System.out.println(jsonBook);
            //Десериализация Json
//            String jsonString = "{\"title\":\"Обитаемый остров\",\"author\":\"Стругацкий А., Стругацкий Б.\",\"pages\":413}";
//            jacksonTest.Book bookNew = new ObjectMapper().readValue(jsonString, jacksonTest.Book.class);
            Book bookNew = new ObjectMapper().readValue(jsonBook, Book.class);
            System.out.println(bookNew);

            //Сериализация Xlm
            ObjectMapper mapperXlm = new XmlMapper();
            mapperXlm.enable(SerializationFeature.INDENT_OUTPUT);
            String xmlBook = mapperXlm.writeValueAsString(book);
            System.out.println(xmlBook);

            //Десериализация Xlm
            Book bookXml =  mapperXlm.readValue(xmlBook, Book.class);
            System.out.println(bookXml.author);
            System.out.println(bookXml.title + "!!!!!!!!!!!!!!!!!!");
            System.out.println(bookXml);

            //Сериализация YAML
            ObjectMapper mapperYAML = new YAMLMapper();
            mapperYAML.enable(SerializationFeature.INDENT_OUTPUT);
            String yamlBook = mapperYAML.writeValueAsString(book);
            System.out.println(yamlBook);

            //десериализация YAML
            Book bookYAML = mapperYAML.readValue(yamlBook, Book.class);
            System.out.println(bookYAML);

        }
    }
}