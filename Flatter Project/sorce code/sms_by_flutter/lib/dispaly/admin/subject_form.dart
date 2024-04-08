import 'package:flutter/material.dart';
import 'package:sms_by_flutter/model/subject_model.dart';

class SubjectForm extends StatefulWidget {
  final SubjectAddModel? subject;
  final Function(SubjectAddModel) onSubmit;

  SubjectForm({this.subject, required this.onSubmit});

  @override
  _SubjectFormState createState() => _SubjectFormState();
}

class _SubjectFormState extends State<SubjectForm> {
  final _formKey = GlobalKey<FormState>();
  late SubjectAddModel _subject;

  @override
  void initState() {
    super.initState();
    _subject = widget.subject ?? SubjectAddModel();
  }

  void _submitForm() {
    if (_formKey.currentState!.validate()) {
      widget.onSubmit(_subject);
      Navigator.of(context).pop();
    }
  }

  @override
  Widget build(BuildContext context) {
    return AlertDialog(
      title: Text(widget.subject == null ? 'Add Subject' : 'Edit Subject'),
      content: Form(
        key: _formKey,
        child: Column(
          mainAxisSize: MainAxisSize.min,
          children: [
            TextFormField(
              initialValue: _subject.subid?.toString() ?? '',
              decoration: InputDecoration(labelText: 'Subject ID'),
              validator: (value) {
                if (value == null || value.isEmpty) {
                  return 'Please enter a Subject ID';
                }
                return null;
              },
              onChanged: (value) {
                setState(() {
                  _subject.subid = int.tryParse(value);
                });
              },
            ),
            TextFormField(
              initialValue: _subject.subName ?? '',
              decoration: InputDecoration(labelText: 'Subject Name'),
              validator: (value) {
                if (value == null || value.isEmpty) {
                  return 'Please enter a Subject Name';
                }
                return null;
              },
              onChanged: (value) {
                setState(() {
                  _subject.subName = value;
                });
              },
            ),
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
          child: Text(widget.subject == null ? 'Add' : 'Update'),
        ),
      ],
    );
  }
}
