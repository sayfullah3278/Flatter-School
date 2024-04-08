// lib/student_form.dart
import 'package:flutter/material.dart';
import 'package:sms_by_flutter/model/student_model.dart';
import 'package:sms_by_flutter/model/teacherr_model.dart';

class TeacherForm extends StatefulWidget {
  final TeacherModel? teacher;
  final Function(TeacherModel) onSubmit;

  TeacherForm({this.teacher, required this.onSubmit});

  @override
  _TeaceherFormState createState() => _TeaceherFormState();
}

class _TeaceherFormState extends State<TeacherForm> {
  final _formKey = GlobalKey<FormState>();
  late TeacherModel _teacher;

  // List of available sessions
  final List<String> _designation = [
    'Head Teacher',
    'Assisten Teacher',
    'teacher',
    // Add more sessions as needed
  ];

  @override
  void initState() {
    super.initState();
    _teacher = widget.teacher?? TeacherModel();
  }

  void _submitForm() {
    if (_formKey.currentState!.validate()) {
      widget.onSubmit(_teacher);
      Navigator.of(context).pop();
    }
  }

  @override
  Widget build(BuildContext context) {
    return AlertDialog(
      title: Text(widget.teacher == null ? 'Add Teacher' : 'Edit Teacher'),
      content: Form(
        key: _formKey,
        child: Column(
          mainAxisSize: MainAxisSize.min,
          children: [

            TextFormField(
              initialValue: _teacher.tname,
              decoration: InputDecoration(labelText: 'Full Name'),
              validator: (value) {
                if (value == null || value.isEmpty) {
                  return 'Please enter a  Full name';
                }
                return null;
              },
              onChanged: (value) {
                setState(() {
                  _teacher.tname = value;
                });
              },
            ),

            DropdownButtonFormField<String>(
              value: _teacher.tdesignation,
              decoration: InputDecoration(labelText: 'Designation'),
              items: _designation.map((designation) {
                return DropdownMenuItem<String>(
                  value: designation,
                  child: Text(designation),
                );
              }).toList(),
              validator: (value) {
                if (value == null || value.isEmpty) {
                  return 'Please select a designation';
                }
                return null;
              },
              onChanged: (value) {
                setState(() {
                  _teacher.tdesignation = value;
                });
              },
            ),

            TextFormField(
              initialValue: _teacher.temail,
              decoration: InputDecoration(labelText: 'email'),
              validator: (value) {
                if (value == null || value.isEmpty) {
                  return 'Please enter a email';
                }
                return null;
              },
              onChanged: (value) {
                setState(() {
                  _teacher.temail = value;
                });
              },
            ),

            TextFormField(
              initialValue: _teacher.tphone,
              decoration: InputDecoration(labelText: 'Phone'),
              validator: (value) {
                if (value == null || value.isEmpty) {
                  return 'Please enter an Phone';
                }
                return null;
              },
              onChanged: (value) {
                setState(() {
                  _teacher.tphone = value;
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
          child: Text(widget.teacher == null ? 'Add' : 'Update'),
        ),
      ],
    );
  }
}