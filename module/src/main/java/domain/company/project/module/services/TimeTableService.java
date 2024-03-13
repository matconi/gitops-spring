package domain.company.project.module.services;

import domain.company.project.module.domain.entities.Lesson;
import domain.company.project.module.domain.entities.Room;
import domain.company.project.module.domain.entities.Teacher;
import domain.company.project.module.domain.entities.Timeslot;
import domain.company.project.module.repositories.LessonRepository;
import domain.company.project.module.repositories.RoomRepository;
import domain.company.project.module.repositories.TeacherRepository;
import domain.company.project.module.repositories.TimeslotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class TimeTableService {

    @Autowired
    TimeslotRepository timeslotRepository;
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    LessonRepository lessonRepository;
    public void generateDemoData() {
        System.out.println("vem bosta");
        List<Timeslot> timeslotList = new ArrayList<>();
        timeslotList.add(new Timeslot(DayOfWeek.MONDAY, LocalTime.of(8, 30), LocalTime.of(9, 30)));
        timeslotList.add(new Timeslot(DayOfWeek.MONDAY, LocalTime.of(9, 30), LocalTime.of(10, 30)));
        timeslotList.add(new Timeslot(DayOfWeek.MONDAY, LocalTime.of(10, 30), LocalTime.of(11, 30)));
        timeslotList.add(new Timeslot(DayOfWeek.MONDAY, LocalTime.of(13, 30), LocalTime.of(14, 30)));
        timeslotList.add(new Timeslot(DayOfWeek.MONDAY, LocalTime.of(14, 30), LocalTime.of(15, 30)));

        timeslotList.add(new Timeslot(DayOfWeek.TUESDAY, LocalTime.of(8, 30), LocalTime.of(9, 30)));
        timeslotList.add(new Timeslot(DayOfWeek.TUESDAY, LocalTime.of(9, 30), LocalTime.of(10, 30)));
        timeslotList.add(new Timeslot(DayOfWeek.TUESDAY, LocalTime.of(10, 30), LocalTime.of(11, 30)));
        timeslotList.add(new Timeslot(DayOfWeek.TUESDAY, LocalTime.of(13, 30), LocalTime.of(14, 30)));
        timeslotList.add(new Timeslot(DayOfWeek.TUESDAY, LocalTime.of(14, 30), LocalTime.of(15, 30)));
        timeslotList.add(new Timeslot(DayOfWeek.WEDNESDAY, LocalTime.of(8, 30), LocalTime.of(9, 30)));
        timeslotList.add(new Timeslot(DayOfWeek.WEDNESDAY, LocalTime.of(9, 30), LocalTime.of(10, 30)));
        timeslotList.add(new Timeslot(DayOfWeek.WEDNESDAY, LocalTime.of(10, 30), LocalTime.of(11, 30)));
        timeslotList.add(new Timeslot(DayOfWeek.WEDNESDAY, LocalTime.of(13, 30), LocalTime.of(14, 30)));
        timeslotList.add(new Timeslot(DayOfWeek.WEDNESDAY, LocalTime.of(14, 30), LocalTime.of(15, 30)));
        timeslotList.add(new Timeslot(DayOfWeek.THURSDAY, LocalTime.of(8, 30), LocalTime.of(9, 30)));
        timeslotList.add(new Timeslot(DayOfWeek.THURSDAY, LocalTime.of(9, 30), LocalTime.of(10, 30)));
        timeslotList.add(new Timeslot(DayOfWeek.THURSDAY, LocalTime.of(10, 30), LocalTime.of(11, 30)));
        timeslotList.add(new Timeslot(DayOfWeek.THURSDAY, LocalTime.of(13, 30), LocalTime.of(14, 30)));
        timeslotList.add(new Timeslot(DayOfWeek.THURSDAY, LocalTime.of(14, 30), LocalTime.of(15, 30)));
        timeslotList.add(new Timeslot(DayOfWeek.FRIDAY, LocalTime.of(8, 30), LocalTime.of(9, 30)));
        timeslotList.add(new Timeslot(DayOfWeek.FRIDAY, LocalTime.of(9, 30), LocalTime.of(10, 30)));
        timeslotList.add(new Timeslot(DayOfWeek.FRIDAY, LocalTime.of(10, 30), LocalTime.of(11, 30)));
        timeslotList.add(new Timeslot(DayOfWeek.FRIDAY, LocalTime.of(13, 30), LocalTime.of(14, 30)));
        timeslotList.add(new Timeslot(DayOfWeek.FRIDAY, LocalTime.of(14, 30), LocalTime.of(15, 30)));
        timeslotRepository.saveAll(timeslotList);

        List<Room> roomList = new ArrayList<>();
        roomList.add(new Room("Room A"));
        roomList.add(new Room("Room B"));
        roomList.add(new Room("Room C"));
        roomList.add(new Room("Room D"));
        roomList.add(new Room("Room E"));
        roomList.add(new Room("Room F"));
        roomRepository.saveAll(roomList);

        Teacher turing = new Teacher(null, "A. Turing", "touring@touring.touring", Collections.emptyList());
        Teacher curie = new Teacher(null, "M. Curie", "curie@curie.curie", Collections.emptyList());
        Teacher darwin = new Teacher(null, "C. Darwin", "darwin@darwin.darwin", Collections.emptyList());
        Teacher jones = new Teacher(null, "I. Jones", "jones@jones.jones", Collections.emptyList());
        Teacher cruz = new Teacher(null, "P. Cruz", "cruz@cruz.cruz", Collections.emptyList());
        Teacher dali = new Teacher(null, "S. Dali", "dali@dali.dali", Collections.emptyList());
        Teacher lewis = new Teacher(null, "C. Lewis", "lewis@lewis.lewis", Collections.emptyList());
        List<Teacher> teacherList = new ArrayList<>();
        teacherList.add(turing);
        teacherList.add(curie);
        teacherList.add(darwin);
        teacherList.add(jones);
        teacherList.add(cruz);
        teacherList.add(dali);
        teacherList.add(lewis);
        teacherRepository.saveAll(teacherList);

        List<Lesson> lessonList = new ArrayList<>();
        lessonList.add(new Lesson("Math", turing, "9th grade"));
        lessonList.add(new Lesson("Math", turing, "9th grade"));
        lessonList.add(new Lesson("Physics", curie, "9th grade"));
        lessonList.add(new Lesson("Chemistry", curie, "9th grade"));
        lessonList.add(new Lesson("Biology", darwin, "9th grade"));
        lessonList.add(new Lesson("History", jones, "9th grade"));
        lessonList.add(new Lesson("English", jones, "9th grade"));
        lessonList.add(new Lesson("English", jones, "9th grade"));
        lessonList.add(new Lesson("Spanish", cruz, "9th grade"));
        lessonList.add(new Lesson("Spanish", cruz, "9th grade"));
        lessonList.add(new Lesson("Math", turing, "9th grade"));
        lessonList.add(new Lesson("Math", turing, "9th grade"));
        lessonList.add(new Lesson("Math", turing, "9th grade"));
        lessonList.add(new Lesson("ICT", turing, "9th grade"));
        lessonList.add(new Lesson("Physics", curie, "9th grade"));
        lessonList.add(new Lesson("Geography", darwin, "9th grade"));
        lessonList.add(new Lesson("Geology", darwin, "9th grade"));
        lessonList.add(new Lesson("History", jones, "9th grade"));
        lessonList.add(new Lesson("English", jones, "9th grade"));
        lessonList.add(new Lesson("Drama", jones, "9th grade"));
        lessonList.add(new Lesson("Art", dali, "9th grade"));
        lessonList.add(new Lesson("Art", dali, "9th grade"));
        lessonList.add(new Lesson("Physical education", lewis, "9th grade"));
        lessonList.add(new Lesson("Physical education", lewis, "9th grade"));
        lessonList.add(new Lesson("Physical education", lewis, "9th grade"));

        lessonList.add(new Lesson("Math", turing, "10th grade"));
        lessonList.add(new Lesson("Math", turing, "10th grade"));
        lessonList.add(new Lesson("Math", turing, "10th grade"));
        lessonList.add(new Lesson("Physics", curie, "10th grade"));
        lessonList.add(new Lesson("Chemistry", curie, "10th grade"));
        lessonList.add(new Lesson("French", curie, "10th grade"));
        lessonList.add(new Lesson("Geography", darwin, "10th grade"));
        lessonList.add(new Lesson("History", jones, "10th grade"));
        lessonList.add(new Lesson("English", cruz, "10th grade"));
        lessonList.add(new Lesson("Spanish", cruz, "10th grade"));
        lessonList.add(new Lesson("Math", turing, "10th grade"));
        lessonList.add(new Lesson("Math", turing, "10th grade"));
        lessonList.add(new Lesson("ICT", turing, "10th grade"));
        lessonList.add(new Lesson("Physics", curie, "10th grade"));
        lessonList.add(new Lesson("Biology", darwin, "10th grade"));
        lessonList.add(new Lesson("Geology", darwin, "10th grade"));
        lessonList.add(new Lesson("History", jones, "10th grade"));
        lessonList.add(new Lesson("English", cruz, "10th grade"));
        lessonList.add(new Lesson("English", cruz, "10th grade"));
        lessonList.add(new Lesson("Drama", jones, "10th grade"));
        lessonList.add(new Lesson("Art", dali, "10th grade"));
        lessonList.add(new Lesson("Art", dali, "10th grade"));
        lessonList.add(new Lesson("Physical education", lewis, "10th grade"));
        lessonList.add(new Lesson("Physical education", lewis, "10th grade"));
        lessonList.add(new Lesson("Physical education", lewis, "10th grade"));

        lessonList.add(new Lesson("Math", turing, "11th grade"));
        lessonList.add(new Lesson("Math", turing, "11th grade"));
        lessonList.add(new Lesson("Math", turing, "11th grade"));
        lessonList.add(new Lesson("Math", turing, "11th grade"));
        lessonList.add(new Lesson("Math", turing, "11th grade"));
        lessonList.add(new Lesson("ICT", turing, "11th grade"));
        lessonList.add(new Lesson("Physics", curie, "11th grade"));
        lessonList.add(new Lesson("Chemistry", curie, "11th grade"));
        lessonList.add(new Lesson("French", curie, "11th grade"));
        lessonList.add(new Lesson("Physics", curie, "11th grade"));
        lessonList.add(new Lesson("Geography", darwin, "11th grade"));
        lessonList.add(new Lesson("Biology", darwin, "11th grade"));
        lessonList.add(new Lesson("Geology", darwin, "11th grade"));
        lessonList.add(new Lesson("History", jones, "11th grade"));
        lessonList.add(new Lesson("History", jones, "11th grade"));
        lessonList.add(new Lesson("English", cruz, "11th grade"));
        lessonList.add(new Lesson("English", cruz, "11th grade"));
        lessonList.add(new Lesson("English", cruz, "11th grade"));
        lessonList.add(new Lesson("Spanish", cruz, "11th grade"));
        lessonList.add(new Lesson("Drama", cruz, "11th grade"));
        lessonList.add(new Lesson("Art", dali, "11th grade"));
        lessonList.add(new Lesson("Art", dali, "11th grade"));
        lessonList.add(new Lesson("Physical education", lewis, "11th grade"));
        lessonList.add(new Lesson("Physical education", lewis, "11th grade"));
        lessonList.add(new Lesson("Physical education", lewis, "11th grade"));

        lessonList.add(new Lesson("Math", turing, "12th grade"));
        lessonList.add(new Lesson("Math", turing, "12th grade"));
        lessonList.add(new Lesson("Math", turing, "12th grade"));
        lessonList.add(new Lesson("Math", turing, "12th grade"));
        lessonList.add(new Lesson("Math", turing, "12th grade"));
        lessonList.add(new Lesson("ICT", turing, "12th grade"));
        lessonList.add(new Lesson("Physics", curie, "12th grade"));
        lessonList.add(new Lesson("Chemistry", curie, "12th grade"));
        lessonList.add(new Lesson("French", curie, "12th grade"));
        lessonList.add(new Lesson("Physics", curie, "12th grade"));
        lessonList.add(new Lesson("Geography", darwin, "12th grade"));
        lessonList.add(new Lesson("Biology", darwin, "12th grade"));
        lessonList.add(new Lesson("Geology", darwin, "12th grade"));
        lessonList.add(new Lesson("History", jones, "12th grade"));
        lessonList.add(new Lesson("History", jones, "12th grade"));
        lessonList.add(new Lesson("English", cruz, "12th grade"));
        lessonList.add(new Lesson("English", cruz, "12th grade"));
        lessonList.add(new Lesson("English", cruz, "12th grade"));
        lessonList.add(new Lesson("Spanish", cruz, "12th grade"));
        lessonList.add(new Lesson("Drama", cruz, "12th grade"));
        lessonList.add(new Lesson("Art", dali, "12th grade"));
        lessonList.add(new Lesson("Art", dali, "12th grade"));
        lessonList.add(new Lesson("Physical education", lewis, "12th grade"));
        lessonList.add(new Lesson("Physical education", lewis, "12th grade"));
        lessonList.add(new Lesson("Physical education", lewis, "12th grade"));
        lessonRepository.saveAll(lessonList);
        System.out.println("vem mais bosta");
    }
}
