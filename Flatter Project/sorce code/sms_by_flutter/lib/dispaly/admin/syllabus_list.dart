import 'package:flutter/material.dart';
import 'package:sms_by_flutter/dispaly/admin/syllabus_form.dart' as Admin;
import 'package:sms_by_flutter/service/syllabus_service.dart';
import 'package:sms_by_flutter/model/syllabus_model.dart';

class SyllabusList extends StatefulWidget {
  @override
  _SyllabusListState createState() => _SyllabusListState();
}

class _SyllabusListState extends State<SyllabusList> {
  final SyllabusService _syllabusService = SyllabusService();
  List<SyllabusModel> _syllabusList = [];
  bool _isLoading = true;

  @override
  void initState() {
    super.initState();
    _fetchSyllabusList();
  }

  Future<void> _fetchSyllabusList() async {
    try {
      _syllabusList = await _syllabusService.fetchSyllabuses();
    } catch (e) {
      print(e);
    }
    setState(() {
      _isLoading = false;
    });
  }

  Future<void> _addSyllabus(SyllabusModel syllabus) async {
    try {
      await _syllabusService.addSyllabus(syllabus);
      _fetchSyllabusList();
    } catch (e) {
      print(e);
    }
  }

  void _showSyllabusForm() {
    showDialog(
      context: context,
      builder: (BuildContext context) {
        return AlertDialog(
          title: Text('Add New Syllabus'),
          content: Admin.SyllabusForm(
            onSubmit: _addSyllabus,
          ),
        );
      },
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('All Syllabus List'),
      ),
      body: _isLoading
          ? Center(child: CircularProgressIndicator())
          : ListView.builder(
        itemCount: _syllabusList.length,
        itemBuilder: (context, index) {
          final syllabus = _syllabusList[index];
          return Card(
            elevation: 5,
            margin: EdgeInsets.symmetric(horizontal: 10, vertical: 5),
            child: ListTile(
              title: Text('${syllabus.subject ?? ''}'),
              subtitle: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text('Class: ${syllabus.sclass ?? ''}'),
                  Text('Exam Category: ${syllabus.examCategory ?? ''}'),
                  Text('Page No: ${syllabus.pageNo ?? ''}'),
                  Text('Description: ${syllabus.description ?? ''}'),
                  Text('Subject ID: ${syllabus.subjectModel?.subid ?? ''}'),
                  Text('Subject Name: ${syllabus.subjectModel?.subName ?? ''}'),
                ],
              ),
            ),
          );
        },
      ),
      floatingActionButton: FloatingActionButton(
        child: Icon(Icons.add),
        onPressed: _showSyllabusForm,
      ),
    );
  }
}
