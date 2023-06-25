import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
record Transaction(String category, Double amount, LocalDate date) { }

// The main method must be in a class named "Main".
class MainClass {
    public static void main(String[] args) {
        System.out.println("hello world..");


    }










    private static void printPrimeNumbersFromList() {
        //printPrimeNumbersFromList();
        List<Integer> list = List.of(3, 4, 8, 12, 13, 99);
        Predicate<? super Integer> greaterThanOne = n->n>1;
        Predicate<? super Integer> isPrime = n->isPrimeNo(n);
        List<Integer> primeList = list.stream()
                .filter(greaterThanOne)
                .filter(isPrime)
                .collect(Collectors.toList());
        System.out.println("Prime list ="+primeList);
    }

    private static boolean isPrimeNo(Integer num) {
        IntPredicate doesNotModAndNonZeroReminder = rangeNumber->(num%rangeNumber)!=0;
        return IntStream.range(2, num / 2).allMatch(doesNotModAndNonZeroReminder);
    }


    //***Question1 - To retrieve the monthly average amount for each category of transactions using Java stream
    private static void monthlyAvgAmoutForEachCategoryOfTransactions() {
        //monthlyAvgAmoutForEachCategoryOfTransactions();
        List<Transaction> transactions = Arrays.asList(
                new Transaction("PhonePe", 120.0, LocalDate.parse("2023-01-01", DateTimeFormatter.ISO_LOCAL_DATE)),
                new Transaction("Gpay", 4500.0, LocalDate.parse("2023-02-01",DateTimeFormatter.ISO_LOCAL_DATE)),
                new Transaction("paytm", 5444.0, LocalDate.parse("2023-02-01",DateTimeFormatter.ISO_LOCAL_DATE)),
                new Transaction("Gpay", 5444.0, LocalDate.parse("2023-02-01",DateTimeFormatter.ISO_LOCAL_DATE)));

        Map<String, Map<String, Double>> taverageAmounts = transactions.stream()
                .collect(Collectors.groupingBy(Transaction::category,
                        Collectors.groupingBy(t->YearMonth.from(t.date()).toString(),
                                Collectors.averagingDouble(Transaction::amount))));
        System.out.println("Average monthly amount by category =" + taverageAmounts);
    }

    //public class StringSubstringsExample {
        public static void StringSubstringExample() {
            String str = "hello";

//            IntStream.rangeClosed(0, str.length())
//                    .flatMap(start -> IntStream.rangeClosed(start + 1, str.length())
//                            .mapToObj(end -> str.substring(start, end)))
//                    .forEach(System.out::println);
        }
    //***Question1  -- smallest element example java8
     public static void smallestElementExample(String[] args) {
         List<Integer> numbers = Arrays.asList(5, 2, 8, 1, 9, 3);

         Integer smallest = numbers.stream()
                    .min(Integer::compareTo)
                    .orElse(null);
            System.out.println("Smallest element: " + smallest);

         //OR
         Comparator<? super Integer> comp = (n1,n2)->n1.compareTo(n2);
         numbers.stream()
                 .max(comp)
                 .orElse(null);
         System.out.println("Largest element: " + smallest);
        }

    //***Question1  -- example insert element to java streams for collections
    public static void addElementsToJavaStream() {
        List<String> oldElements = new ArrayList<>();
        oldElements.add("Alice");
        oldElements.add("Bob");
        oldElements.add("Charlie");

        String newElement = "Dave";
        List<String> updatedNames = Stream.concat(Stream.of(newElement), oldElements.stream())
                .collect(Collectors.toList());

        System.out.println(updatedNames);
    }
    //***Question1  -- Java 8 - Filter() + Map() + Collect() example even Numbers..
    private static void even(int i){
        int[] numbers= {1,2,3,4,5,6,7};
//        List<Integer> even = Arrays.stream(numbers)
//                .map(s->Integer.valueOf(s))
//                .filter(number->number%2==0)
//                .map(i->i).collect();

        //OR single value
        Stream.of(i)
                .filter(k-> k%2==0)
                .forEach(k->System.out.println("Given number "+k+" is an Even number"));
    }

    //***Question1  -- how to write custom functional interface
    @FunctionalInterface
    interface Squar{
        public int calculate(int x);
    }

    //customFunctionalInterfaceSAMcall()
    private static void customFunctionalInterfaceSAMcall() {
        int a=5;
        Squar squar =(int x) -> x*x;
        int cal = squar.calculate(a);
        System.out.println(cal);
    }

private void checkEmpPresent(){
        //** Question 1-- How to find our specific employee in a list of employees.
    ArrayList<Wizard> wizard = new ArrayList<>();
    wizard.add(new Wizard("Raj",34));
    wizard.add(new Wizard("James",33));

    Wizard james = wizard.stream()
            .filter(w -> "James".equals(w.name))
            .findAny()
            .orElse(null);
}












    //ReverseSortString("Hellowa");
    //isPalindromJava8("malayalam"); isPalindromJava8("ajax");isPalindromJava8("rar");
    //highestSalEachDept();
    private static void highestSalEachDept() {
        //***Question1  -- find highest salary person details in each dept.
        ArrayList<Employee> el = new ArrayList<Employee>();
        el.add(new Employee(1,"raj","IT",6000));
        el.add(new Employee(1,"Avantika","IT",5000));
        el.add(new Employee(1,"padma","IT",5000));
        el.add(new Employee(1,"Seetha","Admin",11000));
        el.add(new Employee(1,"ram","Admin",6000));
        el.add(new Employee(1,"praneeth","HR",8000));
        el.add(new Employee(1,"swapna","HR",85000));

        BinaryOperator<Employee> op = (e1, e2)-> e1.getSalary() > e2.getSalary() ? e1:e2;
        el.stream()
                .collect(Collectors.groupingBy(e->e.getDept(),Collectors.reducing(op)))
                .forEach((k,v)->System.out.println(k+" => "+v.get().getName()+", "+v.get().getSalary()));
    }

    private static void isPalindromJava8(String ajax) {
        //OldWayPalindromWhile(ajax);
        newWayPalindromIntStreamRange(ajax);
    }
    private static void newWayPalindromIntStreamRange(String ajax) {
        //***Question1  -- check given string is Palindrom or not in java8. Ex. malayalam=malayalam
        int len = ajax.length()-1;
        boolean b = false;
        b = IntStream.range(0, len/2)
                .allMatch(position -> ajax.charAt(position) == ajax.charAt(len - position));
        if (b) System.out.println(ajax +" ||  java8 intStream - its a palindrom.");
        else System.out.println(ajax +" || java8 intStream - its a palindrom nooooot.");
    }

    private static void OldWayPalindromWhile(String ajax) {
        //method1 non jav8
        boolean flag=false;
        int bp = 0;
        int len = ajax.length();
        int lp = len-1;
        while (bp<len/2) {
            if(ajax.charAt(bp) == ajax.charAt(lp))
            {flag = true;}
                bp++;
                lp--;
        }
        if (flag) System.out.println("its a palindrom.");
        else System.out.println("its a palindrom nooooot.");
    }

    private static void ReverseSortString(String hellow) {
        //***Question1  -- reverse sort given string in java8
        Comparator<? super Character> comp = (c1,c2) -> c2.compareTo(c1);
        hellow.chars()
                .mapToObj(c->(char)c)
                .sorted(comp)
                .forEach(System.out::println);
    }

    private static void CheckEven(int i) {
        //***Question1  -- check given number is even or not in java8
        Stream.of(i)
                .filter(k-> k%2==0)
                .forEach(k->System.out.println("Given number "+k+" is an Even number"));
    }

    private static void CheckAmstrongNumberJava8(int i) {
        //***Question1  -- check given number is amstrong or not in java8. Amstrong is a number abc=a3+b3+c3 or xy=x2+y2
        int len = String.valueOf(i).split("").length;
        int x = Stream.of(String.valueOf(i).split(""))
                .map(Integer::valueOf)
                .map(k -> (int)Math.pow(k,len))//Use Math.pow
                .mapToInt(k -> k)
                .sum();
                //.forEach(System.out::println);
        if(x==i)
            System.out.println("amstrong..");
        else System.out.println("nooot..");
    }


//        CheckAmstrongNumber(1634);
//        CheckAmstrongNumber2(1634);////153 //1634
//        CheckAmstrongNumberJava8(1634);////153 //1634
    //CheckEven(544);
    //masterCardPractice();
    //whileTrueAlwaysNsystemExitOverFinally();
    //stringContactItself();
    //findDuplicateCharactersInString();
    //duplicateCharsInStringSorted();



    private static void CheckAmstrongNumber2(int i) {
        //***Question1  -- check given number is amstrong or not. Amstrong is a number abc=a3+b3+c3 or xy=x2+y2
        int sum=0;
        int temp=i;
        while (temp>0){
            int r = temp%10;
            sum = (int) (sum+Math.pow(r,String.valueOf(i).length()));
            temp = temp/10;
        }
        if(i == sum) System.out.println(i+" the given number is an Armstrong true..");
        else System.out.println(i+ " The given number is not an Armstrong false..");
    }






    private static void CheckAmstrongNumber(int number) {
        //***Question1  -- check given number is amstrong or not. Amstrong is a number abc=a3+b3+c3 or xy=x2+y2
        int sum =0;
        int temp = number;//153 //1634
        String  str = Integer.toString(number);
        char[] arr = str.toCharArray();
        int length = arr.length;

        while( temp> 0)
        {
            int digit = temp % 10;
            sum += Math.pow(digit,length);
            temp /= 10;
        }

        if(sum == number){
            System.out.println(number+ " is an armstrong number");
        }
        else{
            System.out.println(number+ " is not not amstrong numbner");
        }
    }


    private static void duplicateCharsInStringSorted() {
        //***Question1  -- print duplicate characters in a string and print their count beside.- sorted one
        String inpt = "Raja";
        //Out.
        //A - 2
        //J -1
        //R -1
        inpt.chars()
                .mapToObj(i->(char)i)
                .sorted()
                .collect(Collectors.groupingBy(i->i, Collectors.counting()))
                .forEach((k,v)->System.out.println(k+" => "+v));
    }
    private static void findDuplicateCharactersInString() {
        //***Question1  -- print duplicate characters in a string and print their count beside.
        String str = "Java";
        str.chars()
                .mapToObj(c->(char)c)
                //.collect(Collectors.groupingBy(c->c,Collectors.counting()))
                .collect(Collectors.groupingBy(c->c, Collectors.counting()))
                .forEach((k,v)->System.out.println(k+" - "+v));
        //output =
        // a - 2
        // v - 1
        // J - 1
    }

    private static void whileTrueAlwaysNsystemExitOverFinally() {
        //***Question1 what happens if while condition flag is always true or no changes to the flag--- infinite loop
        //***Question2 what happens if the System.exit(1); is present just before finally block. -- System.exit(1) terminates program over finally block
        boolean flag = false;
        try {
            if (flag) {
                while (flag) {
                    // infinite loop..
                }
                //System.out.println("just Came out of while loop - unreachable code");
            } else {
                System.exit(1);
            }
        } finally {
            System.out.println("In Finally"); // Unreachable code as System.exit(1) terminate the program
        }
    }

    private static void stringContactItself() {
        String x = "abc";
        String y = "abc";
        System.out.println(x+y); //output; abcabc
        System.out.println(x.concat(y)); //Output. abcabc
        x.concat(y);
        System.out.println(x);//Output. abc
        // above line, we havnot put concat ouput to x, so x has its old value abc
    }


    private static void masterCardPractice() {
        System.out.println("Hello world!");

        //***Question1 --- reverse the words in a string or sentence.
        String test = "This is a job interview";
        //output should say "INTERVIEW A IS JOB THIS";

        //Method1
        StringBuilder sb = new StringBuilder("");
        for (int i=test.length();i>0;i--){
                sb.append(test.charAt(i-1));
        }
        System.out.println("reversed string ======="+sb.toString());
        //Method2
        StringBuilder sb2 = new StringBuilder("");
        String[] testArray = test.split(" ");
        for (int j = testArray.length; j>0; j--){
            sb2.append(" ");
            sb2.append(testArray[j-1]);
        }
        System.out.println("reversed string sentence but dont reverse the worlds ======="+sb2.toString().toUpperCase());

        List<Wizard> wizards = new ArrayList<>();
        wizards.add(new Wizard("Harry Potter",11));
        wizards.add(new Wizard("Fred Weasley",13));
        wizards.add(new Wizard("Albus Dumbledore",116));
        wizards.add(new Wizard("Severus Snape",35));
        wizards.add(new Wizard("Rubeus Hagrid",55));
        wizards.add(new Wizard("Minerva McGonagal",71));

        //using StreamAPI
        //***Question2 --- Print all the wizards that are 30 years old and above, follow the example below:
        //e.g.
        //Severus Snape, 35 years old
        System.out.println("============age >= 30");
        wizards.stream()
                .filter(w->w.age>=30)
                .forEach(wz->System.out.println(wz.name+",  "+wz.age+"  years old"));


        ////***Question3 --- Sort all the wizards by last name and print them as lastname space firstname:
        //e.g.
        //Dubledore, Albus
        //Hagrid, Rubeus
        System.out.println("============soft by last name, and print by fname lname");
        wizards.stream()
                .map(w->w.name)
                .sorted()
                .forEach(wz->System.out.println(wz.split(" ")[1]+",  "+wz.split(" ")[0]));

        ////***Question4 --- Create a list of Strings containing the Wizards names
        System.out.println("============collection list of strings containg wizard names");
        wizards.stream()
                .map(z->z.name)
                .collect(Collectors.toList());

        //added testing line to comming and push to branch and hence master branch..
    }


    public static class Wizard {
        private String name;
        private int age;

        public Wizard(String name, int age){
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

    public static class Employee{
        private long id;
        private String name;
        private String dept;
        private double salary;

        public Employee(long id, String name, String dept, double salary) {
            this.id = id;
            this.name = name;
            this.dept = dept;
            this.salary = salary;
        }

        public long getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getDept() {
            return dept;
        }

        public double getSalary() {
            return salary;
        }

        public void setId(long id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setDept(String dept) {
            this.dept = dept;
        }

        public void setSalary(double salary) {
            this.salary = salary;
        }
    }
}