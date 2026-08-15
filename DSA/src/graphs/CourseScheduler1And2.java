package graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CourseScheduler1And2 {


        /**
         * Solves both Course Schedule I and II using Kahn's Algorithm (BFS).
         *
         * Course Schedule 1 asks: Can we finish? (Returns true/false)
         * Course Schedule 2 asks: What is the order? (Returns int[] array)
         *
         * @param numCourses Total number of courses (labeled 0 to numCourses - 1)
         * @param prerequisites Array of pairs [course, prerequisite]
         * @return int[] array of the valid course order, or an empty array if a cycle exists.
         */
        public int[] findOrder(int numCourses, int[][] prerequisites) {

            // ==========================================
            // STEP 1: BUILD THE GRAPH & PADLOCK COUNTER
            // ==========================================

            // Adjacency List: maps a course to all the courses it unlocks.
            List<List<Integer>> adj = new ArrayList<>();
            for (int i = 0; i < numCourses; i++) {
                adj.add(new ArrayList<>()); // Pre-populate empty lists to avoid NullPointerExceptions
            }

            // In-Degree Array: tracks how many prerequisites (padlocks) a course has.
            int[] inDegree = new int[numCourses];

            for (int[] pre : prerequisites) {
                int course = pre[0];
                int prereq = pre[1];

                // Draw the arrow: prereq -> course
                adj.get(prereq).add(course);

                // Add a padlock to the target course
                inDegree[course]++;
            }

            // ==========================================
            // STEP 2: FIND THE STARTING POINTS
            // ==========================================

            Queue<Integer> queue = new LinkedList<>();

            // Any course with 0 padlocks is completely unlocked.
            // We add all of them to start our Multi-Source BFS.
            for (int i = 0; i < numCourses; i++) {
                if (inDegree[i] == 0) {
                    queue.add(i);
                }
            }

            // ==========================================
            // STEP 3: PROCESS THE QUEUE (TAKE THE CLASSES)
            // ==========================================

            int[] result = new int[numCourses]; // Holds our final graduation schedule
            int index = 0; // Tracks our position in the result array

            while (!queue.isEmpty()) {
                // "Take" the class by popping it from the queue
                int currentCourse = queue.poll();

                // Add it to our final schedule immediately
                result[index] = currentCourse;
                index++;

                // Tell all neighbors (courses that depended on this one): "I'm done!"
                for (int neighbor : adj.get(currentCourse)) {
                    // Remove one padlock from the neighbor
                    inDegree[neighbor]--;

                    // If the neighbor has 0 padlocks left, it's finally unlocked!
                    // Add it to the queue so we can take it next.
                    if (inDegree[neighbor] == 0) {
                        queue.add(neighbor);
                    }
                }
            }

            // ==========================================
            // STEP 4: CYCLE DETECTION
            // ==========================================

            // If we successfully took every course, 'index' will equal 'numCourses'.
            // This exact check (index == numCourses) is the true/false answer for Course Schedule 1!
            if (index == numCourses) {
                return result; // We graduated. Return the schedule.
            } else {
                // A cycle existed (e.g., A needs B, B needs A), meaning they never reached 0 padlocks.
                return new int[0]; // Impossible to finish, return empty array.
            }
        }

}
