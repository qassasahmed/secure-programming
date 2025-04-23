const express = require('express');
const fs = require('fs');
const path = require('path');
const multer = require('multer');
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
    fs.readFile(filename, 'utf8', (err, data) => {
        if (err) {
            res.status(500).send('Error reading file: ' + err.message);
            return;
        }
        res.send(data);
    });

});

// Secure file upload handling

// Configure multer for file uploads
const upload = multer({
    dest: 'uploads/', // Destination folder for uploaded files
    fileFilter: (req, file, cb) => {
        // Allow only image files
        if (!file.mimetype.startsWith('image/')) {
            return cb(new Error('Only image files are allowed'), false);
        }
        cb(null, true);
    },
    limits: { fileSize: 5 * 1024 * 1024 } // Limit file size to 5MB
});

// Route to handle file uploads
app.post('/upload-file', upload.single('file'), (req, res) => {
    if (!req.file) {
        return res.status(400).send('No file uploaded.');
    }
    res.send(`File uploaded successfully: ${req.file.originalname}`);
});


app.listen(port, () => {
    console.log(`Vulnerable app listening at http://localhost:${port}`);
});
