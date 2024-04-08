

import 'dart:convert';
import 'package:http/http.dart' as http;
import 'package:sms_by_flutter/main.dart';
import 'package:sms_by_flutter/model/subject_model.dart';

class SubjectService{
  static const String _baseUrl = 'http://localhost:8082/admin/subject';

  Future<List<SubjectAddModel>> fetchSubject() async {
    final response = await http.get(Uri.parse(_baseUrl));
    if (response.statusCode == 200) {
      final List<dynamic> data = jsonDecode(response.body);
      return data.map((json) => SubjectAddModel.fromJson(json)).toList();
    } else {
      throw Exception('Failed to load students');
    }
  }
  Future<List<SubjectAddModel>> fetchStudentsByClass(int classNumber) async {
    final response = await http.get(Uri.parse('$_baseUrl/class/$classNumber'));
    if (response.statusCode == 200) {
      final List<dynamic> data = jsonDecode(response.body);
      return data.map((json) => SubjectAddModel.fromJson(json)).toList();
    } else {
      throw Exception('Failed to load students for class $classNumber');
    }
  }

  Future<SubjectAddModel> addSubject(SubjectAddModel sub) async {
    final response = await http.post(
      Uri.parse(_baseUrl),
      headers: {'Content-Type': 'application/json'},
      body: jsonEncode(sub.toJson()),
    );
    if (response.statusCode == 200) {
      return SubjectAddModel.fromJson(jsonDecode(response.body));
    } else {
      throw Exception('Failed to add student');
    }
  }

  Future<SubjectAddModel> updateSubject(SubjectAddModel sub) async {
    final response = await http.put(
      Uri.parse('$_baseUrl/${sub.subid}'),
      headers: {'Content-Type': 'application/json'},
      body: jsonEncode(sub.toJson()),
    );
    if (response.statusCode == 200) {
      return SubjectAddModel.fromJson(jsonDecode(response.body));
    } else {
      throw Exception('Failed to update student');
    }
  }

  Future<void> deleteSubject(int subid) async {
    final response = await http.delete(Uri.parse('$_baseUrl/$subid'));
    if (response.statusCode != 200) {
      throw Exception('Failed to delete student');
    }
  }



}