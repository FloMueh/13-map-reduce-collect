package de.fhro.inf.prg3.a13.tweets.generators;

import com.google.gson.Gson;
import de.fhro.inf.prg3.a13.model.Tweet;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

public class OfflineTweetStreamGenerator implements TweetStreamGenerator{
    private final InputStream trumpStream;

    public OfflineTweetStreamGenerator() {
        trumpStream = getClass().getResourceAsStream("/trump_tweets.json");
    }

    @Override
    public Stream<Tweet> getTweetStream() {
        return Arrays.stream(new Gson().fromJson(new InputStreamReader(trumpStream), Tweet[].class));
    }
}
