import 'package:sms_by_flutter/model/student_model.dart';

class SubjectAddModel{

  int? subid;
  String? subName;

  SubjectAddModel({
    this.subid,
    this.subName,
});

  factory SubjectAddModel.fromJson(Map<String, dynamic> json){

    return SubjectAddModel(
      subid: json['subid'],
      subName: json['subName'],
    );
  }

  Map<String, dynamic> toJson(){

    return {
      'subid': subid,
      'subName': subName,
    };
  }

}
