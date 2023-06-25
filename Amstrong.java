import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

class Armstrong {

    /**
     * /**
     * Java 8 / Lambda approach to generate Armstrong number.
     * Armstrong always start to look from number 1.
     * @param series Number of how many Armstrong number should be generated
     * @return List holding resulting Armstrong number.
     */
    public static List<Integer> generate(int series) {
        return Stream.iterate(1, i -> ++i)
                .filter(i -> i == Stream.of(String.valueOf(i).split(""))
                        .map(Integer::valueOf)
                        .map(n -> (n*n*n))
                        .mapToInt(n -> n)
                        .sum())
                .limit(series)
                .collect(toList());
    }

    public static void main(String[] args) {
        List<Integer> generate = Armstrong.generate(5);
        System.out.println(generate);
    }
}