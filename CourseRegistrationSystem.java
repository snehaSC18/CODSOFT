import java.util.List;

class Course {
    private String courseCode;
    private String title;
    private String description;
    private int capacity;
    private List<Student> enrolledStudents;

    public Course(String courseCode, String title, String description, int capacity) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.enrolledStudents = new ArrayList<>();
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getAvailableSlots() {
        return capacity - enrolledStudents.size();
    }

    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public boolean enrollStudent(Student student) {
        if (enrolledStudents.size() < capacity) {
            enrolledStudents.add(student);
            return true;
        }
        return false;
    }

    public boolean removeStudent(Student student) {
        return enrolledStudents.remove(student);
    }

    @Override
    public String toString() {
        return "Course: " + courseCode + " - " + title + "\nDescription: " + description
                + "\nCapacity: " + capacity + "\nAvailable Slots: " + getAvailableSlots();
    }
}

class Student {
    private int studentID;
    private String name;
    private List<Course> registeredCourses;

    public Student(int studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public int getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public boolean registerCourse(Course course) {
        if (registeredCourses.contains(course)) {
            System.out.println("You are already registered for this course.");
            return false;
        }
        if (course.enrollStudent(this)) {
            registeredCourses.add(course);
            return true;
        }
        System.out.println("Course is full. Cannot register.");
        return false;
    }

    public boolean dropCourse(Course course) {
        if (registeredCourses.remove(course)) {
            course.removeStudent(this);
            return true;
        }
        System.out.println("You are not registered for this course.");
        return false;
    }

    public void displayRegisteredCourses() {
        System.out.println("Registered Courses for " + name + " (ID: " + studentID + "):");
        for (Course course : registeredCourses) {
            System.out.println(course.getCourseCode() + " - " + course.getTitle());
        }
    }
}

public class CourseRegistrationSystem {
    public static void main(String[] args) {
        Course math101 = new Course("MATH101", "Introduction to Mathematics", "Basic math concepts", 30);
        Course phys101 = new Course("PHYS101", "Physics Fundamentals", "Introductory physics", 25);

        Student student1 = new Student(1, "Alice");
        Student student2 = new Student(2, "Bob");

        student1.registerCourse(math101);
        student1.registerCourse(phys101);

        student2.registerCourse(math101);

        System.out.println(math101.toString());
        System.out.println();
        System.out.println(phys101.toString());

        student1.displayRegisteredCourses();
        student2.displayRegisteredCourses();

        student1.dropCourse(math101);

        System.out.println(math101.toString());

        student1.displayRegisteredCourses();
        student2.displayRegisteredCourses();
    }
}
