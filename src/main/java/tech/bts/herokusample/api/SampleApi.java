package tech.bts.herokusample.api;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class SampleApi {

    private MongoCollection<Document> words;
    private MongoCollection<Document> ranges;

    @Autowired
    public SampleApi(@Value("${mongoUri}") String mongoUri) {

        //final MongoClient mongoClient = MongoClients.create("mongodb+srv://monica:<Mr3141592021213>@cluster0-flz04.mongodb.net/test?retryWrites=true");
        final MongoClient mongoClient = MongoClients.create(mongoUri);
        final MongoDatabase database = mongoClient.getDatabase("test");
        this.words = database.getCollection("words");
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

        return result;
    }
}
