package org.example;

import java.io.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {
    static Map<String, String> values = Map.of(
            "one", "o1e",
            "two", "t2o",
            "three", "thr3e",
            "four", "f4ur",
            "five", "fi5e",
            "six", "s6x",
            "seven", "s7ven",
            "eight", "ei8ht",
            "nine", "ni9e"
    );
    public static void main(String[] args) throws IOException {
        var list = new ArrayList<Integer>();

        BufferedReader in = new BufferedReader(new FileReader("aoc_day1_input.txt"));

        Pattern patt = Pattern.compile("\\d");
        List<Integer> nums = new ArrayList<>();
        String str = "";
        while((str = in.readLine())!=null) {
            for (Map.Entry<String, String> entry: values.entrySet()){
                str = str.replaceAll(entry.getKey(), entry.getValue());
            }
            Matcher matcher = patt.matcher(str);
            String temp = "";
            while (matcher.find()) {
                String g = matcher.group();
                temp = temp.concat(g);
            }
            Integer i = Integer.valueOf(String.valueOf(temp.charAt(0)).concat(String.valueOf(temp.charAt(temp.length() - 1))));
            nums.add(i);
        }
        int sum = 0;
        for(Integer n: nums) {
            sum +=n;
        }
        System.out.println(sum);

    }
}