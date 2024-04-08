import 'package:flutter/material.dart';
import 'package:sms_by_flutter/dispaly/detail_screen.dart';
import 'package:sms_by_flutter/navigation/navigation_drawer.dart';

class Dashbord extends StatefulWidget {
  @override
  _DashbordState createState() => _DashbordState();
}

class _DashbordState extends State<Dashbord> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Admin Dashboard'),
      ),
      drawer: CustomNavigationDrawer(),
      body: SingleChildScrollView(
        child: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              Card(
                child: Column(
                  children: [
                    GestureDetector(
                      onTap: () {
                        Navigator.push(
                          context,
                          MaterialPageRoute(
                            builder: (context) => DetailsPage(
                              imagePath: 'images/classroom.jpg',
                              title: 'Classroom Article',
                              description:
                              'This is an article about the classroom environment and its importance for effective learning.'
                              'This is an article about the classroom environment and its importance for effective learning.'
                                  'In elementary schools (from Kindergarten through 5th grade), classrooms can have a whole group of 18 to 30 students (in some cases these numbers may differ) and one, two, or even three teachers. When there are two teachers in a classroom, one is the lead teacher and the other one is the associate. Or the second teacher might be a special education teacher. There may be a third teacher in the back watching and taking notes. In lower elementary the classrooms are set up slightly different from upper elementary. In these classrooms there are tables instead of desks, a rug with a (Smart Board) for whole group learning, a library, computers, and centers. The rug is the focal point of the classroom and everything else is strategically placed around it. The teacher must be able to move swiftly through the classroom. To determine if the classroom is meeting the highest level of quality there is a grading scale called ECERS (Early Childhood Environment Rating Scale).'
                                  ' There are 43 items on this checklist and it is divided into seven categories and they are as follows: Space and Furnishings, Personal Care Routines, Language-Reasoning, Activities, Interactions, Program Structure, and Parents and Staff.[1] In an upper elementary classroom students now use desks, there is no rug for whole group learning but there is a Smart Board and computers. Students also start practicing switching classes to get accustomed to middle and high, usually in fifth grade school transitions.'

                                  'In a self-contained classrooms there are 7 or fewer students. '
                                  'Self-contained classrooms are designed for children that need more one-on-one time.'
                                  'Teachers get to solely focus on their small group of students and create individualized lessons for each child. An integrated or inclusion classroom can be thought of as a mix between a traditional classroom and a self-contained classroom. In this style of classroom, there is a mix of general students and students that need services. There are two teachers in this style of classroom, a general education teacher and special education teacher. They both teach and serve the students in the classroom, but during certain parts of the day the special education teacher may pull the students that have services to give them additional support. This allows students with accommodations or an Individual Education Program (IEP),'
                                  'to  still get to be in a general classroom but also get the individualized instruction they need.',
                            ),
                          ),
                        );
                      },
                      child: Image.asset('images/classroom.jpg'),
                    ),
                    Padding(
                      padding: const EdgeInsets.all(8.0),
                      child: Text(
                        'Classroom Article',
                        style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
                      ),
                    ),
                    Padding(
                      padding: const EdgeInsets.all(8.0),
                      child: Text(
                       'Middle school and high school classrooms are set up quite similar. There is one teacher and students transition from one classroom to the next. They do not stay in one classroom all day. These classrooms can have around 20 students. Students may not exactly have the same group of students in each class depending on the students',
                      ),
                    ),
                  ],
                ),
              ),
              Card(
                child: Column(
                  children: [
                    GestureDetector(
                      onTap: () {
                        Navigator.push(
                          context,
                          MaterialPageRoute(
                            builder: (context) => DetailsPage(
                              imagePath: 'images/online.jpg',
                              title: 'Online Article',
                              description:
                              'This article discusses the benefits and challenges of online learning and how to make the most of it.',
                            ),
                          ),
                        );
                      },
                      child: Image.asset('images/online.jpg'),
                    ),
                    Padding(
                      padding: const EdgeInsets.all(8.0),
                      child: Text(
                        'Online Article',
                        style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
                      ),
                    ),
                    Padding(
                      padding: const EdgeInsets.all(8.0),
                      child: Text(
                        'This article discusses the benefits and challenges of online learning and how to make the most of it.',
                      ),
                    ),
                  ],
                ),
              ),
              Card(
                child: Column(
                  children: [
                    GestureDetector(
                      onTap: () {
                        Navigator.push(
                          context,
                          MaterialPageRoute(
                            builder: (context) => DetailsPage(
                              imagePath: 'images/service.jpg',
                              title: 'Service Article',
                              description:
                              'This article highlights the importance of providing quality services to students and how it contributes to their overall learning experience.',
                            ),
                          ),
                        );
                      },
                      child: Image.asset('images/service.jpg'),
                    ),
                    Padding(
                      padding: const EdgeInsets.all(8.0),
                      child: Text(
                        'Service Article',
                        style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
                      ),
                    ),
                    Padding(
                      padding: const EdgeInsets.all(8.0),
                      child: Text(
                        'This article highlights the importance of providing quality services to students and how it contributes to their overall learning experience.',
                      ),
                    ),
                  ],
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}