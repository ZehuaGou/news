package com.news.web.converter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.SocketHandler;

/**
 * @author Paul Lee
 */
public class ExerciseMain {

    private static List<? extends Exercise> arr = new ArrayList<>();

    public static void main(String[] args) {
        Integer[] arr = {1, 2, 3};
        List list = Arrays.asList(arr);
    }
}
