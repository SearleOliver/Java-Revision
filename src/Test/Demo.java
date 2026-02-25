package Test;

import java.util.*;

class Student implements Comparable<Student> {

    int age;
    String name;

    public Student(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return "Student [age=" + age + ", name=" + name + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && getClass() == obj.getClass()) {
            Student op = (Student) obj;
            return (age == op.age) && (name.equals(op.name));
        }
        return false;
    }

    @Override
    public int compareTo(Student op) {
        if (age == op.age)
            return name.compareTo(op.name);
        else if (age > op.age)
            return 1;
        else
            return -1;
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, name);
    }
}


public class Demo {

    public static void main(String[] args) {

        //###########################################################
        // COMPARATOR
        //###########################################################
        Comparator<Student> com = new Comparator<Student>() {
            public int compare(Student one, Student two) {
                if (one.age > two.age)
                    return 1;
                else if (one.age < two.age)
                    return -1;
                return 0;
            }
        };


        //###########################################################
        // LIST
        //###########################################################
        List<Student> students = new ArrayList<>();

        students.add(new Student(3,  "Al"));
        students.add(new Student(73, "Bob Mortimer"));
        students.add(new Student(73, "Alexander the Great"));
        students.add(new Student(73, "Hunter Biden"));
        students.add(new Student(7,  "Desmund"));

        // Natural order (Comparable)
        Collections.sort(students);
        System.out.println("List sorted by Comparable:");
        System.out.println(students);

        // Custom order (Comparator)
        Collections.sort(students, com);
        System.out.println("\nList sorted by Comparator:");
        System.out.println(students);



        //###########################################################
        // TREESET
        //###########################################################
        NavigableSet<Student> studTree =
            new TreeSet<>(new Comparator<Student>() {
                @Override
                public int compare(Student one, Student two) {
                    if (one.age == two.age)
                        return one.name.compareTo(two.name);
                    else if (one.age > two.age)
                        return 1;
                    else
                        return -1;
                }
            });

        studTree.addAll(students);

        System.out.println("\nTreeSet (descending iterator):");
        for (Iterator<Student> it = studTree.descendingIterator();
             it.hasNext();) {
            Student stud = it.next();
            System.out.println(stud);
        }



        //###########################################################
        // SETS (HashSet & LinkedHashSet)
        //###########################################################
        Set<Student> hashSet = new HashSet<>(students);
        System.out.println("\nHashSet (no order):");
        System.out.println(hashSet);

        Set<Student> linkedSet = new LinkedHashSet<>(students);
        System.out.println("\nLinkedHashSet (insertion order):");
        System.out.println(linkedSet);



        //###########################################################
        // MAPS (HashMap, LinkedHashMap, TreeMap)
        //###########################################################
        System.out.println("\n--- MAPS ---");

        // HashMap : no order
        Map<Integer, Student> hashMap = new HashMap<>();
        hashMap.put(1, students.get(0));
        hashMap.put(2, students.get(1));
        System.out.println("\nHashMap:");
        System.out.println(hashMap);

        // LinkedHashMap : insertion order
        Map<Integer, Student> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put(1, students.get(0));
        linkedHashMap.put(2, students.get(1));
        linkedHashMap.put(3, students.get(2));
        System.out.println("\nLinkedHashMap:");
        System.out.println(linkedHashMap);

        // TreeMap : sorted by key
        Map<Integer, Student> treeMap = new TreeMap<>();
        treeMap.put(3, students.get(2));
        treeMap.put(1, students.get(0));
        treeMap.put(2, students.get(1));
        System.out.println("\nTreeMap (sorted by key):");
        System.out.println(treeMap);

        System.out.println("\nIterating TreeMap:");
        for (Map.Entry<Integer, Student> entry : treeMap.entrySet()) {
            System.out.println("Key = " + entry.getKey()
                               + " Value = " + entry.getValue());
        }
    }
}
