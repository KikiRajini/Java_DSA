import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Streams {

    public static List<Integer> filterEvenNumber(List<Integer> numbers){
        return numbers.stream().filter(n -> n%2==0).collect(Collectors.toList());
    }

    public static List<Integer> reverseSortAndDistinct(List<Integer> numbers){
        return numbers.stream().distinct().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
    }

    public static int findMax(List<Integer> numbers){
        return numbers.stream().max((a,b) -> a.compareTo(b)).get();
    }

    public static long countOfWordsWithLengthGreaterThanFive(List<String> words){
        return words.parallelStream().filter(w -> w.length()>5).count();
    }

    public static String highestFreq(List<String> names){
        return names.stream().collect(Collectors.groupingBy(n->n, Collectors.counting())).entrySet().stream().sorted((n1,n2) -> n2.getValue().compareTo(n1.getValue())).limit(1).toList().get(0).getKey();
    }
    //entryset converts map to set so that we can stream process it Set<Map.Entry<String, Long>>..need to stream this further to process it

    public static boolean prime(int n){
        if(n<2){return false;}
        else{
            return IntStream.rangeClosed(2, (int)Math.sqrt(n)).noneMatch(i -> n%i==0);
        }
    }
    //Factors always come in pairs. If a number has a factor that is larger than its square root, it absolutely must have a paired factor that is smaller than its square root.

    public static int sumOfPrimesInARange(int a, int b){
        return IntStream.rangeClosed(a,b).filter(i -> prime(i)).sum();
    }

    public static int secondLargest(List<Integer> input) {
        return input.stream().sorted(Comparator.reverseOrder()).toList().get(1);
    }

    record Employee(String name, Long salary){};
    public static List<Employee> sortBySalary(List<Employee> employees){
        return employees.stream().sorted((e1,e2) -> e2.salary().compareTo(e1.salary())).toList();
    }

    public static void fibonaci(int n){
        int fib[] = new int[n+1];
        fib[0]=0; fib[1]=1;
        IntStream.rangeClosed(2,n).forEach(i -> fib[i]= fib[i-1]+fib[i-2]);
        IntStream.rangeClosed(1,n).forEach(p -> System.out.println(fib[p]));
    }

    public static int sumOfIntInLong(Long l){
        return l.toString().chars().map(c -> c-'0').sum();
    }

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        System.out.println(filterEvenNumber(numbers));

        List<Integer> numbers1 = Arrays.asList(5, 3, 8, 3, 9);
        System.out.println(reverseSortAndDistinct(numbers1));

        System.out.println(findMax(numbers1));

        List<String> words = Arrays.asList("apple", "banana", "cherry");
        System.out.println(countOfWordsWithLengthGreaterThanFive(words));

        List<String> names = Arrays.asList("kiki", "taki", "kiki", "caki", "kaki", "taki", "taki", "saki", "saki", "saki", "saki");
        System.out.println(highestFreq(names));

        System.out.println(prime(37));
        System.out.println(sumOfPrimesInARange(1,7));

        List<Integer> input = Arrays.asList(1,2,3,4,5,2,8,6,1);
        System.out.println(secondLargest(input));

        List<Employee> employees = Arrays.asList(
                new Employee("Alice",  85000L),
                new Employee("Bob",  60000L),
                new Employee("Charlie", 95000L)
        );
        System.out.println(sortBySalary(employees));

        fibonaci(5);

        System.out.println(sumOfIntInLong(12345L));
    }
}

