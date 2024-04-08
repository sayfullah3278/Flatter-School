class TeacherModel {
  int? tid;
  String? tname;
  String? temail;
  String? tpassword;
  String? tphone;
  dynamic tfathersname;
  dynamic tmothersname;
  String? tgender;
  String? tdob;
  dynamic tjoiningDate;
  String? tsalary;
  dynamic trole;
  dynamic tphoto;
  dynamic tcv;
  String? tdesignation;
  List<dynamic>? paymentModel;

  TeacherModel({
    this.tid,
    this.tname,
    this.temail,
    this.tpassword,
    this.tphone,
    this.tfathersname,
    this.tmothersname,
    this.tgender,
    this.tdob,
    this.tjoiningDate,
    this.tsalary,
    this.trole,
    this.tphoto,
    this.tcv,
    this.tdesignation,
    this.paymentModel,
  });

  TeacherModel.fromJson(Map<String, dynamic> json) {
    tid = json['tid'];
    tname = json['tname'];
    temail = json['temail'];
    tpassword = json['tpassword'];
    tphone = json['tphone'];
    tfathersname = json['tfathersname'];
    tmothersname = json['tmothersname'];
    tgender = json['tgender'];
    tdob = json['tdob'];
    tjoiningDate = json['tjoiningDate'];
    tsalary = json['tsalary'];
    trole = json['trole'];
    tphoto = json['tphoto'];
    tcv = json['tcv'];
    tdesignation = json['tdesignation'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = <String, dynamic>{};
    data['tid'] = tid;
    data['tname'] = tname;
    data['temail'] = temail;
    data['tpassword'] = tpassword;
    data['tphone'] = tphone;
    data['tfathersname'] = tfathersname;
    data['tmothersname'] = tmothersname;
    data['tgender'] = tgender;
    data['tdob'] = tdob;
    data['tjoiningDate'] = tjoiningDate;
    data['tsalary'] = tsalary;
    data['trole'] = trole;
    data['tphoto'] = tphoto;
    data['tcv'] = tcv;
    data['tdesignation'] = tdesignation;
    return data;
  }
}