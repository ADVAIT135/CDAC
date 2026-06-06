# Web-based Teacher-Student Examination System with NLP-Driven Automated Scoring and Google Sheets Integration

<p align="left"> 
  <img src="https://github.com/ADVAIT135/CDAC/blob/c560bfcad80156bd0d97d66cc78b69bcfdfe1867/Web-based%20Teacher-Student%20%20Examination%20System%20with%20NLP-Driven%20%20Automated%20Scoring%20and%20Google%20Sheets%20%20Integration/NLP_SCORE.png" / height ="500"/ width="900">
</p>

## 📋 Project Overview
A comprehensive web-based examination system that leverages Natural Language Processing (NLP) for automated answer evaluation and integrates with Google Sheets for efficient data management. This system bridges the gap between traditional examination methods and modern technology, providing an efficient solution for both teachers and students.

## 🚀 Features
- **Web-based Interface**: User-friendly platform accessible to both teachers and students
- **NLP-Powered Evaluation**: Automated scoring of descriptive answers using Natural Language Processing
- **Google Sheets Integration**: Seamless data management and result storage
- **Automated Assessment**: Quick and accurate evaluation of student responses
- **Real-time Results**: Instant feedback and scoring
- **Secure Authentication**: Protected access for teachers and students

## 🛠️ Technologies Used
- **Frontend**: 
  - HTML5
  - CSS3
  - JavaScript
- **Backend**:
  - Python
  - Natural Language Processing Libraries
- **Integration**:
  - Google Sheets API
  - NLP Models
- **Authentication**:
  - User authentication system

## 📦 Installation
1. Clone the repository
```bash
git clone [repository-url]
cd [project-directory]
```

2. Install dependencies
```bash
pip install -r requirements.txt
```

3. Configure Google Sheets API
- Set up Google Cloud Project
- Enable Google Sheets API
- Download credentials and save as `credentials.json`

4. Set up environment variables
```bash
cp .env.example .env
# Edit .env with your configuration
```

## 🔧 Usage
1. Start the application
```bash
python app.py
```

2. Access the web interface at `http://localhost:5000`

3. Login as:
   - Teacher: Create exams, monitor results
   - Student: Take exams, view results

## 🏗️ System Architecture
```
├── frontend/           # Web interface files
├── backend/            # Server-side logic
├── nlp_module/        # NLP processing components
├── sheets_integration/ # Google Sheets API integration
└── utils/             # Helper functions
```

## 💡 How It Works
1. **Teacher Module**:
   - Create and manage examinations
   - Set questions and evaluation criteria
   - View and analyze results

2. **Student Module**:
   - Access assigned examinations
   - Submit answers
   - View results and feedback

3. **NLP Evaluation Process**:
   - Answer processing
   - Semantic analysis
   - Automated scoring
   - Result generation

4. **Google Sheets Integration**:
   - Automated data storage
   - Result compilation
   - Performance tracking

## 🤝 Contributing
Contributions are welcome! Please feel free to submit a Pull Request.

## 📝 License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👥 Authors
- [@ADVAIT135](https://github.com/ADVAIT135)

## 📞 Contact
For any queries or suggestions, please reach out:
- GitHub: [@ADVAIT135](https://github.com/ADVAIT135)

## 🙏 Acknowledgments
- Thanks to all contributors who have helped in developing this system
- Special thanks to mentors and advisors for their guidance
```
