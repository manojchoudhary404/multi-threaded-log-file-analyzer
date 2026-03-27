# 🧵 Multi-Threaded Log File Analyzer

A Java console application that analyzes large log files using multiple threads to concurrently count log level occurrences (`INFO`, `ERROR`, `WARNING`).

---

## 📁 Project Structure

```
multi_threaded_log_file_analyzer/
├── src/
│   └── com/
│       └── loganalyzer/
│           ├── app/
│           │   └── LogAnalyzerApp.java       # Main entry point
│           └── service/
│               └── LogProcessor.java         # Runnable thread worker
├── log.txt                                   # Sample log file
├── .gitignore                                # Git ignore rules
└── README.md                                 # Project documentation
```

---

## ⚙️ Prerequisites

- Java JDK 11 or higher
- Terminal / Command Prompt

---

## 🚀 How to Run

### Step 1 – Compile

```bash
javac -d . src/com/loganalyzer/**/*.java
```

### Step 2 – Run

```bash
java com.loganalyzer.app.LogAnalyzerApp
```

---

## 📄 Log File Format

Place your log file as `log.txt` in the project root. Each line must follow this format:

```
2026-03-10 INFO  User logged in
2026-03-10 ERROR Database connection failed
2026-03-10 WARNING Disk space low
```

| Field    | Description                          |
|----------|--------------------------------------|
| Date     | `YYYY-MM-DD` format                  |
| Level    | `INFO`, `ERROR`, or `WARNING`        |
| Message  | Any descriptive text                 |


---

## 🧠 How It Works

1. **File Reading** – `LogAnalyzerApp` reads all lines from `log.txt` using Java File I/O.
2. **Partitioning** – Lines are split into equal chunks, one per thread.
3. **Multi-Threading** – Each `LogProcessor` thread counts `INFO`, `ERROR`, and `WARNING` in its chunk using local counters.
4. **Result Merging** – Thread-local counts are combined into shared `AtomicInteger` counters for thread safety.
5. **Output** – Final totals are printed to the console.

---

## 🔒 Thread Safety

- Each thread uses **local variables** during processing (zero contention).
- Results are merged using **`AtomicInteger.addAndGet()`** — no `synchronized` block needed.
- `ExecutorService.awaitTermination()` ensures all threads finish before results are displayed.

---

## 📦 Classes Overview

| Class              | Package                       | Responsibility                        |
|--------------------|-------------------------------|---------------------------------------|
| `LogAnalyzerApp`   | `com.loganalyzer.app`         | Entry point, file reading, thread mgmt|
| `LogProcessor`     | `com.loganalyzer.service`     | Runnable: counts log levels per chunk |

---

## 👨‍💻 Author

**Manoj Choudhary**

