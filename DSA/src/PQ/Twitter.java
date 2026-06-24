package PQ;

import java.util.*;

public class Twitter {


        private int count;
        private Map<Integer, LinkedList<int[]>> tweetMap; //maintains what a user posted
        //list is to keep track of the most recent one (negative count -- lesser , more recent)
        private Map<Integer, Set<Integer>> followMap; //who follows who
        //set because easy to add and remove followee

        public Twitter() {
            this.count = 0;
            this.tweetMap = new HashMap<>();
            this.followMap = new HashMap<>();
        }

        public void postTweet(int userId, int tweetId) {
            tweetMap.computeIfAbsent(userId, k -> new LinkedList<>()).add(new int[]{count, tweetId});
            count--;
            if(tweetMap.get(userId).size()>10){tweetMap.get(userId).removeFirst();}
        }

        public List<Integer> getNewsFeed(int userId) {
            List<Integer> res = new ArrayList<>();

            // Max-Heap: Since your count goes down (-1, -2, -3), the smaller value is actually newer.
            // We want the smallest 'count' value at the top of our heap.
            PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));

            // 1. Ensure the user is following themselves so their own tweets show up
            followMap.computeIfAbsent(userId, k -> new HashSet<>()).add(userId);

            // 2. Push the single most recent tweet of every followee into the heap
            for (int followeeId : followMap.get(userId)) {
                if (tweetMap.containsKey(followeeId) && !tweetMap.get(followeeId).isEmpty()) {
                    List<int[]> tweets = tweetMap.get(followeeId);
                    int lastIndex = tweets.size() - 1; // The end of the list has the newest tweet
                    int[] tweet = tweets.get(lastIndex);

                    // Format: { timestamp, tweetId, followeeId, nextIndexToLookAt }
                    maxHeap.offer(new int[]{tweet[0], tweet[1], followeeId, lastIndex - 1});
                }
            }

            // 3. Extract the top 10 tweets
            while (!maxHeap.isEmpty() && res.size() < 10) {
                int[] curr = maxHeap.poll();
                res.add(curr[1]); // Add tweetId to result

                int nextIndex = curr[3];
                int followeeId = curr[2];

                // If this user has more older tweets left, push the next one into the heap
                if (nextIndex >= 0) {
                    int[] nextTweet = tweetMap.get(followeeId).get(nextIndex);
                    maxHeap.offer(new int[]{nextTweet[0], nextTweet[1], followeeId, nextIndex - 1});
                }
            }

            return res;
        }

        public void follow(int followerId, int followeeId) {
            followMap.computeIfAbsent(followerId, k -> new HashSet<>()).add(followeeId);
        }

        public void unfollow(int followerId, int followeeId) {
            if(followMap.containsKey(followerId)){
                followMap.get(followerId).remove(followeeId);
            }

        }


}
