package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        int red = 12;
        int blue = 14;
        int green = 13;
        AtomicInteger gamenum = new AtomicInteger(0);
        AtomicInteger score = new AtomicInteger(0);

        Map<String, String> games = new HashMap<>();
        List<Integer> maxList = new ArrayList<>();
        Pattern pattern = Pattern.compile("(\\d+)\\s+red| (\\d+)\\s+blue| (\\d+)\\s+green");
        int sum =  Files.lines(Path.of("aoc_day2_input.txt"))
                .map( line -> {
                    System.out.println(line);
                    gamenum.getAndIncrement();
                    String[] temp = line.split(":");
                    String[] sets = temp[1].split(";");
                    int valAssign = 0;
                    boolean flag = false;
                    int maxRed, maxBlue, maxGreen;
                    maxRed=maxBlue=maxGreen=0;
                    int totalRed =0, totalBlue=0, totalGreen =0;

                    for(String s: sets){
                         totalRed =0; totalBlue=0; totalGreen =0;
                        Matcher matcher = pattern.matcher(s);

                        while(matcher.find()) {
                            if (matcher.group(1) != null) {
                                totalRed += Integer.parseInt(matcher.group(1));
                            }
                            if (matcher.group(2) != null) {
                                totalBlue += Integer.parseInt(matcher.group(2));
                            }
                            if (matcher.group(3) != null) {
                                totalGreen += Integer.parseInt(matcher.group(3));
                            }

                            if (totalRed > maxRed) {
                                maxRed = totalRed;
                            }
                            if (totalBlue > maxBlue) {
                                maxBlue = totalBlue;
                            }
                            if (totalGreen > maxGreen) {
                                maxGreen = totalGreen;
                            }

                            System.out.println(String.format("TR: %d  TB: %d TG: %d - %s flag", totalRed, totalBlue, totalGreen, flag));
                            System.out.println(String.format("MaxR: %d  MaxB: %d MaxG: %d", maxRed, maxBlue, maxGreen));

                        }
                    }
                    System.out.println(String.format("MaxR: %d  MaxB: %d MaxG: %d", maxRed, maxBlue, maxGreen));

                    maxList.add(maxRed*maxBlue*maxGreen);
                    if(!flag){
                        valAssign=gamenum.get();
                    }

                    score.getAndAdd(valAssign);
                    return valAssign;
                }).mapToInt(Integer::intValue).sum();
        int powerset = maxList.stream()
                .mapToInt(Integer::intValue).sum();
        System.out.println(sum);
        System.out.println(maxList);
        System.out.println(powerset);
    }

    }

