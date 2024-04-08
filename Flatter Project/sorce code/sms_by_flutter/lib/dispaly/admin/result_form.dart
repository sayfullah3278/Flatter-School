import 'package:flutter/material.dart';
import 'package:sms_by_flutter/model/result_model.dart';
import 'package:sms_by_flutter/model/student_model.dart';

class ResultForm extends StatefulWidget {
  final Function(ResultAddModel) onSubmit;

  ResultForm({required this.onSubmit});

  @override
  _ResultFormState createState() => _ResultFormState();
}

class _ResultFormState extends State<ResultForm> {
  final _formKey = GlobalKey<FormState>();
  ResultAddModel _result = ResultAddModel();

  @override
  Widget build(BuildContext context) {
    return AlertDialog(
      title: Text('Add Result'),
      content: Form(
        key: _formKey,
        child: Column(
          mainAxisSize: MainAxisSize.min,
          crossAxisAlignment: CrossAxisAlignment.start,
          children: <Widget>[
            TextFormField(
              decoration: InputDecoration(labelText: 'Bangla'),
              validator: (value) {
                if (value == null || value.isEmpty) {
                  return 'Please enter Bangla marks';
                }
                return null;
              },
              onSaved: (value) {
                _result.rbangla = int.parse(value!);
              },
            ),
            // Similar TextFormField for other fields

            TextFormField(
              decoration: InputDecoration(labelText: 'Student ID'),
              validator: (value) {
                if (value == null || value.isEmpty) {
                  return 'Please enter student ID';
                }
                return null;
              },
              onSaved: (value) {
                _result.stid = int.parse(value!);
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
          onPressed: () {
            if (_formKey.currentState!.validate()) {
              _formKey.currentState!.save();
              widget.onSubmit(_result);
              Navigator.of(context).pop();
            }
          },
          child: Text('Submit'),
        ),
      ],
    );
  }
}
