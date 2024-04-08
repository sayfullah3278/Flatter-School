// lib/student_model.dart

class StudentAddModel {
   int? sid;
   String? batchId;
   String? session;
   String? stClass;
   String? stRoll;
   String? stfirstname;
   String? stlastname;
   String? stemail;
   String? stfathersname;
   String? stmothersname;
   String? stpassword;
   String? strole;
   String? stdob;
   String? staddress;
   String? stgender;
   String? stphone;
   String? stPhoto;

  StudentAddModel({
    this.sid,
    this.batchId,
    this.session,
    this.stClass,
    this.stRoll,
    this.stfirstname,
    this.stlastname,
    this.stemail,
    this.stfathersname,
    this.stmothersname,
    this.stpassword,
    this.strole,
    this.stdob,
    this.staddress,
    this.stgender,
    this.stphone,
    this.stPhoto,
  });

  factory StudentAddModel.fromJson(Map<String, dynamic> json) {
    return StudentAddModel(
      sid: json['sid'],
      batchId: json['batchId'],
      session: json['session'],
      stClass: json['stClass'],
      stRoll: json['stRoll'],
      stfirstname: json['stfirstname'],
      stlastname: json['stlastname'],
      stemail: json['stemail'],
      stfathersname: json['stfathersname'],
      stmothersname: json['stmothersname'],
      stpassword: json['stpassword'],
      strole: json['strole'],
      stdob: json['stdob'],
      staddress: json['staddress'],
      stgender: json['stgender'],
      stphone: json['stphone'],
      stPhoto: json['stPhoto'],
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'sid': sid,
      'batchId': batchId,
      'session': session,
      'stClass': stClass,
      'stRoll': stRoll,
      'stfirstname': stfirstname,
      'stlastname': stlastname,
      'stemail': stemail,
      'stfathersname': stfathersname,
      'stmothersname': stmothersname,
      'stpassword': stpassword,
      'strole': strole,
      'stdob': stdob,
      'staddress': staddress,
      'stgender': stgender,
      'stphone': stphone,
      'stPhoto': stPhoto,
    };
  }
}