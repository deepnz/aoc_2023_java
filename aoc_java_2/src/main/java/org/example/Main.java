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
        List<Integer> valList = new ArrayList<>();
        Pattern pattern = Pattern.compile("(\\d+)\\s+red| (\\d+)\\s+blue| (\\d+)\\s+green");
        int sum =  Files.lines(Path.of("aoc_day2_input.txt"))
                .map( line -> {
                    System.out.println(line);
                    gamenum.getAndIncrement();
                    String[] temp = line.split(":");
                    String[] sets = temp[1].split(";");
                    int valAssign = 0;
                    boolean flag = false;
                    for(String s: sets){
                        int totalRed =0, totalBlue=0, totalGreen =0;
                        Matcher matcher = pattern.matcher(s);

                        while(matcher.find() && !flag) {
                            if (matcher.group(1) != null) {
                                totalRed += Integer.parseInt(matcher.group(1));
                            }
                            if (matcher.group(2) != null) {
                                totalBlue += Integer.parseInt(matcher.group(2));
                            }
                            if (matcher.group(3) != null) {
                                totalGreen += Integer.parseInt(matcher.group(3));
                            }

                            if ( totalRed > red ||  totalBlue > blue || totalGreen > green) {
                                flag = true;
                            }
                            System.out.println(String.format("TR: %d  TB: %d TG: %d - %s flag", totalRed, totalBlue, totalGreen, flag));
                        }
                    }

                    if(!flag){
                        valAssign=gamenum.get();
                    }

                    score.getAndAdd(valAssign);
                    System.out.println("GAME: " +gamenum);
                    System.out.println("Value: "+valAssign);
                    System.out.println("Total Value thus far: "+score);
                    return valAssign;
                }).mapToInt(Integer::intValue).sum();

        System.out.println(sum);
    }

    }

