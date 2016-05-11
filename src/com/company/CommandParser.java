package com.company;

import com.company.data.DataBase;
import com.company.data.Idea;
import com.company.data.Student;
import com.company.enums.CommandEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Renaud on 10/05/2016.
 */
public class CommandParser {
    private String cmd;
    private CommandEnum action;
    private String args;
    private DataBase db;

    public CommandParser(DataBase db, String command)
    {
        cmd = command;
        action = parseAction();
        args = parseArguments();
        this.db = db;
    }

    private CommandEnum parseAction()
    {
        return CommandEnum.valueOf(cmd.split(" ")[0]);
    }

    public String parseArguments() {
        int firstSpace = cmd.indexOf(' ');
        return cmd.substring(firstSpace+1);
    }

    public void act()
    {
        switch(parseAction())
        {
            case ADD:
                handleAdd();
                break;
            case IDEAS:
                handleIdeas();
                break;
            case PARTICIPATE:
                handleParticipate();
                break;
            case STUDENTS:
                handleStudents();
                break;
        }
    }

    private void cleanChevrons(String [] args)
    {
        for (int i = 0; i < args.length; i++)
        {
            if (args[i].charAt(0) == '<')
            {
                args[i] = args[i].substring(1);
            }
            if (args[i].charAt(args[i].length()-1) == '>')
            {
                args[i] = args[i].substring(0,args[i].length()-1);
            }
        }
    }

    public void handleAdd()
    {
        String [] argTab = args.split("><");
        for (int i = 0; i < 4; i++) {
            cleanChevrons(argTab);
        }
        Student s = new Student(argTab[1],argTab[0]);
        Idea i = new Idea(db,argTab[0],argTab[1],argTab[2],argTab[3]);
        db.addIdea(i);
        db.addParticipation(i.getId(),s);
    }

    public void handleIdeas()
    {
        List<Idea> ideas = db.getIdeas();
        // Reponse du serveur


    }

    public void handleParticipate()
    {
        String [] argTab = args.split("><");
        for (int i = 0; i < 3; i++) {
            cleanChevrons(argTab);
        }
        //TODO
    }

    public void handleStudents()
    {

    }


}