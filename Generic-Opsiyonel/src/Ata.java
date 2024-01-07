public class Ata {
    public static void main(String[] args) {
        MyList<Integer> integerList = new MyList<>();
        integerList.add(10);
        integerList.add(20);
        integerList.add(30);

        MyList<String> stringList = new MyList<>();
        stringList.add("Java");
        stringList.add("Python");
        stringList.add("C++");

        System.out.println("Integer List: " + integerList.toString());
        System.out.println("String List: " + stringList.toString());
    }
}
