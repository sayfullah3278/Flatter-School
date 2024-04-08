import 'package:flutter/material.dart';
import 'package:sms_by_flutter/dispaly/admin/result_form.dart';
import 'package:sms_by_flutter/model/result_model.dart';
import 'package:sms_by_flutter/service/result_service.dart';

class ResultList extends StatefulWidget {
  @override
  _ResultListState createState() => _ResultListState();
}

class _ResultListState extends State<ResultList> {
  final ResultService _resultService = ResultService();
  List<ResultAddModel> _resultList = [];
  bool _isLoading = true;

  @override
  void initState() {
    super.initState();
    _fetchResultList();
  }

  Future<void> _fetchResultList() async {
    try {
      _resultList = await _resultService.fetchResults();
    } catch (e) {
      print(e);
    }
    setState(() {
      _isLoading = false;
    });
  }

  Future<void> _addResult(ResultAddModel result) async {
    try {
      await _resultService.addResult(result);
      _fetchResultList();
    } catch (e) {
      print(e);
    }
  }

  Future<void> _updateResult(ResultAddModel result) async {
    try {
      await _resultService.updateResult(result);
      _fetchResultList();
    } catch (e) {
      print(e);
    }
  }

  Future<void> _deleteResult(int rid) async {
    try {
      await _resultService.deleteResult(rid);
      _fetchResultList();
    } catch (e) {
      print(e);
    }
  }

  void _showAddModal() {
    showDialog(
      context: context,
      builder: (BuildContext context) {
        return ResultForm(
          onSubmit: _addResult,
        );
      },
    );
  }

  void _showEditModal(ResultAddModel result) {
    showDialog(
      context: context,
      builder: (BuildContext context) {
        return ResultForm(
           // Pass the result object here
          onSubmit: _updateResult,
        );
      },
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('All Result List'),
      ),
      body: _isLoading
          ? Center(child: CircularProgressIndicator())
          : ListView.builder(
        itemCount: _resultList.length,
        itemBuilder: (context, index) {
          final result = _resultList[index];
          return Card(
            child: ListTile(
              title: Text('Result ID: ${result.rid ?? ''}'),
              subtitle: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text('Student ID: ${result.stid ?? ''}'),
                  // Add more fields as needed
                ],
              ),
              trailing: Row(
                mainAxisSize: MainAxisSize.min,
                children: [
                  IconButton(
                    icon: Icon(Icons.edit),
                    onPressed: () => _showEditModal(result),
                  ),
                  // IconButton(
                  //   icon: Icon(Icons.delete),
                  //   onPressed: () => _deleteResult(result.rid),
                  // ),
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
