package de.fhro.inf.prg3.a13.tweets;

import de.fhro.inf.prg3.a13.model.Tweet;
import de.fhro.inf.prg3.a13.tweets.generators.OfflineTweetStreamGenerator;
import org.apache.commons.lang3.NotImplementedException;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Peter Kurfer
 */
public class TrumpTweetStats {

    public static Map<String, Long> calculateSourceAppStats(Stream<Tweet> tweetStream) {
        return tweetStream.collect(Collectors.groupingBy(Tweet::getSourceApp, Collectors.counting()));
    }

    public static Map<String, Set<Tweet>> calculateTweetsBySourceApp(Stream<Tweet> tweetStream) {
        /* TODO group the tweets by the `sourceApp`
         * collect the tweets in `Set`s for each source app */
        return tweetStream.collect(Collectors.groupingBy(Tweet::getSourceApp, Collectors.toSet()));
    }

    public static Map<String, Integer> calculateWordCount(Stream<Tweet> tweetStream, List<String> stopWords) {
        /* Remark: implement this method at last */
        /* TODO split the tweets, lower them, trim them, remove all words that are in the `stopWords`,
         * reduce the result to a Map<String, Integer> to count how often each word were in the tweets
         * optionally you could filter for all words that were used more than 10 times */
        return tweetStream
                .map(x -> Arrays.stream(x.getText().toLowerCase().split(" ")))
                .flatMap(a -> a)
                .filter(x -> !stopWords.contains(x))
                .reduce(new LinkedHashMap<String, Integer>(), (map, word) -> {
                    map.put(word, map.compute(word, (k, v) -> v == null ? 1 : v + 1));
                    return map;
                }, (m1, m2) -> m1);
        /* Filterung
                .entrySet()
                .stream()
                .filter(x -> x.getValue() > 10)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.::getValue));
                */
    }
}
