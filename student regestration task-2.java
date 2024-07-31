import java.util.*;

class Course {
    String code;
    String title;
    String description;
    int capacity;
    int enrolled;
    String schedule;

    public Course(String code, String title, String description, int capacity, String schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.enrolled = 0;
        this.schedule = schedule;
    }

    public boolean isFull() {
        return enrolled >= capacity;
    }

    public void enroll() {
        if (!isFull()) {
            enrolled++;
        }
    }

    public void drop() {
        if (enrolled > 0) {
            enrolled--;
        }
    }

    @Override
    public String toString() {
        return code + ": " + title + " (" + enrolled + "/" + capacity + " slots filled) - " + schedule;
    }
}

class Student {
    String id;
    String name;
    List<Course> registeredCourses;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public void registerCourse(Course course) {
        if (!course.isFull() && !registeredCourses.contains(course)) {
            registeredCourses.add(course);
            course.enroll();
        }
    }

    public void dropCourse(Course course) {
        if (registeredCourses.remove(course)) {
            course.drop();
        }
    }

    @Override
    public String toString() {
        return "Student ID: " + id + ", Name: " + name;
    }
}

public class CourseRegistrationSystem {
    private static Map<String, Course> courseDatabase = new HashMap<>();
    private static Map<String, Student> studentDatabase = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        initializeCourses();
        initializeStudents();

        while (true) {
            System.out.println("\n--- Course Registration System ---");
            System.out.println("1. List Available Courses");
            System.out.println("2. Register for a Course");
            System.out.println("3. Drop a Course");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    listCourses();
                    break;
                case 2:
                    registerCourse(scanner);
                    break;
                case 3:
                    dropCourse(scanner);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void initializeCourses() {
        courseDatabase.put("CS101", new Course("CS101", "Introduction to Computer Science", "Basic concepts in CS", 30, "Mon/Wed/Fri 9-10am"));
        courseDatabase.put("MATH202", new Course("MATH202", "Calculus II", "Advanced calculus concepts", 25, "Tue/Thu 11am-12:30pm"));
        courseDatabase.put("ENG105", new Course("ENG105", "English Literature", "Exploring classic literature", 20, "Mon/Wed 2-3:30pm"));
    }

    private static void initializeStudents() {
        studentDatabase.put("S001", new Student("S001", "Alice Johnson"));
        studentDatabase.put("S002", new Student("S002", "Bob Smith"));
    }

    private static void listCourses() {
        System.out.println("\nAvailable Courses:");
        for (Course course : courseDatabase.values()) {
            System.out.println(course);
        }
    }

    private static void registerCourse(Scanner scanner) {
        System.out.print("Enter student ID: ");
        String studentId = scanner.nextLine();
        Student student = studentDatabase.get(studentId);

        if (student != null) {
            System.out.print("Enter course code to register: ");
            String courseCode = scanner.nextLine();
            Course course = courseDatabase.get(courseCode);

            if (course != null) {
                student.registerCourse(course);
                System.out.println("Registered successfully.");
            } else {
                System.out.println("Course not found.");
            }
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void dropCourse(Scanner scanner) {
        System.out.print("Enter student ID: ");
        String studentId = scanner.nextLine();
        Student student = studentDatabase.get(studentId);

        if (student != null) {
            System.out.print("Enter course code to drop: ");
            String courseCode = scanner.nextLine();
            Course course = courseDatabase.get(courseCode);

            if (course != null) {
                student.dropCourse(course);
                System.out.println("Dropped successfully.");
            } else {
                System.out.println("Course not found.");
            }
        } else {
            System.out.println("Student not found.");
        }
    }
}
