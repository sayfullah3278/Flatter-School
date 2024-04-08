// lib/result_service.dart

import 'dart:convert';
import 'package:http/http.dart' as http;
import 'package:sms_by_flutter/model/result_model.dart';

class ResultService {
  static const String _baseUrl = 'http://localhost:8082/admin/result';

  Future<List<ResultAddModel>> fetchResults() async {
    final response = await http.get(Uri.parse(_baseUrl));
    if (response.statusCode == 200) {
      final List<dynamic> data = jsonDecode(response.body);
      return data.map((json) => ResultAddModel.fromJson(json)).toList();
    } else {
      throw Exception('Failed to load results');
    }
  }

  Future<ResultAddModel> addResult(ResultAddModel result) async {
    final response = await http.post(
      Uri.parse(_baseUrl),
      headers: {'Content-Type': 'application/json'},
      body: jsonEncode(result.toJson()),
    );
    if (response.statusCode == 200) {
      return ResultAddModel.fromJson(jsonDecode(response.body));
    } else {
      throw Exception('Failed to add result');
    }
  }

  Future<ResultAddModel> updateResult(ResultAddModel result) async {
    final response = await http.put(
      Uri.parse('$_baseUrl/${result.rid}'),
      headers: {'Content-Type': 'application/json'},
      body: jsonEncode(result.toJson()),
    );
    if (response.statusCode == 200) {
      return ResultAddModel.fromJson(jsonDecode(response.body));
    } else {
      throw Exception('Failed to update result');
    }
  }

  Future<void> deleteResult(int rid) async {
    final response = await http.delete(Uri.parse('$_baseUrl/$rid'));
    if (response.statusCode != 200) {
      throw Exception('Failed to delete result');
    }
  }
}
