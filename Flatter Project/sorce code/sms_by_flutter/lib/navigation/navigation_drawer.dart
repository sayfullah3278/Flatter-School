import 'package:flutter/material.dart';

class CustomNavigationDrawer extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Drawer(
      child: ListView(
        padding: EdgeInsets.zero,
        children: <Widget>[
          DrawerHeader(
            child: Text('Admin Dashbord '),
            decoration: BoxDecoration(
              color: Colors.blue,
            ),
          ),
          ListTile(
            title: Text('Home'),
            onTap: () {
              Navigator.pushNamed(context, '/');
            },
          ),
          ExpansionTile(
            leading: Icon(Icons.person),
            title: Text('Student'),
            children: <Widget>[
              ListTile(
                title: Text('Student List'),
                onTap: () {
                  Navigator.pushNamed(context, '/student/studentlist');
                },
              ),
              ListTile(
                title: const Text('Class-6'),
                onTap: () {
                  Navigator.pushNamed(
                    context,
                    '/student/class6',
                    arguments: 6, // Pass the class number as an argument
                  );
                },
              ),
              ListTile(
                title: const Text('Class-7'),
                onTap: () {
                  Navigator.pushNamed(
                    context,
                    '/student/class7',
                    arguments: 7, // Pass the class number as an argument
                  );
                },
              ),ListTile(
                title: const Text('Class-8'),
                onTap: () {
                  Navigator.pushNamed(
                    context,
                    '/student/class8',
                    arguments: 8, // Pass the class number as an argument
                  );
                },
              ),ListTile(
                title: const Text('Class-9'),
                onTap: () {
                  Navigator.pushNamed(
                    context,
                    '/student/class9',
                    arguments: 9, // Pass the class number as an argument
                  );
                },
              ),ListTile(
                title: const Text('Class-10'),
                onTap: () {
                  Navigator.pushNamed(
                    context,
                    '/student/class10',
                    arguments: 10, // Pass the class number as an argument
                  );
                },
              ),
            ],
          ),
          ExpansionTile(
            leading: Icon(Icons.person),
            title: Text('Teacher'),
            children: <Widget>[
              ListTile(
                title: Text('Teacher List'),
                onTap: () {
                  Navigator.pushNamed(context, '/teacher/teacherlist');
                },
              ),
            ],
          ),
          ExpansionTile(
            leading: Icon(Icons.newspaper),
            title: Text('Routine'),
            children: <Widget>[
              ListTile(
                title: Text('Page 2.1'),
                onTap: () {
                  Navigator.pushNamed(context, '/page2/page2.1');
                },
              ),
              ListTile(
                title: Text('Page 2.2'),
                onTap: () {
                  Navigator.pushNamed(context, '/page2/page2.2');
                },
              ),
            ],
          ),
          ExpansionTile(
            leading: Icon(Icons.rate_review),
            title: Text('Result'),
            children: <Widget>[
              ListTile(
                title: Text('Student Result Sheet'),
                onTap: () {
                  Navigator.pushNamed(context, '/result/resultlist');
                },
              ),
              ListTile(
                title: Text('Page 2.2'),
                onTap: () {
                  Navigator.pushNamed(context, '/page2/page2.2');
                },
              ),
            ],
          ),
          ExpansionTile(
            leading: Icon(Icons.payment),
            title: Text('Fee Collection'),
            children: <Widget>[
              ListTile(
                title: Text('Page 2.1'),
                onTap: () {
                  Navigator.pushNamed(context, '/page2/page2.1');
                },
              ),
              ListTile(
                title: Text('Page 2.2'),
                onTap: () {
                  Navigator.pushNamed(context, '/page2/page2.2');
                },
              ),
            ],
          ),
          ExpansionTile(
            leading: Icon(Icons.pages),
            title: Text('Syllabus'),
            children: <Widget>[
              ListTile(
                title: Text('Page 2.1'),
                onTap: () {
                  Navigator.pushNamed(context, '/syllabus/syllabuslist');
                },
              ),
              ListTile(
                title: Text('Page 2.2'),
                onTap: () {
                  Navigator.pushNamed(context, '/syllabus/syllabusform');
                },
              ),
            ],
          ),
          ExpansionTile(
            leading: Icon(Icons.view_day),
            title: Text('Attendance'),
            children: <Widget>[
              ListTile(
                title: Text('Page 2.1'),
                onTap: () {
                  Navigator.pushNamed(context, '/page2/page2.1');
                },
              ),
              ListTile(

                title: Text('Page 2.2'),
                onTap: () {
                  Navigator.pushNamed(context, '/page2/page2.2');
                },
              ),
            ],
          ),
          ExpansionTile(
            leading: Icon(Icons.bookmark),
            title: Text('Subject Add'),
            children: <Widget>[
              ListTile(
                title: Text('Page 2.1'),
                onTap: () {
                  Navigator.pushNamed(context, '/subject/subjectlist');
                },
              ),
              ListTile(
                title: Text('Page 2.2'),
                onTap: () {
                  Navigator.pushNamed(context, '/subject/subjectform');
                },
              ),
            ],
          ),
          ExpansionTile(
            leading: Icon(Icons.call_to_action_outlined),
            title: Text('Admit Card'),
            children: <Widget>[
              ListTile(
                title: Text('Page 2.1'),
                onTap: () {
                  Navigator.pushNamed(context, '/page2/page2.1');
                },
              ),
              ListTile(
                title: Text('Page 2.2'),
                onTap: () {
                  Navigator.pushNamed(context, '/page2/page2.2');
                },
              ),
            ],
          ),
          ExpansionTile(
            leading: Icon(Icons.add_card_sharp),
            title: Text('ID Card'),
            children: <Widget>[
              ListTile(
                title: Text('Page 2.1'),
                onTap: () {
                  Navigator.pushNamed(context, '/page2/page2.1');
                },
              ),
              ListTile(
                title: Text('Page 2.2'),
                onTap: () {
                  Navigator.pushNamed(context, '/page2/page2.2');
                },
              ),
            ],
          ),
          ExpansionTile(
            leading: Icon(Icons.logout),
            title: Text('Logout'),
            children: <Widget>[
              ListTile(
                title: Text('Page 2.1'),
                onTap: () {
                  Navigator.pushNamed(context, '/page2/page2.1');
                },
              ),
              ListTile(
                title: Text('Page 2.2'),
                onTap: () {
                  Navigator.pushNamed(context, '/page2/page2.2');
                },
              ),
            ],
          ),



        ],
      ),
    );
  }
}