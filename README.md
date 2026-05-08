# University of Luxembourg Mobile Security CTF

Android reverse-engineering CTF app for the **Security of Mobiles** course.

The scenario is a campus incident-response application used during a fictional investigation at the University of Luxembourg. The app intentionally contains hidden flags across common Android analysis surfaces: APK contents, resources, Java bytecode, exported components, intents, deep links, local storage, WebView JavaScript, Logcat, SQLite, a content provider, and a small instrumentation challenge.

The app is intentionally vulnerable for a controlled classroom exercise. It does not collect real credentials, attack other devices, exploit external services, or persist maliciously.

## What is included

- Java Android project that opens directly in Android Studio
- 18 flags
- In-app flag submission
- Progress counter and progress bar
- Clean course-themed interface and custom CTF logo
- Player brief in `docs/PLAYER_BRIEF.md`
- Private solution guide in `docs/FLAG_SOLUTIONS_PRIVATE.md`

## Requirements

- Android Studio
- Android SDK Platform 35 or another recent installed platform
- Android emulator or Android phone with USB debugging enabled
- Optional analysis tools for solving: jadx, apktool, adb, Logcat, Burp Suite or mitmproxy

No NDK or CMake setup is required for this version.

## Run from Android Studio

1. Unzip the project.
2. Open Android Studio.
3. Select **Open**.
4. Choose the project folder: `UL_MobileSecurity_CTF`.
5. Wait until Gradle sync finishes.
6. Start an emulator or connect a phone.
7. Press the green **Run** button.

## Build an APK

In Android Studio:

1. Go to **Build**.
2. Select **Build Bundle(s) / APK(s)**.
3. Select **Build APK(s)**.
4. When the build finishes, click **locate** to find the APK.

Command line option, if a Gradle wrapper is added by Android Studio:

```bash
./gradlew assembleDebug
```

On Windows PowerShell:

```powershell
.\gradlew.bat assembleDebug
```

If there is no Gradle wrapper yet, use Android Studio first. Android Studio can create or import the wrapper during normal project setup.


```

## Important files

- `docs/PLAYER_BRIEF.md` can be shared with players.
- `docs/challenge-list.md` gives a high-level challenge overview.
- `docs/FLAG_SOLUTIONS_PRIVATE.md` contains all flags and solution steps.
- `docs/FLAG_SOLUTIONS_PRIVATE.md` must not be shared before the CTF.
