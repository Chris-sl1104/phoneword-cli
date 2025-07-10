package com.example.phonewords;

import java.util.*;

public class PhoneWordEncoder {
    private final Map<String, List<String>> digitSeqToWords;

    public PhoneWordEncoder(Map<String, List<String>> digitSeqToWords) {
        this.digitSeqToWords = digitSeqToWords;
    }

    public List<String> encodeNumber(String digits) {
        List<String> results = new ArrayList<>();
        Deque<String> stack = new ArrayDeque<>();
        Map<MemoKey, List<List<String>>> memo = new HashMap<>();
        backtrack(digits, 0, false, stack, results, memo);
        return results;
    }

    private void backtrack(String digits, int idx, boolean prevWasDigit,
                           Deque<String> current, List<String> results,
                           Map<MemoKey, List<List<String>>> memo) {
        if (idx == digits.length()) {
            results.add(String.join("-", current));
            return;
        }
        MemoKey key = new MemoKey(idx, prevWasDigit);
        if (memo.containsKey(key)) {
            for (List<String> suffix : memo.get(key)) {
                List<String> combined = new ArrayList<>(current);
                combined.addAll(suffix);
                results.add(String.join("-", combined));
            }
            return;
        }
        int origSize = results.size();

        for (int end = idx + 1; end <= digits.length(); end++) {
            String slice = digits.substring(idx, end);
            List<String> words = digitSeqToWords.get(slice);
            if (words == null) continue;
            for (String w : words) {
                current.addLast(w);
                backtrack(digits, end, false, current, results, memo);
                current.removeLast();
            }
        }
        if (!prevWasDigit) {
            current.addLast(String.valueOf(digits.charAt(idx)));
            backtrack(digits, idx + 1, true, current, results, memo);
            current.removeLast();
        }
        if (results.size() > origSize) {
            List<List<String>> suffixes = new ArrayList<>();
            for (int i = origSize; i < results.size(); i++) {
                String full = results.get(i);
                String[] tokens = full.split("-");
                suffixes.add(Arrays.asList(tokens).subList(current.size(), tokens.length));
            }
            memo.put(key, suffixes);
        }
    }

    private static final class MemoKey {
        final int pos;
        final boolean prevWasDigit;
        MemoKey(int pos, boolean prevWasDigit) {
            this.pos = pos;
            this.prevWasDigit = prevWasDigit;
        }
        @Override
        public boolean equals(Object o) {
            return o instanceof MemoKey m && m.pos == pos && m.prevWasDigit == prevWasDigit;
        }
        @Override
        public int hashCode() {
            return Objects.hash(pos, prevWasDigit);
        }
    }
}
