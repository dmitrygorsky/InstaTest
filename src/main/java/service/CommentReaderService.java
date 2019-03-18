package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class CommentReaderService extends BaseService {

    private static final String commentsFile = "src/main/resources/comments.txt";

    public List<String> putCommentsToList() {
        List<String> comments = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(commentsFile))) {
            comments = br.lines().collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(
                    "This should never happen", e);
        }
        return comments;
    }

    public String selectRandomComment(List<String> comments) {
        random = new Random();
        return comments.get(random.nextInt(comments.size()));
    }

}
