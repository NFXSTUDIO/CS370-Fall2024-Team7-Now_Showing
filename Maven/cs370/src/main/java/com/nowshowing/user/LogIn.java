package com.nowshowing.user;

import java.util.ArrayList;
import java.util.Objects;

public class LogIn {
    public static ArrayList<String> log_in(String username, String password){
        ArrayList<String> export = new ArrayList<>();
        ArrayList<String> data = UserHandler.add_data_to_database(STR."u,uv,\{username}");

        if(Objects.equals(data.get(1), "exist")){
            export.add("u_e");
            data = UserHandler.add_data_to_database(STR."u,pv,\{username},\{password}");

            if(Objects.equals(data.get(1), "correct")){
                export.add("u_pc");
            }
            else{
                export.add("u_pnc");
            }
        }
        else{
            export.add("u_ne");
        }
        return export;
    }

    public static ArrayList<String> sign_up(String firstName,String lastName,String username, String password){
        ArrayList<String> export = new ArrayList<>();
        ArrayList<String> data = UserHandler.add_data_to_database(STR."u,uv,\{username}");

        if(Objects.equals(data.get(1), "exist")){
            export.add("u_e");
        }
        else{
            export.add("u_ne");
        }

        data = UserHandler.add_data_to_database(STR."u,a,\{firstName},\{lastName},\{username},\{password}");

        if(Objects.equals(data.get(0), "good")){
            export.add("u_a");
        }
        else{
            export.add("u_na");
        }

        return export;
    }
}
