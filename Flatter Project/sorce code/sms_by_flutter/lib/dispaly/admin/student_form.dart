// lib/student_form.dart
import 'package:flutter/material.dart';
import 'package:sms_by_flutter/model/student_model.dart';

class StudentForm extends StatefulWidget {
  final StudentAddModel? student;
  final Function(StudentAddModel) onSubmit;

  StudentForm({this.student, required this.onSubmit});

  @override
  _StudentFormState createState() => _StudentFormState();
}

class _StudentFormState extends State<StudentForm> {
  final _formKey = GlobalKey<FormState>();
  late StudentAddModel _student;

  // List of available sessions
  final List<String> _sessions = [
    '2024',
    '2025',
    '2026',
    // Add more sessions as needed
  ];
  final List<String> _batchid = [
    'A',
    'B',
    'C',
    // Add more sessions as needed
  ];
  final List<String> _class = [
    '6',
    '7',
    '8',
    '9',
    '10',
    // Add more sessions as needed
  ];
  // final List<String> _gender = [
  //   'Male',
  //   'Female',
  //   // Add more sessions as needed
  // ];

  @override
  void initState() {
    super.initState();
    _student = widget.student ?? StudentAddModel();
  }

  void _submitForm() {
    if (_formKey.currentState!.validate()) {
      widget.onSubmit(_student);
      Navigator.of(context).pop();
    }
  }

  @override
  Widget build(BuildContext context) {
    return AlertDialog(
      title: Text(widget.student == null ? 'Add Student' : 'Edit Student'),
      content: Form(
        key: _formKey,
        child: Column(
          mainAxisSize: MainAxisSize.min,
          children: [
            DropdownButtonFormField<String>(
              value: _student.session,
              decoration: InputDecoration(labelText: 'Session'),
              items: _sessions.map((session) {
                return DropdownMenuItem<String>(
                  value: session,
                  child: Text(session),
                );
              }).toList(),
              validator: (value) {
                if (value == null || value.isEmpty) {
                  return 'Please select a session';
                }
                return null;
              },
              onChanged: (value) {
                setState(() {
                  _student.session = value;
                });
              },
            ),
            DropdownButtonFormField<String>(
              value: _student.batchId,
              decoration: InputDecoration(labelText: 'Batch Id'),
              items: _batchid.map((batchid) {
                return DropdownMenuItem<String>(
                  value: batchid,
                  child: Text(batchid),
                );
              }).toList(),
              validator: (value) {
                if (value == null || value.isEmpty) {
                  return 'Please select a Batch Id';
                }
                return null;
              },
              onChanged: (value) {
                setState(() {
                  _student.batchId = value;
                });
              },
            ),
            DropdownButtonFormField<String>(
              value: _student.stClass,
              decoration: InputDecoration(labelText: 'Class'),
              items: _class.map((classs) {
                return DropdownMenuItem<String>(
                  value: classs,
                  child: Text(classs),
                );
              }).toList(),
              validator: (value) {
                if (value == null || value.isEmpty) {
                  return 'Please select a Class';
                }
                return null;
              },
              onChanged: (value) {
                setState(() {
                  _student.stClass = value;
                });
              },
            ),
            TextFormField(
              initialValue: _student.stRoll,
              decoration: InputDecoration(labelText: 'Roll'),
              validator: (value) {
                if (value == null || value.isEmpty) {
                  return 'Please enter a  Roll';
                }
                return null;
              },
              onChanged: (value) {
                setState(() {
                  _student.stRoll = value;
                });
              },
            ),
            TextFormField(
              initialValue: _student.stfirstname,
              decoration: InputDecoration(labelText: 'First Name'),
              validator: (value) {
                if (value == null || value.isEmpty) {
                  return 'Please enter a first name';
                }
                return null;
              },
              onChanged: (value) {
                setState(() {
                  _student.stfirstname = value;
                });
              },
            ),
            TextFormField(
              initialValue: _student.stlastname,
              decoration: InputDecoration(labelText: 'Last Name'),
              validator: (value) {
                if (value == null || value.isEmpty) {
                  return 'Please enter a last name';
                }
                return null;
              },
              onChanged: (value) {
                setState(() {
                  _student.stlastname = value;
                });
              },
            ),

            TextFormField(
              initialValue: _student.stemail,
              decoration: InputDecoration(labelText: 'Email'),
              validator: (value) {
                if (value == null || value.isEmpty) {
                  return 'Please enter an email';
                }
                return null;
              },
              onChanged: (value) {
                setState(() {
                  _student.stemail = value;
                });
              },
            ),
            // Add more form fields as needed
          ],
        ),
      ),


      actions: [
        TextButton(
          onPressed: () => Navigator.of(context).pop(),
          child: Text('Cancel'),
        ),
        ElevatedButton(
          onPressed: _submitForm,
          child: Text(widget.student == null ? 'Add' : 'Update'),
        ),
      ],
    );
  }
}