package com.company;

import java.util.*;

public class Main implements SongCache {

    Map<String, Integer> map;
    Main(){
        map = new HashMap<>();
    }
    public static void main(String[] args) {
	// write your code here
        System.out.println("Hello World!");
        Main test = new Main();
        test.recordSongPlays("ID-1", 3);
        test.recordSongPlays("ID-1", 1);
        test.recordSongPlays("ID-2", 2);
        test.recordSongPlays("ID-3", 5);
        test.recordSongPlays("ID-3", 5);
        test.recordSongPlays("ID-4", 20);
        test.recordSongPlays("ID-6", 9);

        System.out.println(test.getPlaysForSong("ID-1"));
        System.out.println(test.getPlaysForSong("ID-9"));

        System.out.println(test.getTopNSongsPlayed(5));
        System.out.println(test.getTopNSongsPlayed(0));
    }


    @Override
    public void recordSongPlays(String songId, int numPlays) {
        map.put(songId, map.getOrDefault(songId, 0) + numPlays);
    }

    @Override
    public int getPlaysForSong(String songId) {
        return map.containsKey(songId) ? map.get(songId) : -1;
    }

    @Override
    public List<String> getTopNSongsPlayed(int n) {
        PriorityQueue<String> heap = new PriorityQueue<String>((w1, w2) -> map.get(w1).equals(map.get(w2)) ? w2.compareTo(w1) : map.get(w1) - map.get(w2));
        List<String> topNSongs = new ArrayList<>();
        if(n == 0) return topNSongs;
        for(String song: map.keySet()) {
            heap.offer(song);
            if(heap.size() > n) heap.poll();
        }

        while(!heap.isEmpty()) {
            topNSongs.add(heap.poll());
        }
        Collections.reverse(topNSongs);
        return topNSongs;
    }
}
