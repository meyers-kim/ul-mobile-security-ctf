function reveal(mode) {
    const marker = "Y291cnNlLWFkbWlu";
    const encodedFlag = "RkxBR3t3ZWJ2aWV3X2NvbnNvbGVfcmV3aXJlZH0=";

    if (mode === atob(marker)) {
        return atob(encodedFlag);
    }
    return "guest mode only";
}
