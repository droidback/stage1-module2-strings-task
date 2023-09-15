package com.epam.mjc;

import java.util.*;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source     source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        int size = delimiters.size();
        Iterator<String> iterator = delimiters.iterator();
        for (int i = 0; i < size; i++) {
            source = source.replaceAll(iterator.next(), "+");
        }
        String[] tokens = source.replaceAll("[+]+", " ").trim().split(" ");
        List<String> result = new ArrayList<>();
        Collections.addAll(result, tokens);

        return result;
    }
}
