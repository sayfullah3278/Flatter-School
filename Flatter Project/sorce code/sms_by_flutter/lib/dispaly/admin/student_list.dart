import 'package:flutter/material.dart';
import 'package:sms_by_flutter/dispaly/admin/student_form.dart';
import 'package:sms_by_flutter/model/student_model.dart';
import 'package:sms_by_flutter/service/student_service.dart';


class StudentList extends StatefulWidget {
  @override
  _StudentListState createState() => _StudentListState();
}

class _StudentListState extends State<StudentList> {
  final StudentService _studentService = StudentService();
  List<StudentAddModel> _students = [];
  bool _isLoading = true;

  @override
  void initState() {
    super.initState();
    _fetchStudents();
  }

  Future<void> _fetchStudents() async {
    try {
      _students = await _studentService.fetchStudents();
    } catch (e) {
      print(e);
    }
    setState(() {
      _isLoading = false;
    });
  }

  Future<void> _addStudent(StudentAddModel student) async {
    try {
      await _studentService.addStudent(student);
      _fetchStudents();
    } catch (e) {
      print(e);
    }
  }

  Future<void> _updateStudent(StudentAddModel student) async {
    try {
      await _studentService.updateStudent(student);
      _fetchStudents();
    } catch (e) {
      print(e);
    }
  }

  Future<void> _deleteStudent(int sid) async {
    try {
      await _studentService.deleteStudent(sid);
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

//   @override
//   Widget build(BuildContext context) {
//     return Scaffold(
//       appBar: AppBar(
//         title: Text('Student List'),
//       ),
//       body: _isLoading
//           ? Center(child: CircularProgressIndicator())
//           : SingleChildScrollView(
//         child: DataTable(
//           columns: const [
//             DataColumn(label: Text('sid')),
//             DataColumn(label: Text('Name')),
//             DataColumn(label: Text('Class')),
//             DataColumn(label: Text('Roll')),
//             DataColumn(label: Text('Email')),
//             DataColumn(label: Text('Actions')),
//           ],
//           rows: _students.map((student) {
//             return DataRow(
//
//               cells: [
//                 DataCell(Text(student.sid.toString())),
//                 DataCell(Text('${student.stfirstname} ${student.stlastname}')),
//                 DataCell(Text(student.stClass ?? '')),
//                 DataCell(Text(student.stRoll ?? '')),
//                 DataCell(Text(student.stemail ?? '')),
//                 DataCell(
//                   Row(
//                     mainAxisSize: MainAxisSize.min,
//                     children: [
//                       IconButton(
//                         icon: Icon(Icons.edit),
//                         onPressed: () => _showEditModal(student),
//                       ),
//                       IconButton(
//                         icon: Icon(Icons.delete),
//                         onPressed: () => _deleteStudent(student.sid!),
//                       ),
//                     ],
//                   ),
//                 ),
//               ],
//             );
//           }).toList(),
//         ),
//       ),
//       floatingActionButton: FloatingActionButton(
//         child: Icon(Icons.add),
//         onPressed: _showAddModal,
//       ),
//     );
//   }
// }










  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('All Student List'),
      ),
      body: _isLoading
          ? Center(child: CircularProgressIndicator())
          : ListView.builder(
        itemCount: _students.length,
        itemBuilder: (context, index) {
          final student = _students[index];
          return
            Card(
              child: ListTile(
                title: Text('${student.stfirstname} ${student.stlastname}'),
                subtitle: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Text('Class: ${student.stClass ?? ''}'),
                    Text('Roll: ${student.stRoll ?? ''}'),
                    Text(student.stemail ?? ''),

                  ],
                ),
              trailing: Row(
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