# GitHub Setup

Keep the repository private until the CTF is over.

## Command line

```bash
git init -b main
git add .
git commit -m "Initial University of Luxembourg mobile security CTF"
git remote add origin https://github.com/YOUR_USERNAME/ul-mobile-security-ctf.git
git push -u origin main
```

## Team workflow

1. One person owns the final release branch.
2. Each teammate works on one challenge area.
3. Each challenge must be solved by another teammate before release.
4. Do not commit screenshots or files that reveal the private solution guide.
5. Release only the APK to players, not the source repository.

## Suggested branches

```text
main
feature/ui-theme
feature/assets
feature/components
feature/storage
feature/web-network
feature/instrumentation
```
