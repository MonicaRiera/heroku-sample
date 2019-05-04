package tech.bts.herokusample.api;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(path = "/api")
public class SampleApi {

    private MongoCollection<Document> words;
    private MongoCollection<Document> ranges;
    private MongoCollection<Document> nameCodes;

    @Autowired
    public SampleApi(@Value("${mongoUri}") String mongoUri) {

        //final MongoClient mongoClient = MongoClients.create("mongodb+srv://monica:<Mr3141592021213>@cluster0-flz04.mongodb.net/test?retryWrites=true");
        final MongoClient mongoClient = MongoClients.create(mongoUri);
        final MongoDatabase database = mongoClient.getDatabase("test");
        this.words = database.getCollection("words");
        this.ranges = database.getCollection("ranges");
        this.nameCodes = database.getCollection("nameCodes");
    }

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello from sample app";
    }

    // insert?word=dog
    @GetMapping("/insert")
    public String insertWord(@RequestParam String word) {

        final Document doc = new Document()
                .append("word", word)
                .append("date", new Date());

        words.insertOne(doc);

        return "Word inserted: " + word;
    }

    // insert?word=dog
    @GetMapping("/list")
    public List<String> listWords() {

        final List<String> result = new ArrayList<>();

        for (Document doc : words.find()) {
            result.add(doc.getString("word"));
        }

        return result;
    }

    // range?num=20
    @GetMapping("/range")
    public List<Integer> range(@RequestParam int num) {
        final List<Integer> result = new ArrayList<>();

        for (int i = 1; i <= num; i++) {
            result.add(i);
        }

        final Document document = new Document().append("range", result);
        ranges.insertOne(document);
        return result;
    }

    @GetMapping("/namecode")
    public String nameCode(@RequestParam String name) {
        String result = "";
        char[] chars = name.toCharArray();
        for (char letter : chars) {
            int value = Character.getNumericValue(letter);
            result += value;
        }

        final Document document = new Document().append("name", name).append("code", result);
        nameCodes.insertOne(document);
        return "Your name code is " + result;
    }

    @GetMapping("/codes")
    public Map<String, String> listCodes() {

        final Map<String, String> result = new HashMap<>();

        for (Document doc : nameCodes.find()) {
            result.put(doc.getString("name"), doc.getString("code"));
        }

        return result;
    }
}
