import 'package:flutter/material.dart';
import 'package:sms_by_flutter/dispaly/admin/teacher_form.dart';
import 'package:sms_by_flutter/model/teacherr_model.dart';
import 'package:sms_by_flutter/service/teacher_service.dart';



class TeacherList extends StatefulWidget {
  @override
  _TeachertListState createState() => _TeachertListState();
}

class _TeachertListState extends State<TeacherList> {
  final TeacherService _teacherService = TeacherService();
  List<TeacherModel> _teachers = [];
  bool _isLoading = true;

  @override
  void initState() {
    super.initState();
    _fetchTeachers();
  }

  Future<void> _fetchTeachers() async {
    try {
      _teachers = await _teacherService.fetchTeachers();
    } catch (e) {
      print(e);
    }
    setState(() {
      _isLoading = false;
    });
  }

  Future<void> _addTeacher(TeacherModel teacher) async {
    try {
      await _teacherService.addTeacher(teacher);
      _fetchTeachers();
    } catch (e) {
      print(e);
    }
  }

  Future<void> _updateTeacher(TeacherModel teacherModel) async {
    try {
      await _teacherService.updateTeacher(teacherModel);
     _fetchTeachers();
    } catch (e) {
      print(e);
    }
  }

  Future<void> _deleteStudent(int tid) async {
    try {
      await _teacherService.deleteTeacher(tid);
      _fetchTeachers();
    } catch (e) {
      print(e);
    }
  }

  void _showAddModal() {
    showDialog(
      context: context,
      builder: (BuildContext context) {
        return TeacherForm(
          onSubmit: _addTeacher,
        );
      },
    );
  }

  void _showEditModal(TeacherModel teacher) {
    showDialog(
      context: context,
      builder: (BuildContext context) {
        return TeacherForm(
          teacher: teacher,
          onSubmit: _updateTeacher,
        );
      },
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Teacher List'),
      ),
      body: _isLoading
          ? Center(child: CircularProgressIndicator())
          : SingleChildScrollView(
        child: DataTable(
          columns: const [
            DataColumn(label: Text('Tid')),
            DataColumn(label: Text('Name')),
            DataColumn(label: Text('Designation')),
            DataColumn(label: Text('Email')),
            DataColumn(label: Text('Phone')),
            DataColumn(label: Text('Actions')),
          ],
          rows: _teachers.map((teacher) {
            return DataRow(

              cells: [
                DataCell(Text(teacher.tid.toString())),
                DataCell(Text(teacher.tname ?? '')),
                DataCell(Text(teacher.tdesignation?? '')),
                DataCell(Text(teacher.temail ?? '')),
                DataCell(Text(teacher.tphone ?? '')),
                DataCell(
                  Row(
                    mainAxisSize: MainAxisSize.min,
                    children: [
                      IconButton(
                        icon: Icon(Icons.edit),
                        onPressed: () => _showEditModal(teacher),
                      ),
                      IconButton(
                        icon: Icon(Icons.delete),
                        onPressed: () => _deleteStudent(teacher.tid!),
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

//   @override
//   Widget build(BuildContext context) {
//     return Scaffold(
//       appBar: AppBar(
//         title: Text('School Management System'),
//       ),
//       body: _isLoading
//           ? Center(child: CircularProgressIndicator())
//           : ListView.builder(
//         itemCount: _students.length,
//         itemBuilder: (context, index) {
//           final student = _students[index];
//           return Card(
//             child: ListTile(
//               title: Text('${student.stfirstname} ${student.stlastname}'),
//               subtitle: Text(student.stemail ?? ''),
//               trailing: Row(
//                 mainAxisSize: MainAxisSize.min,
//                 children: [
//                   IconButton(
//                     icon: Icon(Icons.edit),
//                     onPressed: () => _showEditModal(student),
//                   ),
//                   IconButton(
//                     icon: Icon(Icons.delete),
//                     onPressed: () => _deleteStudent(student.sid!),
//                   ),
//                 ],
//               ),
//             ),
//           );
//         },
//       ),
//       floatingActionButton: FloatingActionButton(
//         child: Icon(Icons.add),
//         onPressed: _showAddModal,
//       ),
//     );
//   }
}