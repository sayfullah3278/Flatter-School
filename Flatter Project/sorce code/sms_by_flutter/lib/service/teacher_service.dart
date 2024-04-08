// lib/teacher_service.dart
import 'dart:convert';
import 'package:http/http.dart' as http;
import 'package:sms_by_flutter/model/teacherr_model.dart';

class TeacherService {
  static const String _baseUrl = 'http://localhost:8082/admin/teacher';

  Future<List<TeacherModel>> fetchTeachers() async {
    final response = await http.get(Uri.parse(_baseUrl));
    if (response.statusCode == 200) {
      final List<dynamic> data = jsonDecode(response.body);
      return data.map((json) => TeacherModel.fromJson(json)).toList();
    } else {
      throw Exception('Failed to load teachers');
    }
  }

  Future<TeacherModel> addTeacher(TeacherModel teacher) async {
    final response = await http.post(
      Uri.parse(_baseUrl),
      headers: {'Content-Type': 'application/json'},
      body: jsonEncode(teacher.toJson()),
    );
    if (response.statusCode == 200) {
      return TeacherModel.fromJson(jsonDecode(response.body));
    } else {
      throw Exception('Failed to add teacher');
    }
  }

  Future<TeacherModel> updateTeacher(TeacherModel teacher) async {
    final response = await http.put(
      Uri.parse('$_baseUrl/${teacher.tid}'),
      headers: {'Content-Type': 'application/json'},
      body: jsonEncode(teacher.toJson()),
    );
    if (response.statusCode == 200) {
      return TeacherModel.fromJson(jsonDecode(response.body));
    } else {
      throw Exception('Failed to update teacher');
    }
  }

  Future<void> deleteTeacher(int tid) async {
    final response = await http.delete(Uri.parse('$_baseUrl/$tid'));
    if (response.statusCode != 200) {
      throw Exception('Failed to delete teacher');
    }
  }
}