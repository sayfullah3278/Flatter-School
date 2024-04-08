// lib/student_service.dart

import 'dart:convert';
import 'package:http/http.dart' as http;
import 'package:sms_by_flutter/model/student_model.dart';


class StudentService {
  static const String _baseUrl = 'http://localhost:8082/admin/student';

  Future<List<StudentAddModel>> fetchStudents() async {
    final response = await http.get(Uri.parse(_baseUrl));
    if (response.statusCode == 200) {
      final List<dynamic> data = jsonDecode(response.body);
      return data.map((json) => StudentAddModel.fromJson(json)).toList();
    } else {
      throw Exception('Failed to load students');
    }
  }
  Future<List<StudentAddModel>> fetchStudentsByClass(int classNumber) async {
    final response = await http.get(Uri.parse('$_baseUrl/class/$classNumber'));
    if (response.statusCode == 200) {
      final List<dynamic> data = jsonDecode(response.body);
      return data.map((json) => StudentAddModel.fromJson(json)).toList();
    } else {
      throw Exception('Failed to load students for class $classNumber');
    }
  }

  Future<StudentAddModel> addStudent(StudentAddModel student) async {
    final response = await http.post(
      Uri.parse(_baseUrl),
      headers: {'Content-Type': 'application/json'},
      body: jsonEncode(student.toJson()),
    );
    if (response.statusCode == 200) {
      return StudentAddModel.fromJson(jsonDecode(response.body));
    } else {
      throw Exception('Failed to add student');
    }
  }

  Future<StudentAddModel> updateStudent(StudentAddModel student) async {
    final response = await http.put(
      Uri.parse('$_baseUrl/${student.sid}'),
      headers: {'Content-Type': 'application/json'},
      body: jsonEncode(student.toJson()),
    );
    if (response.statusCode == 200) {
      return StudentAddModel.fromJson(jsonDecode(response.body));
    } else {
      throw Exception('Failed to update student');
    }
  }

  Future<void> deleteStudent(int sid) async {
    final response = await http.delete(Uri.parse('$_baseUrl/$sid'));
    if (response.statusCode != 200) {
      throw Exception('Failed to delete student');
    }
  }
}