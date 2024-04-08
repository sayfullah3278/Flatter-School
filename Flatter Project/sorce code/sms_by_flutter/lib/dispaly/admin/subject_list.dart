import 'package:flutter/material.dart';
import 'package:sms_by_flutter/dispaly/admin/subject_form.dart';
import 'package:sms_by_flutter/model/subject_model.dart';
import 'package:sms_by_flutter/service/subject_service.dart';

class SubjectList extends StatefulWidget {
  @override
  _SubjectListState createState() => _SubjectListState();
}

class _SubjectListState extends State<SubjectList> {
  final SubjectService _subjectService = SubjectService();
  List<SubjectAddModel> _subjects = [];
  bool _isLoading = true;

  @override
  void initState() {
    super.initState();
    _fetchSubjects();
  }

  Future<void> _fetchSubjects() async {
    try {
      _subjects = await _subjectService.fetchSubject();
    } catch (e) {
      print(e);
    }
    setState(() {
      _isLoading = false;
    });
  }

  Future<void> _addSubject(SubjectAddModel subject) async {
    try {
      await _subjectService.addSubject(subject);
      _fetchSubjects();
    } catch (e) {
      print(e);
    }
  }

  Future<void> _updateSubject(SubjectAddModel subject) async {
    try {
      await _subjectService.updateSubject(subject);
      _fetchSubjects();
    } catch (e) {
      print(e);
    }
  }

  Future<void> _deleteSubject(int subid) async {
    try {
      await _subjectService.deleteSubject(subid);
      _fetchSubjects();
    } catch (e) {
      print(e);
    }
  }

  void _showAddModal() {
    showDialog(
      context: context,
      builder: (BuildContext context) {
        return SubjectForm(
          onSubmit: _addSubject,
        );
      },
    );
  }

  void _showEditModal(SubjectAddModel subject) {
    showDialog(
      context: context,
      builder: (BuildContext context) {
        return SubjectForm(
          subject: subject,
          onSubmit: _updateSubject,
        );
      },
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('All Subjects List'),
      ),
      body: _isLoading
          ? Center(child: CircularProgressIndicator())
          : ListView.builder(
        itemCount: _subjects.length,
        itemBuilder: (context, index) {
          final subject = _subjects[index];
          return Card(
            child: ListTile(
              title: Text(subject.subName ?? ''),
              subtitle: Text('ID: ${subject.subid ?? ''}'),
              trailing: Row(
                mainAxisSize: MainAxisSize.min,
                children: [
                  IconButton(
                    icon: Icon(Icons.edit),
                    onPressed: () => _showEditModal(subject),
                  ),
                  IconButton(
                    icon: Icon(Icons.delete),
                    onPressed: () => _deleteSubject(subject.subid!),
                  ),
                ],
              ),
            ),
          );
        },
      ),
      floatingActionButton: FloatingActionButton(
        child: Icon(Icons.add),
        onPressed: _showAddModal,
      ),
    );
  }
}
