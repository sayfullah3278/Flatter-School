import 'package:flutter/material.dart';
import 'package:sms_by_flutter/model/subject_model.dart';
import 'package:sms_by_flutter/model/syllabus_model.dart';

class SyllabusForm extends StatefulWidget {
  final SyllabusModel? syllabus;
  final Future<void> Function(SyllabusModel) onSubmit;

  SyllabusForm({this.syllabus, required this.onSubmit});

  @override
  _SyllabusFormState createState() => _SyllabusFormState();
}

class _SyllabusFormState extends State<SyllabusForm> {
  final _formKey = GlobalKey<FormState>();
  late SyllabusModel _syllabus;

  @override
  void initState() {
    super.initState();
    _syllabus = widget.syllabus ?? SyllabusModel(
      syid: 0,
      sclass: '',
      examCategory: '',
      subject: '',
      pageNo: '',
      description: '',
      subjectModel: SubjectModel(subid: 0, subName: ''),
    );
  }

  void _submitForm() {
    if (_formKey.currentState!.validate()) {
      widget.onSubmit(_syllabus);
      Navigator.of(context).pop();
    }
  }

  @override
  Widget build(BuildContext context) {
    return AlertDialog(
      title: Text(widget.syllabus == null ? 'Add Syllabus' : 'Edit Syllabus'),
      content: Form(
        key: _formKey,
        child: Column(
          mainAxisSize: MainAxisSize.min,
          children: [
            TextFormField(
              initialValue: _syllabus.syid.toString(),
              decoration: InputDecoration(labelText: 'Syllabus ID'),
              validator: (value) {
                if (value == null || value.isEmpty) {
                  return 'Please enter a Syllabus ID';
                }
                return null;
              },
              onChanged: (value) {
                setState(() {
                  _syllabus.syid = int.tryParse(value) ?? 0;
                });
              },
            ),
            // Other TextFormField widgets...
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
          child: Text(widget.syllabus == null ? 'Add' : 'Update'),
        ),
      ],
    );
  }
}
