class SyllabusModel {
  int? syid;
  String? sclass;
  String? examCategory;
  String? subject;
  String? pageNo;
  String? description;
  SubjectModel? subjectModel;

  SyllabusModel({
    this.syid,
    this.sclass,
    this.examCategory,
    this.subject,
    this.pageNo,
    this.description,
    this.subjectModel,
  });

  factory SyllabusModel.fromJson(Map<String, dynamic> json) {
    return SyllabusModel(
      syid: json['syid'],
      sclass: json['sclass'],
      examCategory: json['examCatagory'],
      subject: json['subject'],
      pageNo: json['pageNo'],
      description: json['discription'],
      subjectModel: SubjectModel.fromJson(json['subjectModel']),
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'syid': syid,
      'sclass': sclass,
      'examCatagory': examCategory,
      'subject': subject,
      'pageNo': pageNo,
      'discription': description,
      'subjectModel': subjectModel?.toJson(),
    };
  }
}

class SubjectModel {
   int? subid;
   String? subName;

  SubjectModel({
    this.subid,
    this.subName,
  });

  factory SubjectModel.fromJson(Map<String, dynamic> json) {
    return SubjectModel(
      subid: json['subid'],
      subName: json['subName'],
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'subid': subid,
      'subName': subName,
    };
  }
}
