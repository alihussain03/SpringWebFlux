package com.reactor.practice.FluxAndMono;

import java.util.Arrays;
import java.util.List;

public class FlatMapExample {

  public static void main(String[] args) {
    func1WithOutFlatMap();
    func1();
    System.out.println("\n");
    func2();
  }

  static void func1WithOutFlatMap() {
    List<List<String>> names = List.of(List.of("ely", "mark"), List.of("Sara"), List.of("Noah", "Ava"));
    names.stream().map(List::stream).forEach(System.out::println);
  }

  // First: List.of("Ali","Hussain").stream() → Stream("ely","mark")
  //Second: List.of("Sara").stream() → Stream("Sara")
  //Third: List.of("Noah","Ava").stream() → Stream("Noah","Ava")
  static void func1() {
    List<List<String>> names = List.of(List.of("ely", "mark"), List.of("Sara"), List.of("Noah", "Ava"));
    List<String> flat = names.stream().flatMap(List::stream).toList();
    System.out.println(flat);
  }

  static void func2() {
    List<String> sentences = List.of("hello world", "java streams rock");
    List<String> words = sentences.stream().flatMap(s -> Arrays.stream(s.split("\\s+"))).toList();
    System.out.println(words);
  }

}
