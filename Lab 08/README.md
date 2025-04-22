## 1. Learning Objectives

By the end of this session students will be able to:

1. Identify common file‑handling and error‑handling vulnerabilities in a Node.js/Express web app.
2. Explain the risks associated with poor input validation and weak error handling.
3. Apply secure coding patterns to mitigate path traversal and unhandled exceptions.
4. Refactor existing code to enforce input sanitization, proper error handling, and safe defaults.
5. Demonstrate the secure version of the web app and verify its behavior.

---

## 2. Prerequisites & Materials

- **Prerequisites:**
  - Basic JavaScript/Node.js and Express knowledge.
  - Git, Node.js v14+ installed.
  - Code editor (VS Code recommended).

- **Materials:**
  - Cloned project folder containing the vulnerable Express app.
  - Handout with code snippets and lab instructions (this document).

---

## 3. Detailed Lab Tasks

1. **Secure Read Endpoint**
   - Wrap `fs.readFile` in `try/catch` or handle callback errors.
   - Validate `req.query.filename` against a whitelist directory.
   - Return `400 Bad Request` for invalid input.

```JavaScript
   app.get('/read-file', (req, res) => {
  const filename = req.query.filename;
  const allowedDir = path.resolve(__dirname, 'user_files');
  const userPath = path.resolve(allowedDir, filename);

  // Validate that the resolved path is within our allowed directory
  if (!userPath.startsWith(allowedDir + path.sep)) {
    return res.status(400).send('Invalid file path.');
  }

  // Read file with try/catch for synchronous error handling
  try {
    const data = fs.readFileSync(userPath, 'utf8');
    res.status(200).send(data);
  } catch (err) {
    if (err.code === 'ENOENT') {
      return res.status(404).send('File not found.');
    }
    console.error('Read error:', err);
    res.status(500).send('An internal error occurred.');
  }
});
```
- Path Resolution & Whitelisting: `path.resolve(allowedDir, filename)` ensures any relative or absolute input is anchored inside user_files.

- Directory Check: `startsWith(allowedDir + path.sep)` prevents traversal (e.g., ../secret.txt).

- Synchronous Read with try/catch: Simplifies error handling.

- Granular Errors: Distinguish ENOENT (404) vs. other errors (500).

- Tip: Always append path.sep when comparing prefixes to avoid false positives (e.g., /app/user_files_malicious).

> [!NOTE]
> Note: You can also use `fs.promises.readFile` with `async/await` and `try/catch` for non‑blocking I/O.

2. **Secure Delete Endpoint**
   - Resolve the user-supplied path and ensure it begins with `path.resolve(__dirname, 'user_files')`.
   - Catch and handle `ENOENT` and permission errors separately.

3. **Secure Logging Endpoint**
   - Use `fs.appendFileSync` inside `try/catch`.
   - Sanitize or escape user data to avoid unexpected control characters.

---

## 5. Further Reading & Resources

- OWASP File Handling Cheat Sheet: https://cheatsheetseries.owasp.org/cheatsheets/File_Handling_Cheat_Sheet.html
- Node.js Security Best Practices: https://nodejs.org/en/docs/guides/security/
- Express Error Handling: https://expressjs.com/en/guide/error-handling.html



