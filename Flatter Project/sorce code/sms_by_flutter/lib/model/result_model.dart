import 'package:sms_by_flutter/model/student_model.dart';

class ResultAddModel {
  int? rid;
  int? stid;
  int? rbangla;
  int? rmath;
  int? renglish;
  int? rislam;
  int? rscince;
  int? rsocial;
  int? rtotalmark;
  double? ravg;
  double? rgpa;
  String? rgrade;
  String? rpassFail;
  String? rexamcatagory;
  StudentAddModel? studentAddModel;

  ResultAddModel({
    this.rid,
    this.stid,
    this.rbangla,
    this.rmath,
    this.renglish,
    this.rislam,
    this.rscince,
    this.rsocial,
    this.rtotalmark,
    this.ravg,
    this.rgpa,
    this.rgrade,
    this.rpassFail,
    this.rexamcatagory,
    this.studentAddModel,
  });

  factory ResultAddModel.fromJson(Map<String, dynamic> json) {
    return ResultAddModel(
      rid: json['rid'],
      stid: json['stid'],
      rbangla: json['rbangla'],
      rmath: json['rmath'],
      renglish: json['renglish'],
      rislam: json['rislam'],
      rscince: json['rscince'],
      rsocial: json['rsocial'],
      rtotalmark: json['rtotalmark'],
      ravg: json['ravg'],
      rgpa: json['rgpa'],
      rgrade: json['rgrade'],
      rpassFail: json['rpassFail'],
      rexamcatagory: json['rexamcatagory'],
      studentAddModel: StudentAddModel.fromJson(json['studentAddModel']),
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'rid': rid,
      'stid': stid,
      'rbangla': rbangla,
      'rmath': rmath,
      'renglish': renglish,
      'rislam': rislam,
      'rscince': rscince,
      'rsocial': rsocial,
      'rtotalmark': rtotalmark,
      'ravg': ravg,
      'rgpa': rgpa,
      'rgrade': rgrade,
      'rpassFail': rpassFail,
      'rexamcatagory': rexamcatagory,
      'studentAddModel': studentAddModel?.toJson(),
    };
  }
}
