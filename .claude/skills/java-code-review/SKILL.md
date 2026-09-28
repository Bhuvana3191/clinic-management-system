\---

name: java-code-review

description: Reviews Java code in this Clinic Management System project for OOP quality, correctness, and edge cases. Use when asked to review, check, or audit the code.

\---



\# Java Code Review



Review the Java files under src/com/clinic and report findings by severity

(High / Medium / Low).



\## What to check

1\. Encapsulation: private fields, getters/setters only where needed

2\. Null safety: places where a null could cause a NullPointerException

3\. Edge cases in booking logic: time comparison, slot conflicts, cancellation

4\. Unused imports, unused variables, and dead code

5\. Naming conventions and consistent formatting

6\. Duplicate logic that could be extracted into a method

7\. Missing input validation (for example Scanner input in ClinicApp)



\## Output format

\- List each issue with the file name, the method, and a one-line fix suggestion

\- Finish with a short summary of the top 3 improvements

\- Do not rewrite the code unless asked

