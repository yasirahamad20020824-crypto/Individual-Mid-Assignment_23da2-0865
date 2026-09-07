# Mini Hospital Emergency Management System

CIT300 Data Structures and Algorithms - Individual Mid Assignment.

This Java console application demonstrates four required data structures:

- **Binary Search Tree (BST):** stores patient records using Patient ID as the key.
- **Queue:** manages emergency patients in FIFO order.
- **Stack:** stores completed treatment records in LIFO order.
- **Singly Linked List:** stores visit history for each patient.

## Project Structure

```text
src/
  main/java/com/hospital/
    EmergencyQueue.java
    HospitalManagementSystem.java
    Patient.java
    PatientBST.java
    TreatmentRecord.java
    TreatmentStack.java
    Visit.java
    VisitHistory.java
  test/java/com/hospital/
    DataStructureTest.java
```

## Requirements

- Java Development Kit (JDK) 17 or later
- No external libraries are required

## Compile and Run

From the project root:

```powershell
New-Item -ItemType Directory -Force out | Out-Null
javac -d out src/main/java/com/hospital/*.java
java -cp out com.hospital.HospitalManagementSystem
```

## Run Tests

```powershell
javac -d out src/main/java/com/hospital/*.java src/test/java/com/hospital/DataStructureTest.java
java -cp out com.hospital.DataStructureTest
```

The interactive menu supports patient registration, searching, deletion, ascending BST display, emergency queue operations, treatment completion/history, and patient visit history operations.

## Suggested GitHub Development History

Use progressive commits such as:

1. `Created Java project structure`
2. `Implemented patient BST`
3. `Added emergency patient queue`
4. `Added treatment history stack`
5. `Added patient visit linked list`
6. `Added menu and integration`
7. `Added data structure tests and README`
