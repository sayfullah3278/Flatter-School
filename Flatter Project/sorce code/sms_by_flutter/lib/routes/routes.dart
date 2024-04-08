import 'dart:js';

import 'package:flutter/material.dart';
import 'package:sms_by_flutter/dispaly/admin/Subject_form.dart';
import 'package:sms_by_flutter/dispaly/admin/classStudentsScreen.dart';
import 'package:sms_by_flutter/dispaly/admin/dashbord.dart';
import 'package:sms_by_flutter/dispaly/admin/resultList.dart';
import 'package:sms_by_flutter/dispaly/admin/result_form.dart';

import 'package:sms_by_flutter/dispaly/admin/student_list.dart';
import 'package:sms_by_flutter/dispaly/admin/subject_list.dart';
import 'package:sms_by_flutter/dispaly/admin/syllabus_form.dart';
import 'package:sms_by_flutter/dispaly/admin/syllabus_list.dart';
import 'package:sms_by_flutter/dispaly/admin/teacherList.dart';

final Map<String, WidgetBuilder> routes = {
  '/': (context) => Dashbord(),

  '/student/studentlist': (context) => StudentList(),
  '/student/class6': (context) => ClassStudentsScreen(
    classNumber: ModalRoute.of(context)?.settings.arguments as int,
  ),
  '/student/class7': (context) => ClassStudentsScreen(
    classNumber: ModalRoute.of(context)?.settings.arguments as int,
  ),
  '/student/class8': (context) => ClassStudentsScreen(
    classNumber: ModalRoute.of(context)?.settings.arguments as int,

  ),  '/student/class9': (context) => ClassStudentsScreen(
    classNumber: ModalRoute.of(context)?.settings.arguments as int,

  ),  '/student/class10': (context) => ClassStudentsScreen(
    classNumber: ModalRoute.of(context)?.settings.arguments as int,
  ),


  '/teacher/teacherlist': (context) => TeacherList(),



  '/result/resultlist': (context) => ResultList(),
  '/result/resultform':(context) => ResultForm(onSubmit: (ResultAddModel ) {  },),

  // New routes for Subject and Syllabus
  // '/subject/subjectform': (context) => SubjectForm(onSubmit: (SubjectAddModel ) {  },),
  // '/subject/subjectlist': (context) => SubjectList(),
  // '/syllabus/syllabusform': (context) => SyllabusForm(onSubmit: (SyllabusModel ) {  },),
  // '/syllabus/syllabuslist': (context) => SyllabusList(),
  '/subject/subjectform': (context) => SubjectForm(
    onSubmit: (subject) async {
      // Handle the submission of subject form data
    },
  ),
  '/subject/subjectlist': (context) => SubjectList(),
  '/syllabus/syllabusform': (context) => SyllabusForm(
    onSubmit: (syllabus) async {
      // Handle the submission of syllabus form data
    },
  ),
  '/syllabus/syllabuslist': (context) => SyllabusList(),

};