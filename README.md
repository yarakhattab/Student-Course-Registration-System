
---

# 🎓 Student Course Registration System (Android + SQLite)

## 📘 Description
This project is a **Student Course Registration System** built as an Android application using **SQLite** for local data storage. It allows students to register for courses, manage enrollments, and view academic information via predefined queries.

The system provides a clean, user-friendly interface with multiple activities for different tasks such as adding students, adding courses, enrolling students, and executing structured SQL queries.

---

## 🧱 Features & Requirements

### ✅ Core Features:
- 📚 Add new **students**, **courses**, and **enrollments**.
- 🔄 Execute **predefined SQL queries** to extract meaningful insights.
- 🧭 Navigate easily across activities using **buttons**.
- 🔔 Use of **Toast notifications** to confirm user actions.

### 📁 SQLite Database Schema:
#### 1. Students Table:
| Column      | Data Type | Constraints             |
|-------------|-----------|-------------------------|
| student_id  | INTEGER   | PRIMARY KEY             |
| name        | TEXT      | NOT NULL                |
| email       | TEXT      | UNIQUE, NOT NULL        |

#### 2. Courses Table:
| Column      | Data Type | Constraints             |
|-------------|-----------|-------------------------|
| course_id   | INTEGER   | PRIMARY KEY             |
| title       | TEXT      | NOT NULL                |
| instructor  | TEXT      | NOT NULL                |

#### 3. Enrollments Table:
| Column        | Data Type | Constraints                                      |
|---------------|-----------|--------------------------------------------------|
| enrollment_id | INTEGER   | PRIMARY KEY                                      |
| student_id    | INTEGER   | FOREIGN KEY REFERENCES Students(student_id)      |
| course_id     | INTEGER   | FOREIGN KEY REFERENCES Courses(course_id)        |

---

## 📱 Application Activities

### 🔹 Activity 1: Main Menu
- Navigation hub with buttons for:
  - ➕ Add Student
  - ➕ Add Course
  - ➕ Enroll Student
  - 📊 View Reports (Query Execution)

### 🔹 Activity 2: Query Execution
- Predefined query buttons.
- Results are displayed in **TextViews**.
- Includes an input field for entering a **student ID** for personalized schedule queries.

### 🔹 Activity 3: Add Student
- Form for entering student name and email.
- Saves to `Students` table.

### 🔹 Activity 4: Add Course
- Form for entering course title and instructor name.
- Saves to `Courses` table.

### 🔹 Activity 5: Enroll Student
- Form to assign a student (by ID) to a course (by ID).
- Saves to `Enrollments` table.

---

## 📊 Predefined SQL Queries

1. 📝 List all students and their registered courses.
2. 👥 Display students who have registered for **more than two courses**.
3. ❌ Find courses with **no enrolled students**.
4. 🌟 Show the **most popular course** (by highest number of enrollments).
5. 📅 Show student schedule for a given **student ID** (entered via EditText).

---

## 🧪 Test Data (for Submission Video)

### 👨‍🎓 Students Table:
| student_id | name          | email                    |
|------------|---------------|--------------------------|
| 1          | Ahmed Khaled  | a.khaled@example.com     |
| 2          | Sarah Youssef | s.youssef@example.com    |
| 3          | Laila Mahmoud | l.mahmoud@example.com    |
| 4          | Omar Nabil    | o.nabil@example.com      |

### 📘 Courses Table:
| course_id | title                 | instructor               |
|-----------|-----------------------|--------------------------|
| 1         | Computer Architecture | Dr. Aziz Qaroush         |
| 2         | Applied Cryptography  | Dr. Mohammad Hussein     |
| 3         | Computer Networks     | Dr. Abdelkraim Awad      |
| 4         | Machine Learning      | Dr. Yazan Abufarha       |
| 5         | Operating Systems     | Dr. Adnan Yahya          |

### 🧾 Enrollments Table:
| enrollment_id | student_id | course_id |
|----------------|------------|-----------|
| 1              | 1          | 1         |
| 2              | 1          | 2         |
| 3              | 1          | 3         |
| 4              | 2          | 1         |
| 5              | 2          | 3         |
| 6              | 3          | 2         |
| 7              | 3          | 4         |
| 8              | 4          | 1         |

---

## 🛠 Technologies Used
- **Java / Kotlin** (Android)
- **Android Studio**
- **SQLite (Android built-in)**
- **XML Layouts**
- **Intent-based Activity Navigation**
- **Toast for feedback**

---

## ✍️ Authors 

**Yara Khattab**  

  📧 [yarakhattab16@gmail.com](mailto:yarakhattab16@gmail.com) 

  
  🔗 [GitHub: @yarakhattab](https://github.com/yarakhattab)

