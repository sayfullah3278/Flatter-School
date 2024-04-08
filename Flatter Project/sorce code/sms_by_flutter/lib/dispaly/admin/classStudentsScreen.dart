import 'package:flutter/material.dart';
import 'package:sms_by_flutter/dispaly/admin/student_form.dart';
import 'package:sms_by_flutter/model/student_model.dart';
import 'package:sms_by_flutter/service/student_service.dart';

class ClassStudentsScreen extends StatefulWidget {
  final int classNumber;

  const ClassStudentsScreen({Key? key, required this.classNumber})
      : super(key: key);

  @override
  _ClassStudentsScreenState createState() => _ClassStudentsScreenState();
}

class _ClassStudentsScreenState extends State<ClassStudentsScreen> {
  final StudentService studentService = StudentService();
  List<StudentAddModel> _students = [];
  bool _isLoading = true;

  @override
  void initState() {
    super.initState();
    _fetchStudents();
  }

  Future<void> _fetchStudents() async {
    try {
      _students = await studentService.fetchStudentsByClass(widget.classNumber);
    } catch (e) {
      print(e);
    }
    setState(() {
      _isLoading = false;
    });
  }

  Future<void> _addStudent(StudentAddModel student) async {
    try {
      await studentService.addStudent(student);
      _fetchStudents();
    } catch (e) {
      print(e);
    }
  }

  Future<void> _updateStudent(StudentAddModel student) async {
    try {
      await studentService.updateStudent(student);
      _fetchStudents();
    } catch (e) {
      print(e);
    }
  }

  Future<void> _deleteStudent(int sid) async {
    try {
      await studentService.deleteStudent(sid);
      _fetchStudents();
    } catch (e) {
      print(e);
    }
  }

  void _showAddModal() {
    showDialog(
      context: context,
      builder: (BuildContext context) {
        return StudentForm(
          onSubmit: _addStudent,
        );
      },
    );
  }

  void _showEditModal(StudentAddModel student) {
    showDialog(
      context: context,
      builder: (BuildContext context) {
        return StudentForm(
          student: student,
          onSubmit: _updateStudent,
        );
      },
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Class ${widget.classNumber} Students'),
      ),
      body: _isLoading
          ? Center(child: CircularProgressIndicator())
          : SingleChildScrollView(
        child: DataTable(
          columns: const [
            DataColumn(label: Text('sid')),
            DataColumn(label: Text('Name')),
            DataColumn(label: Text('Class')),
            DataColumn(label: Text('Roll')),
            DataColumn(label: Text('Email')),
            DataColumn(label: Text('Actions')),
          ],
          rows: _students.map((student) {
            return DataRow(
              cells: [
                DataCell(Text(student.sid.toString())),
                DataCell(Text('${student.stfirstname} ${student.stlastname}')),
                DataCell(Text(student.stClass ?? '')),
                DataCell(Text(student.stRoll ?? '')),

                DataCell(Text(student.stemail ?? '')),
                DataCell(
                  Row(
                    mainAxisSize: MainAxisSize.min,
                    children: [
                      IconButton(
                        icon: Icon(Icons.edit),
                        onPressed: () => _showEditModal(student),
                      ),
                      IconButton(
                        icon: Icon(Icons.delete),
                        onPressed: () => _deleteStudent(student.sid!),
                      ),
                    ],
                  ),
                ),
              ],
            );
          }).toList(),
        ),
      ),
      floatingActionButton: FloatingActionButton(
        child: Icon(Icons.add),
        onPressed: _showAddModal,
      ),
    );
  }
}