# Player Brief

You have received an APK from a fictional campus incident-response app. Your task is to recover hidden flags and submit them inside the app.

## Rules

- Only analyze the provided APK and your own emulator or test device.
- Do not attack real systems or other students' devices.
- Do not submit flags to anyone except through the agreed classroom process.
- The expected flag format is `FLAG{...}`.

## Suggested tools

- Android Studio emulator
- adb
- jadx
- apktool
- Logcat
- sqlite3 or DB Browser for SQLite
- Burp Suite or mitmproxy for the network challenge

## Hints

Start with the APK structure. An APK is a ZIP archive. Look at the manifest, resources, assets, DEX bytecode, and app components. The app also contains a progress screen, but the progress tracker is not part of the challenge scoring logic outside your own device.
