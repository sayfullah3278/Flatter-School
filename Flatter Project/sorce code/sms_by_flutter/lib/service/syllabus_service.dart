import 'dart:convert';
import 'package:http/http.dart' as http;
import 'package:flutter/material.dart';
import 'package:sms_by_flutter/dispaly/admin/syllabus_form.dart';
import 'package:sms_by_flutter/model/syllabus_model.dart';

class SyllabusService {
  static const String _baseUrl = 'http://localhost:8082/admin/syllabus';

  Future<List<SyllabusModel>> fetchSyllabuses() async {
    final response = await http.get(Uri.parse(_baseUrl));
    if (response.statusCode == 200) {
      final List<dynamic> data = jsonDecode(response.body);
      return data.map((json) => SyllabusModel.fromJson(json)).toList();
    } else {
      throw Exception('Failed to load syllabuses');
    }
  }

  Future<SyllabusModel> addSyllabus(SyllabusModel syllabus) async {
    final response = await http.post(
      Uri.parse(_baseUrl),
      headers: {'Content-Type': 'application/json'},
      body: jsonEncode(syllabus.toJson()),
    );
    if (response.statusCode == 200) {
      return SyllabusModel.fromJson(jsonDecode(response.body));
    } else {
      throw Exception('Failed to add syllabus');
    }
  }

  Future<SyllabusModel> updateSyllabus(SyllabusModel syllabus) async {
    final response = await http.put(
      Uri.parse('$_baseUrl/${syllabus.syid}'),
      headers: {'Content-Type': 'application/json'},
      body: jsonEncode(syllabus.toJson()),
    );
    if (response.statusCode == 200) {
      return SyllabusModel.fromJson(jsonDecode(response.body));
    } else {
      throw Exception('Failed to update syllabus');
    }
  }

  Future<void> deleteSyllabus(int syid) async {
    final response = await http.delete(Uri.parse('$_baseUrl/$syid'));
    if (response.statusCode != 200) {
      throw Exception('Failed to delete syllabus');
    }
  }
}
