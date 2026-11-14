# Patient Management Portal

**CE/CS/SE 3354 Software Engineering — Team 2**  
**Repository:** [https://github.com/lancelot126/F26_3354_Team2](https://github.com/lancelot126/F26_3354_Team2)

---

## Overview

The **Patient Management Portal** is a centralized healthcare information system designed to streamline clinical workflows, secure patient medical records, and simplify appointment and prescription management. Built around a **Repository Architecture** routed through an **Authentication & Authorization Service**, the system enforces strict Role-Based Access Control (RBAC) in alignment with HIPAA privacy and security standards.

---

## Key Features

- **Patient Record Management (FR-1, FR-2, FR-3, FR-8):**
  - Doctors can retrieve, update, save, and filter patient medical histories by date, condition, or ID.
  - Patients have read-only access restricted strictly to their own medical records.
- **Appointment Scheduling & Notifications (FR-4, FR-7):**
  - Online booking system allowing patients to view available slots and schedule appointments while preventing double-booking conflicts.
  - Automated appointment reminders sent via an external notification gateway.
- **Prescription Management (FR-6):**
  - Standardized workflow for doctors to validate, issue, log, and transmit prescriptions directly to external pharmacy systems.
- **Role-Based Access Control & Administration (FR-5):**
  - Administrators manage user roles (`DOCTOR`, `NURSE`, `PATIENT`, `RECEPTIONIST`, `ADMINISTRATOR`) and access permissions.

---

## System Architecture

- **Architectural Pattern:** Repository Architecture
- **Core Components:**
  - **Hospital Data Repository:** Centralized persistent SQL database storing patient records, appointments, prescriptions, and access policies.
  - **Authentication Service:** Security middleman validating credentials and enforcing RBAC before granting access to underlying repository data.
  - **Domain Services:**
    - Patient Records Service
    - Appointment Scheduling Service
    - Prescription Management Service
    - Insurance Policy Management Service
    - Notification Service
- **Development Methodology:** Agile / Scrum

---

## Unit Testing & Verification

Unit tests are implemented using **Java 17**, **JUnit 5**, and **Mockito** to verify access control enforcement and record retrieval logic in `PatientService`.

### Sample Test Coverage (`PatientServiceTest.java`)
| Test ID | Scenario | Expected Outcome | Status |
| :--- | :--- | :--- | :--- |
| **TC-1** | Authorized `DOCTOR` retrieves patient record (`P-1001`) | Returns valid `PatientRecord` | PASS |
| **TC-2** | `PATIENT` accesses their own record (`P-1001`) | Returns valid `PatientRecord` | PASS |
| **TC-3** | Unauthorized `RECEPTIONIST` attempts record access | Throws `SecurityException` (`Access denied`) | PASS |

### Running Tests
```bash
mvn test