const express = require('express');
const fs = require('fs');
const path = require('path');
const app = express();
const port = 3000;

app.use(express.urlencoded({ extended: true }));
app.use(express.json());

// Serve static files (HTML, CSS, JS) from the 'public' directory
app.use(express.static('public'));

// Route to display the UI (HTML page)
app.get('/', (req, res) => {
    res.sendFile(path.join(__dirname, 'public', 'index.html'));
});

// Weak error handling: no error handling for file reading
app.get('/read-file', (req, res) => {
    const filename = req.query.filename;  // Get filename from query parameter
    // fs.readFile(filename, 'utf8', (err, data) => {
    //     if (err) {
    //         res.status(500).send('Error reading file: ' + err.message);
    //         return;
    //     }
    //     res.send(data);
    // });

    try {
        const data = fs.readFileSync(filename, 'utf8');
        res.send(data);
    } catch (err) {
        res.status(500).send('File Not Found! ' + '<a href="/">Try again</a>');
        fs.appendFileSync('error.log', `Error reading file: ${err.message}\n`);
    }
});

// Insecure file handling: no sanitization of file paths
app.post('/delete-file', (req, res) => {
    const filename = req.body.filename;  // Get filename from body

    fs.unlink(filename, (err) => {
        if (err) {
            res.status(500).send('Error deleting file: ' + err.message);
            return;
        }
        res.send('File deleted.');
    });
});

// Weak error handling in file appending: no exception handling or input validation
app.post('/log', (req, res) => {
    const userData = req.body.data;  // Get log data from body

    fs.appendFile('log.txt', userData + '\n', (err) => {
        if (err) {
            res.status(500).send('Error appending to log file: ' + err.message);
            return;
        }
        res.send('Log data appended.');
    });
});

app.listen(port, () => {
    console.log(`Vulnerable app listening at http://localhost:${port}`);
});
