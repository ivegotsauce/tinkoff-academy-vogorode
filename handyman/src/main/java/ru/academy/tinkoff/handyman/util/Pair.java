package ru.academy.tinkoff.handyman.util;

public record Pair<K, V>(K first, V second) {

    public static <K, V> Pair<K, V> of(K first, V second) {
        return new Pair<>(first, second);
    }
}